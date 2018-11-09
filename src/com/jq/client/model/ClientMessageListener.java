package com.jq.client.model;

import com.jq.JQConstants;
import com.jq.MessageListener;
import com.jq.client.view.ChatRoom;
import com.jq.client.view.FriendsList;

public class ClientMessageListener implements MessageListener {
	private FriendsList friendsList;
	
	public ClientMessageListener(FriendsList friendsList)
	{
		this.friendsList = friendsList;
	}
	
	@Override
	public void messageReceived(String from, String to, String message) {
		if(from.equals(JQConstants.NEWONLINEUSER))
			friendsList.AddUser(message);
		else
		{			
			startChat(from,message);
		}

	}
	
	private void startChat(String friend,String message)
	{
		ChatRoom room = friendsList.getChatRoom(friend);
		
		room.setDisplayMessages(friend + ":" + message);
	}

}
