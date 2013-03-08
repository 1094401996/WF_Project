package com.wufang.client.frontend;
/**
 * 在线会员列表
 * @author Fang Wu
 *
 */
import javax.swing.*;

import com.wufang.client.backend.ChattingWindowsManager;

import java.awt.*;
import java.awt.event.*;

public class FriendList extends JFrame implements MouseListener {
	JPanel jp1, jp2;
	JButton jb;
	JScrollPane jsp;
	String myID;
	public static void main(String[] args) {
		new FriendList().friendlist("2");
		
	}
	public void friendlist(String myID){
		
		this.myID = myID;
		jb = new JButton("my friends");
		jp1 = new JPanel(new BorderLayout());
		jp1.add(jb);
		//假设50个好友
		jp2 = new JPanel(new GridLayout(50,1,5,5));	
		JLabel[]jls = new JLabel[50];
		for (int i = 0; i < jls.length; i++){
			jls[i] = new JLabel(i + 1 + "" , JLabel.LEFT);
			jls[i].addMouseListener(this);
			jp2.add(jls[i]);
			
		}
		jsp = new JScrollPane(jp2);
		this.add(jp1,"North");
		this.add(jsp,"Center");
		this.pack();
		this.setTitle(myID);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2){
			//get the id of the friend
			String friendId = ((JLabel)e.getSource()).getText();
			ChattingWindow cw = new ChattingWindow(friendId);
			cw.creatChattingWindow(myID);
			//把聊天界面写入到管理类
			ChattingWindowsManager.addChattingWindow(this.myID + " " +friendId, cw);
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.BLUE);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.black);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
