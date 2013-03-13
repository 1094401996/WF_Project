package com.wufang.client.utilities;

import java.util.Hashtable;

import com.wufang.client.backend.ClientThread;
import com.wufang.client.frontend.FriendList;

public class FriendListsManager {
	private  Hashtable hash = new Hashtable<String, FriendList>();
	private static FriendListsManager instance = null;
	private FriendListsManager(){
		
	}
	public static FriendListsManager getInstance(){
		if(instance == null){
			instance = new FriendListsManager();
		}
		return instance;
	}
	
	public  void addFriendList(String usename,FriendList fl ){
		hash.put(usename, fl);
	}
	public  FriendList getFriendList(String usename){
		return (FriendList) hash.get(usename);
	}
}
