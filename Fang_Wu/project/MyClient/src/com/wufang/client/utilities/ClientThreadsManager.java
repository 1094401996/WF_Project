package com.wufang.client.utilities;
/**
 * 这是一个管理客户端和服务器保持通讯的线程
 * @author gengchen
 *
 */
import java.net.*;
import java.util.*;

import com.wufang.client.backend.ClientThread;
public class ClientThreadsManager {
	private  Hashtable hash = new Hashtable<String, ClientThread>();
	private static ClientThreadsManager instance = null;
	private ClientThreadsManager(){
		
	}
	public static ClientThreadsManager getInstance(){
		if(instance == null){
			instance = new ClientThreadsManager();
		}
		return instance;
	}
	public  void addClientThread(String usename,ClientThread ct ){
		hash.put(usename, ct);
	}
	//可以通过qq取得该线程
	public  ClientThread getClientThread(String usename){
		return (ClientThread) hash.get(usename);
	}
}
