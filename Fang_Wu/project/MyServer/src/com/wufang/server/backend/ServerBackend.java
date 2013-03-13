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
import com.wufang.server.utilities.SocketThreadsManager;
public class ServerBackend  {

	public  ServerBackend(){
		try {
			System.out.println("the port 5555 of server is listenning........");
			ServerSocket ss = new ServerSocket(5555);
			
			while(true){
			//the accept method is blocking.....so it will wait until some client tries to connect it
				Socket s=ss.accept();
				System.out.println("check mine");
			//read the info from the client
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Info in=(Info)ois.readObject();
				System.out.println("a client is trying to connect the server : " + in.getUsername() + ", the password: " + in.getPassword());
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				Info out = new Info();
				// as long as the password is 123456, the user is valid... you guys might use a databaseto 
				// verify it... But from my part, I think database is a little complex... I suggest you guys
				// use the file(store the valid user information in the file, once the server starts, read the 
				//file content into a hashtable....Sound easy and feasible.....
				if(in.getPassword().equals("123456")){
					// create a new thread on the server side, the thread will be responsible for interacting with the user
					SocketProcessThread spt = new SocketProcessThread(s);
					// the SocketThreadsManager is similar to the ClientThreadManager in the client....
					SocketThreadsManager stm = SocketThreadsManager.getInstance();
					stm.addSocketThread(in.getUsername(), spt);
					//start the thread
					spt.start();
					
				  //return a info, tell the user the login is successful
					out.setInfoType(InfoType.login_succeed);
					out.setReceiver(in.getSender());
					oos.writeObject(out);
					
				}else{
					 //return a info, tell the user the login failed
					out.setInfoType(InfoType.login_fail);
					oos.writeObject(out);
					s.close();
				}
				}
		} catch (Exception e) {
			System.out.println("bug in you server");
			e.printStackTrace();
		}
	}

}
