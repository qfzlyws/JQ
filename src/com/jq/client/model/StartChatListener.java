package com.jq.client.model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

import javax.swing.JList;

import com.jq.client.view.ChatRoom;
import com.jq.client.view.FriendsList;
import com.jq.client.view.FriendsListCell;

public class StartChatListener extends MouseAdapter {
	private FriendsList friendsList = null;
	
	public StartChatListener(FriendsList friendsList)
	{
		this.friendsList = friendsList;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getClickCount() == 2)
		{
			if(e.getSource() instanceof JList)
			{
				JList list = (JList)e.getSource();
				String clickedFriend = (String) list.getSelectedValue();
				
				friendsList.getChatRoom(clickedFriend);
			}
		}
	}
	
}
