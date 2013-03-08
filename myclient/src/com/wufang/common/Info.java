package com.wufang.common;
import java.io.*;
public class Info implements Serializable {

	private String InfoType;
	private String Receiver;
	 private String Sender;
	 private String message;
	 private String Sendedtime;
	 
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
