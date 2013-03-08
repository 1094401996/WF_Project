package com.wufang.server.frontend;

import javax.swing.*;

import com.wufang.server.backend.ServerBackend;

import java.awt.*;
import java.awt.event.*;
public class ServerFrontend extends JFrame {
	JPanel jp;
	JButton jb1, jb2;
	public static void main(String[] args) {
		new ServerFrontend().startServer();
	}
	
	public void startServer(){
		jp = new JPanel();
		jb1 = new JButton("start the server");
		jb1.addActionListener(new startServer());
		jb2 = new JButton("close the server");
		jb2.addActionListener(new closeServer());
		jp.add(jb1);
		jp.add(jb2);
		
		this.add(jp,"South");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,400);
		this.setVisible(true);
	}
	
	class startServer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			new ServerBackend();
		}
		
	}
	class closeServer implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.exit(0);
		}
	}

}
