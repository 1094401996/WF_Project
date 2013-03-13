package com.wufang.client.backend;
/**
 * after the login verification, the user will use the thread to interact with the server.....
 */
import java.net.Socket;
import java.io.*;

import com.wufang.client.frontend.ChattingWindow;
import com.wufang.client.frontend.FriendList;
import com.wufang.client.utilities.ChattingWindowsManager;
import com.wufang.client.utilities.ClientThreadsManager;
import com.wufang.client.utilities.FriendListsManager;
import com.wufang.common.Info;
import com.wufang.common.InfoType;

public class ClientThread extends Thread{

	private  Socket s;
	
	public Socket getSocket(){
		return this.s;
	}
	public  ClientThread(Socket s){
		this.s = s;
	}
	
	public void sendInfo(Info out){
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
	public void run(){
		ChattingWindowsManager cwm = ChattingWindowsManager.getInstance();
		FriendListsManager flm = FriendListsManager.getInstance();
		while(true){
			
			try {
				
				Info in = this.receiveInfo();
				//check the type of Info instance...
				
				//if it is just the chatting message between users, just show the message on the 
				//corresponding chattingwindow....
				if(in.getInfoType().equals(InfoType.chatting_message)){
					//get the chattingwindow
					ChattingWindow cw = cwm.getChattingWindow(in.getReceiver()+ " "+ in.getSender());
					cw.setjta(in);
				// if it is the online friend list, i need to update the corresponding friendlist
				}else if(in.getInfoType().equals(InfoType.return_onlineFriends)){
					String usename = in.getReceiver();
					System.out.println("client " + in.getReceiver()+ "   gets the online friends list from the server:  " + in.getMessage());
					FriendList fl = flm.getFriendList(usename);
					if(fl!=null && in.getMessage()!= null){
						fl.update_onlineFriends(in);
					}
					//if it is the offline friend list, i need to update the corresponding friendlist
				}else if(in.getInfoType().equals(InfoType.notify_someone_off)){
					String receiver = in.getReceiver();
					String off_usename = in.getMessage();
					System.out.println("The server is telling me that User " + in.getMessage() + " is off now");
					FriendList fl = flm.getFriendList(receiver);
					fl.update_offlineFriends(in);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
