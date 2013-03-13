package com.wufang.client.utilities;
/**
 * ����һ������ͻ��˺ͷ���������ͨѶ���߳�
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
	//����ͨ��qqȡ�ø��߳�
	public  ClientThread getClientThread(String usename){
		return (ClientThread) hash.get(usename);
	}
}
