package com.jq.client.model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

import com.jq.client.view.FriendsListFrame;

public class StartChatListener extends MouseAdapter {
	private FriendsListFrame friendsList = null;

	public StartChatListener(FriendsListFrame friendsList) {
		this.friendsList = friendsList;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 2 && e.getSource() instanceof JList) {

			@SuppressWarnings("unchecked")
			JList<String> list = (JList<String>) e.getSource();
			String clickedFriend = list.getSelectedValue();

			friendsList.getChatRoom(clickedFriend).setVisible(true);
		}
	}
}
