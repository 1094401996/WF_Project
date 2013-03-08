package com.wufang.client.frontend;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.wufang.client.backend.ClientBackend;
import com.wufang.client.backend.ClientLoginVerify;
import com.wufang.common.Info;
import com.wufang.common.User;

import java.awt.Container;
import java.io.*;
/*@author Fang Wu
 * @function frontend of client. login....
 * ����Ҫ����Ϣ��Ҫ������Ϣ�����Կͻ���Ҫ���߳�
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
		//��������
		jln = new JLabel("welcome to the chat client",JLabel.CENTER);
		//�����ϱ�
		jps = new JPanel();
		jbs1 = new JButton("login");
		jbs1.addActionListener(new loginListener());
		jbs2 = new JButton("cancel");
		jbs3 = new JButton("help");
		jps.add(jbs1);
		jps.add(jbs2);
		jps.add(jbs3);
		
		//�����м�
		jpm = new JPanel(new GridLayout(2,2));
		jlm1 = new JLabel("username",JLabel.CENTER);
		jlm2 = new JLabel("password",JLabel.CENTER);
		jtm = new JTextField();
		jpwm = new JPasswordField();
		jpm.add(jlm1);
		jpm.add(jtm);
		jpm.add(jlm2);
		jpm.add(jpwm);
		
		
//		this.setSize(400,250);
		this.add(jln,"North");
		this.add(jps,"South");
		this.add(jpm,"Center");
		this.pack();
		this.setResizable(false);
		this.setVisible(true);	
	}
	class loginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			ClientLoginVerify clv = new ClientLoginVerify();
			User u = new User();
			u.setUsername(jtm.getText());
			u.setPassword(new String(jpwm.getPassword()));
			if(clv.verifyUser(u)){
				new FriendList().friendlist(u.getUsername());
				//attention
				ClientLogin.this.dispose();
			}else{
				JOptionPane.showMessageDialog(ClientLogin.this,"wrong username or password");
			}
			
		}
		
	}
	

}