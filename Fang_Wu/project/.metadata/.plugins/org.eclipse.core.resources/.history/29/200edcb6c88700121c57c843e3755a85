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
			System.out.println("服务器5555正在监听");
			ServerSocket ss = new ServerSocket(5555);
			
			while(true){
			//the accept method is blocking.....
				Socket s=ss.accept();
				System.out.println("check mine");
			//接受客户端的信息
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject();
				System.out.println("I get the user info" + u.getUsername() + "  " + u.getPassword());
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				Info in = new Info();
				if(u.getPassword().equals("123456")){
				//返回一个成功登录的信息包,认证用户登录的地方
					in.setInfoType("1");
					oos.writeObject(in);
					//在这里单开一个线程
					SocketProcessThread spt = 	new SocketProcessThread(s);
					SocketThreadManager.addSocketThread(u.getUsername(), spt);
					
					//启动和该客户端通信的线程
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
