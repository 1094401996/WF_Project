package com.wufang.client.backend;
/**
 * ���ǿͻ��˺ͷ������˱���ͨ�ŵ��߳�
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
				//�Ѵӷ������ж�������Ϣ���͵�����ʾ�����������
				ChattingWindow cw = ChattingWindowsManager.getChattingWindow(in.getReceiver() + " " + in.getSender());
				cw.setjta(in);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}