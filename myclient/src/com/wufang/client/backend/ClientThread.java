package com.wufang.client.backend;
/**
 * 这是客户端和服务器端保持通信的线程
 */
import java.net.Socket;
import java.io.*;

import com.wufang.client.frontend.ChattingWindow;
import com.wufang.common.Info;

public class ClientThread extends Thread{

	private  Socket s;
	
	public Socket getSocket(){
		return this.s;
	}
	public  ClientThread(Socket s){
		this.s = s;
	}
	
	public void run(){
		while(true){
			
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Info in = (Info)ois.readObject();
				System.out.println(in.getSender() + " said to " + in.getReceiver() + " : " + in.getMessage() + " at " + in.getSendedtime());
				//把从服务器中读到的消息发送到该显示的聊天界面上
				ChattingWindow cw = ChattingWindowsManager.getChattingWindow(in.getReceiver() + " " + in.getSender());
				cw.setjta(in);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
