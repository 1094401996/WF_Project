package com.wufang.client.backend;
/**
 * ����һ������ͻ��˺ͷ���������ͨѶ���߳�
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
	//����ͨ��qqȡ�ø��߳�
	public static ClientThread getClientThread(String usename){
		return (ClientThread) hm.get(usename);
	}
}
