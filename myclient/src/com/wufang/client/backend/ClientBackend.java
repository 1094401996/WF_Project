/**
 * backend of the client,用来和服务器交互的
 * @author Fang Wu
 */
package com.wufang.client.backend;
import java.util.*;
import java.net.*;
import java.io.*;

import com.wufang.common.*;

public class ClientBackend {
	public Socket s;

	//发送第一次请求
	public boolean sendLoginInfo(Object o){
		boolean b = false;
		try{
			
		    s = new Socket("127.0.0.1",5555);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Info in = (Info)ois.readObject();
			if(in.getInfoType().equals("1") ){
				ClientThread ct = new ClientThread(this.s);
				//启动该线程
				ct.start();
				ClientThreadManager.addClientThread(((User)o).getUsername(), ct);
				
				
				b= true;
			}else{
				b = false;
				s.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return b ;
		}
	}

}
