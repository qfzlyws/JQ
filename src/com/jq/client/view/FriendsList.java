package com.jq.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.jq.ConnectionManager;
import com.jq.client.model.StartChatListener;
import com.jq.utilities.GUITools;

public class FriendsList extends JFrame {
	private ConnectionManager connManager;
	private JList friendsList = null;
	private HashMap<String,ChatRoom> chatRooms = new HashMap<String,ChatRoom>();
	
	public FriendsList(ConnectionManager connManager,String[] onlineUsers)
	{		
		super(connManager.getAccount().getAccount());
		
		this.connManager = connManager;
		
		DefaultListModel model = new DefaultListModel();
		
		if(onlineUsers != null)
		{
			for(Object item:onlineUsers)
				model.addElement(item);
		}
		
		friendsList = new JList(model);
		friendsList.setCellRenderer(new FriendsListCell());
		friendsList.setBackground(new Color(235,242,250));
		friendsList.addMouseListener(new StartChatListener(this));
		
		this.getContentPane().add(new JScrollPane(friendsList), BorderLayout.CENTER);
		this.setSize(250, 500);
		
		GUITools.setFrameCenter(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
	}

	public ChatRoom getChatRoom(String friend)
	{
		ChatRoom room = chatRooms.get(friend);
		
		if(room == null)
		{
			room = new ChatRoom(connManager,friend);
			chatRooms.put(friend, room);
		}
		
		return room;
	}
	
	public void AddUser(String newUser)
	{
		DefaultListModel model = (DefaultListModel) friendsList.getModel();
		model.addElement(newUser);
	}
}
