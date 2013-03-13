package com.wufang.client.backend;
/**
 * 这是一个管理客户端和服务器保持通讯的线程
 * @author gengchen
 *
 */
import java.net.*;
import java.util.*;
public class ClientThreadManager {
	private static Hashtable hm = new Hashtable<String, ClientThread>();
	
	public static void addClientThread(String usename,ClientThread ct ){
		hm.put(usename, ct);
	}
	//可以通过qq取得该线程
	public static ClientThread getClientThread(String usename){
		return (ClientThread) hm.get(usename);
	}
}
