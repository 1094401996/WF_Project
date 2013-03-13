package com.wufang.client.frontend;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.wufang.client.backend.ClientBackend;
import com.wufang.client.backend.ClientLoginVerify;
import com.wufang.client.backend.ClientThread;
import com.wufang.client.utilities.ClientThreadsManager;
import com.wufang.client.utilities.FriendListsManager;
import com.wufang.common.Info;
import com.wufang.common.InfoType;
import java.awt.Container;
import java.io.*;
/*@author Fang Wu
 * 
 * @function 
 * Frontend of client. the entrance of the client. 
 * 
 */
public class ClientLogin extends JFrame {
	//define the north part of frame
	JLabel jln = null;
	
	//deifne the middle part of frame
	JPanel jpm = null;
	JLabel jlm1,jlm2;
	JTextField jtm;
	JPasswordField jpwm;
	
	//define the south part of frame
	JPanel jps = null;
	JButton jbs1,jbs2,jbs3;

	public static void main(String[] args) {
		new ClientLogin().startClient();
	}
	
	public void startClient(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jln = new JLabel("welcome to the chat client",JLabel.CENTER);
		
		
		jps = new JPanel();
		jbs1 = new JButton("log In");
		// add a listener instance on the "login in" button, once you click the button , the client will send the 
		// username information to the server....
		jbs1.addActionListener(new loginListener());
		jbs2 = new JButton("cancel");
		jbs3 = new JButton("Sign Up");
		jps.add(jbs1);
		jps.add(jbs2);
		jps.add(jbs3);
		
		jpm = new JPanel(new GridLayout(2,2));
		jlm1 = new JLabel("username",JLabel.CENTER);
		jlm2 = new JLabel("password",JLabel.CENTER);
		jtm = new JTextField();
		jpwm = new JPasswordField();
		jpm.add(jlm1);
		jpm.add(jtm);
		jpm.add(jlm2);
		jpm.add(jpwm);
		
		this.add(jln,"North");
		this.add(jps,"South");
		this.add(jpm,"Center");
		this.pack();
		this.setResizable(false);
		this.setVisible(true);	
	}
	// the listener class, a innner class..... 
	private class loginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//  verification module on the client side, it is responsible to pass the user information to the  ClentBackend class. 
			//  the ClientBackend will interact with the server about the verification of the user information
			// I think you need to check the ClientBackend class in detail in order to totally understand the design 
			// 
			ClientLoginVerify clv = new ClientLoginVerify();
			Info out = new Info();
			out.setUsername(jtm.getText());
			out.setPassword(new String(jpwm.getPassword()));
			out.setSender(jtm.getText());
			//if the verification is right, the login view will disappear, and the friendlist will show up
			if(clv.verifyUser(out)){
				
				try {
					//creat a FrinedList
					FriendList fl = new FriendList();
					fl.friendlist(out.getUsername());
					//add the friendlist to FriendlistManager, you must keep in mind that some users might login in on the 
					// same computer.... So you need  some utility (FriendlistManager£© to manage them . To understand the FriendListsManger deeper, you need
					// to check the implementation of FriendlistManager and the diagram I give you .....
					FriendListsManager flm = FriendListsManager.getInstance();
					flm.addFriendList(out.getUsername(), fl);
					ClientLogin.this.dispose();
					
					//every user will have its own thread(they are binded, check the ClientThread and CLientEnd class)  . 
					//The thread will totally carry the duty to interact with the server for the user after the user logins in successfully...
					// just as the FriendListsManager, the ClientThreadsManager will manage all the threads....remind you again some users might login in on the 
					// same computer
					ClientThreadsManager ctm = ClientThreadsManager.getInstance();
					ClientThread ct = ctm.getClientThread(out.getUsername());
					
					//after the frindlist shows up , it needs to know who is online... So it will send a packet(an object Info) to the server to check. Just keep in mind
					// all the exchanged information between the client and server are wrappered in the Info instance.....you can define different kinds of 
					//information packet through the attribute " InfoType" in the Info class...... you can define the value of InfoType in InfType interface... 
					// I think the design of Info and InfoType are the most attractive points in the project... I hope you guys can understand it since it could
					//help you a lot if you want to extend the function of the project....
					Info out2 = new Info();
					out2.setSender(out.getUsername());
					out2.setInfoType(InfoType.require_onlineFriends);
					ct.sendInfo(out2);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				// the verification failed, just pop a dialog to remind the user.....
			}else{
				JOptionPane.showMessageDialog(ClientLogin.this,"wrong username or password");
			}
			
		}
		
	}
	

}
