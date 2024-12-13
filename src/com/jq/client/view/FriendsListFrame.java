package com.jq.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.jq.ConnectionManager;
import com.jq.client.model.StartChatListener;
import com.jq.utilities.GUITools;

@SuppressWarnings("serial")
public class FriendsListFrame extends JFrame {
	private ConnectionManager connManager;
	private JList<String> friendsList = null;
	private Map<String,ChatRoom> chatRooms = null;
	
	public FriendsListFrame(ConnectionManager connManager,String[] onlineUsers)
	{		
		super(connManager.getAccount().getAccountName());
		
		this.connManager = connManager;
		this.chatRooms = new HashMap<>();
		
		DefaultListModel<String> model = new DefaultListModel<>();
		
		if(onlineUsers != null)
		{
			for(String item:onlineUsers)
				model.addElement(item);
		}
		
		friendsList = new JList<>(model);
		friendsList.setCellRenderer(new FriendCell());
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
	
	public void addUser(String newUser)
	{
		DefaultListModel<String> model = (DefaultListModel<String>) friendsList.getModel();
		model.addElement(newUser);
	}
}
