package com.wufang.server.backend;
/**
 * the class provides the all the service to the assigned user
 * after the user verification, servr will use the thread to interact with the user....
 * @author Fang Wu
 *
 */
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;

import com.wufang.common.*;
import com.wufang.server.utilities.SocketThreadsManager;
public class SocketProcessThread extends Thread{
	Socket s;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public SocketProcessThread(Socket s){
		
		this.s = s;
	}
	
	private void sendInfo(Info out){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private Info receiveInfo(){
		try {
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
     		Info in = (Info)ois.readObject();
     		return in;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//tell all the onlineusers someone is just on , you need to update you online friendlist...
	public void notify_All_someone_am_On(String username){
		SocketThreadsManager stm = SocketThreadsManager.getInstance();
		HashMap hash = stm.return_Hashmap();
		Iterator it = hash.keySet().iterator();
		Info out = new Info();
		out.setInfoType(InfoType.return_onlineFriends);
		out.setMessage(username);
		while(it.hasNext()){
			String receiver = it.next().toString();
			if(!receiver.equals(username)){
				out.setReceiver(receiver);
				SocketProcessThread spt = stm.getSocketThread(receiver);
				spt.sendInfo(out);
			}
		}
	}
	//tell all the onlineusers someone is just off , you need to update you offline friendlist...

	private void notity_all_someone_off(String usename){
		SocketThreadsManager stm = SocketThreadsManager.getInstance();
		HashMap hash = stm.return_Hashmap();
		Iterator it = hash.keySet().iterator();
		Info out = new Info();
		out.setInfoType(InfoType.notify_someone_off);
		out.setMessage(usename);
		while(it.hasNext()){
			String receiver = it.next().toString();
			out.setReceiver(receiver);
			SocketProcessThread spt = stm.getSocketThread(receiver);
			spt.sendInfo(out);
		}
	}
	public void run(){
		
		SocketThreadsManager stm = SocketThreadsManager.getInstance();

		while(true){
			
			Info in = this.receiveInfo();//blocking......
			
			//check the type of Info instance...
			
			//if it is just the chatting message between users, just forward the message to the receiver
			if(in.getInfoType().equals(InfoType.chatting_message)){
				SocketProcessThread spt = stm.getSocketThread(in.getReceiver());
				spt.sendInfo(in);
				// if it's the requirement for the onlinefriens.. return the list to the user
			}else if(in.getInfoType().equals(InfoType.require_onlineFriends)){
				
				System.out.println("user" +in.getSender() + "  is requiring the onlineuser list");
				Info out = new Info();
				out.setInfoType(InfoType.return_onlineFriends);
				out.setReceiver(in.getSender());					
				String str = stm.return_onlineFriends();
				out.setMessage(str);
				this.sendInfo(out);
				System.out.println("the server is sending back online friendlist : " + str + " to " + out.getReceiver());
				notify_All_someone_am_On(in.getSender());
				//if it's the notice that I will be off, notify all other online users to update the friendlist...
			}else if (in.getInfoType().equals(InfoType.notify_I_am_off)){
				stm.removeOffFriend(in.getSender());
				notity_all_someone_off(in.getSender());
			}
		}
	}

}
