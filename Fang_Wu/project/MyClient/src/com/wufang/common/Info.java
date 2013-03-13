package com.wufang.common;
import java.io.*;

/**
 * all the exchanged infomation between user and server is wrappered in Info instance....
 * @author Administrator
 *
 */
public class Info implements Serializable {

	private String InfoType;
	private String Receiver;
	 private String Sender;
	 private String message;
	 private String Sendedtime;
	 private String username ;
	 
	 public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password ; 
	 
    public String getReceiver() {
		return Receiver;
	}
	public void setReceiver(String receiver) {
		Receiver = receiver;
	}
	public String getSender() {
		return Sender;
	}
	public void setSender(String sender) {
		Sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSendedtime() {
		return Sendedtime;
	}
	public void setSendedtime(String sendedtime) {
		Sendedtime = sendedtime;
	}
	
	public String getInfoType() {
		return InfoType;
	}
	public void setInfoType(String infoType) {
		this.InfoType = infoType;
	}
	public static void main(String[] args) {
		

	}

}
