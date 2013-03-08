package com.wufang.server.backend;
/**
 * 功能：是服务器和某个客户端的通信线程
 * @author gengchen
 *
 */
import java.net.*;
import java.io.*;

import com.wufang.common.*;
public class SocketProcessThread extends Thread{
	Socket s;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public SocketProcessThread(Socket s){
		//把服务器和该客户端的连接赋给s
		this.s = s;
	}
	public void run(){
		while(true){
			//该线程就可以接受客户端的信息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Info in = (Info)ois.readObject();
				System.out.println(in.getSender() + " is sending to " + in.getReceiver() +"the content is " + in.getMessage()  );
				
				//完成转发功能
				//取得接收人的通信线程
				SocketProcessThread st = SocketThreadManager.getSocketThread(in.getReceiver());
				ObjectOutputStream oos = new ObjectOutputStream(st.s.getOutputStream());
				oos.writeObject(in);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
