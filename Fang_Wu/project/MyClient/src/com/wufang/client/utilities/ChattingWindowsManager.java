package com.wufang.client.utilities;
import java.util.*;
import com.wufang.client.frontend.*;
public class ChattingWindowsManager {
	private  HashMap hash = new HashMap<String, ChattingWindow>();
	private static ChattingWindowsManager instance = null;
	private ChattingWindowsManager(){
		
	}
	public static ChattingWindowsManager getInstance(){
		if (instance == null){
			instance = new ChattingWindowsManager();
		}
		return instance;
	}
	
	public  void addChattingWindow(String bothnames,ChattingWindow cw ){
		hash.put(bothnames,cw);
	}
	public  ChattingWindow getChattingWindow(String bothnames ){
		return (ChattingWindow)hash.get(bothnames);
	}
	public void removeChattingWindow(String bothnames){
		hash.remove(bothnames);
	}

}

