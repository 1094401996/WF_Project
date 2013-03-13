/**
 * 
 * @author Fang Wu
 * @function
 * when the user login, the module will get the user information from the CientLoginVerify module, and send it
 * to server and will pass the verification result back to the CientLoginVerify module. In the process, it will
 * create a thread for every verified user
 * 
 */
package com.wufang.client.backend;
import java.util.*;
import java.net.*;
import java.io.*;

import com.wufang.client.utilities.ClientThreadsManager;
import com.wufang.common.*;

public class ClientBackend {
	public Socket s;

	public boolean sendInfo(Info out){
		boolean b = false;
		try{
			
		    s = new Socket("127.0.0.1",5555);//connect to server
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(out);
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Info in = (Info)ois.readObject();
			// if the verification is successful
			if(in.getInfoType().equals(InfoType.login_succeed)){
				// create a thread for the user..
				ClientThread ct = new ClientThread(this.s);
				ClientThreadsManager ctm = ClientThreadsManager.getInstance();
				ctm.addClientThread(out.getUsername(), ct);
				//start the thread
				ct.start();
				b= true;
			}else if(in.getInfoType().equals(InfoType.login_fail)){
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
