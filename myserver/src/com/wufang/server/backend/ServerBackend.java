package com.wufang.server.backend;
/**
 * 
 * @author Fang Wu
 *
 */
import java.io.*;
import java.net.*;
import java.util.*;
import com.wufang.common.*;
public class ServerBackend  {

	public  ServerBackend(){
		try {
			System.out.println("������5555���ڼ���");
			ServerSocket ss = new ServerSocket(5555);
			
			while(true){
			//the accept method is blocking.....
				Socket s=ss.accept();
				System.out.println("check mine");
			//���ܿͻ��˵���Ϣ
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject();
				System.out.println("I get the user info" + u.getUsername() + "  " + u.getPassword());
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				Info in = new Info();
				if(u.getPassword().equals("123456")){
				//����һ���ɹ���¼����Ϣ��,��֤�û���¼�ĵط�
					in.setInfoType("1");
					oos.writeObject(in);
					//�����ﵥ��һ���߳�
					SocketProcessThread spt = 	new SocketProcessThread(s);
					SocketThreadManager.addSocketThread(u.getUsername(), spt);
					
					//�����͸ÿͻ���ͨ�ŵ��߳�
					spt.start();
				}else{
					in.setInfoType("2");
					oos.writeObject(in);
					s.close();
				}
				}
		} catch (Exception e) {
			System.out.println("bug in you server");
			e.printStackTrace();
		}
	}

}
