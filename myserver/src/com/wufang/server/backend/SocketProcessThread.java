package com.wufang.server.backend;
/**
 * ���ܣ��Ƿ�������ĳ���ͻ��˵�ͨ���߳�
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
		//�ѷ������͸ÿͻ��˵����Ӹ���s
		this.s = s;
	}
	public void run(){
		while(true){
			//���߳̾Ϳ��Խ��ܿͻ��˵���Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Info in = (Info)ois.readObject();
				System.out.println(in.getSender() + " is sending to " + in.getReceiver() +"the content is " + in.getMessage()  );
				
				//���ת������
				//ȡ�ý����˵�ͨ���߳�
				SocketProcessThread st = SocketThreadManager.getSocketThread(in.getReceiver());
				ObjectOutputStream oos = new ObjectOutputStream(st.s.getOutputStream());
				oos.writeObject(in);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
