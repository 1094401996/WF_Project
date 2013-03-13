package com.wufang.client.frontend;
/**
 * 在线会员列表
 * @author Fang Wu
 *
 */
import javax.swing.*;

import com.wufang.client.backend.ClientThread;
import com.wufang.client.utilities.ChattingWindowsManager;
import com.wufang.client.utilities.ClientThreadsManager;
import com.wufang.common.Info;
import com.wufang.common.InfoType;

import java.awt.*;
import java.awt.event.*;

public class FriendList extends JFrame implements MouseListener {
	JPanel jp1, jp2;
	JButton jb;
	JScrollPane jsp;
	String myID;
	JLabel[] jls = new JLabel[50];

	
	public void friendlist(String myID){
		
		this.myID = myID;
		jb = new JButton("my friends");
		jp1 = new JPanel(new BorderLayout());
		jp1.add(jb);
		//just imagine you have 50 friends...so you need to 50 labels...
		jp2 = new JPanel(new GridLayout(50,1,5,5));	
		
		for (int i = 0; i < jls.length; i++){
			jls[i] = new JLabel(i + 1 + "" , JLabel.LEFT);
			jls[i].setEnabled(false);
			if(jls[i].getText().equals(this.myID)){
				jls[i].setEnabled(true); // to show the user is on
			}
			jls[i].addMouseListener(this);// add  a listener on every label..if some one double click it, a ChattingWindow will pop 
			jp2.add(jls[i]);
			
		}
		jsp = new JScrollPane(jp2);
		this.add(jp1,"North");
		this.add(jsp,"Center");
		this.pack();
		this.setTitle(myID);
		
		// add a listerner on the FriendList, if the user quit, it will send a Info to server telling what happened
		// and then the server will forward the log off information to other online user so that their friendlists will
		// show the face
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				ClientThreadsManager ctm = ClientThreadsManager.getInstance();
				ClientThread ct = ctm.getClientThread(FriendList.this.myID);
				Info out = new Info();
				out.setInfoType(InfoType.notify_I_am_off);
				out.setSender(FriendList.this.myID);
				ct.sendInfo(out);
				FriendList.this.dispose();
			}
			
		});
		this.setVisible(true);
		
		
		
	}
	//update the online friends
	public void update_onlineFriends(Info in){
		String[] str = in.getMessage().split(" ");
		for(int i = 0; i < str.length; i++){
			jls[Integer.parseInt(str[i]) - 1].setEnabled(true);		}
	}
	//update the offline friends
	public void update_offlineFriends(Info in){
		jls[Integer.parseInt(in.getMessage()) - 1].setEnabled(false);	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel jlb = (JLabel)e.getSource();
		if((e.getClickCount() == 2) && (jlb.getText() != this.myID )){
			//get the id of the friend
			String friendId = ((JLabel)e.getSource()).getText();
			ChattingWindow cw = new ChattingWindow(friendId);
			cw.creatChattingWindow(myID);
			// you also need a manager to manage all the chattingwindows.....check the implementation of ChattingWindows
			ChattingWindowsManager cwm = ChattingWindowsManager.getInstance();
			cwm.addChattingWindow(this.myID + " " +friendId, cw);
		}else
			return ;
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	public void actionPerformed(ActionEvent e) {
		
	}

}
