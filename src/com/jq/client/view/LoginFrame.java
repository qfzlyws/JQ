package com.jq.client.view;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.*;
import com.jq.ConnectionManager;
import com.jq.JQConstants;
import com.jq.MessageListener;
import com.jq.client.model.Account;
import com.jq.client.model.ClientMessageListener;
import com.jq.utilities.GUITools;

public class LoginFrame extends JFrame {
	private ConnectionManager connManager;
	private JLabel header;
	private JButton loginJB;
	private JPanel panelLogin;
	private LoginFrameBackground background = null;
	private LoginFrameCenter loginCenter = null;
	private LoginFrame loginFrame = this;

	public LoginFrame(ConnectionManager connManager) {
		super("登錄");

		this.connManager = connManager;

		ImageIcon heardIcon = new ImageIcon(this.getClass().getResource("images/loginViewHeader.png"));
		ImageIcon backPic = new ImageIcon(this.getClass().getResource("images/loginBackground.png"));

		header = new JLabel(heardIcon);
		loginJB = new JButton("登錄");

		background = new LoginFrameBackground(backPic.getImage());
		background.setLayout(new BorderLayout());

		loginCenter = new LoginFrameCenter();
		panelLogin = new JPanel();
		panelLogin.setOpaque(false);

		panelLogin.add(loginJB);

		background.add(header, BorderLayout.NORTH);
		background.add(loginCenter, BorderLayout.CENTER);
		background.add(panelLogin, BorderLayout.SOUTH);

		add(background, BorderLayout.CENTER);

		loginJB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String accountStr = loginCenter.getAccount();
				String password = loginCenter.getPassword();
				boolean isSavePassChecked = loginCenter.isSavePassChecked();
				boolean isAutoLoginChecked = loginCenter.isAutoLoginChecked();

				if (accountStr == null || accountStr.trim().equals("")) {
					loginCenter.setDisplayMessages("賬號不能為空");
					return;
				}

				if (password == null || password.trim().equals("")) {
					loginCenter.setDisplayMessages("密碼不能為空");
					return;
				}

				loginCenter.setDisplayMessages(" ");

				//if (!isSavePassChecked)
				//	password = null;
				
				connManager.connect();
				
				String loginMessage = connManager.login(accountStr, password);
				
				if(loginMessage.startsWith(JQConstants.SUCESS_FLAG))
				{
					String onlineUsers = loginMessage.replace(JQConstants.SUCESS_FLAG, "");
					String[] friends = null;

					if (!onlineUsers.equals("null")) {
						friends = onlineUsers.split(",");
					}
					
					loginFrame.setVisible(false);
					FriendsList friendList = new FriendsList(connManager, friends);
					MessageListener listener = new ClientMessageListener(friendList);
					connManager.setMessageListener(listener);
				}
				else
				{
					loginCenter.setDisplayMessages(loginMessage);
				}
			}
		});

		setSize(350, 240);
		setResizable(false);
		GUITools.setFrameCenter(this);
		setVisible(true);
	}
}
