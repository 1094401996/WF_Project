package com.wufang.server.utilities;

import java.net.Socket;
import java.util.*;

import com.wufang.server.backend.SocketProcessThread;
public class SocketThreadsManager {
	private static SocketThreadsManager instance = null;
	private  HashMap<String,SocketProcessThread> Hash = new HashMap<String,SocketProcessThread>();
	private SocketThreadsManager(){
		
	}
	public static SocketThreadsManager getInstance(){
		if(instance == null){
			instance = new SocketThreadsManager();
		}
		return instance;
	}
	public void addSocketThread(String username,SocketProcessThread spt){
		Hash.put(username,spt);
	}
	public  SocketProcessThread getSocketThread(String username){
		return Hash.get(username);
	}
	public  String return_onlineFriends(){
		Iterator it = Hash.keySet().iterator();
		String str = "";
		while(it.hasNext()){
			str = str + it.next().toString() + " ";
		}
		return str;
	}
	public HashMap return_Hashmap(){
		return Hash;
	}
//	public ArrayList<SocketProcessThread> return_allThreads(){
//		ArrayList<SocketProcessThread>  threads = new ArrayList<SocketProcessThread>();
//		Iterator it = Hash.keySet().iterator();
//		while(it.hasNext()){
//			threads.add(Hash.get(it.next()));
//		}
//		return threads;
//	}
	public  void removeOffFriend(String usename){
		Hash.remove(usename);
	}

}
