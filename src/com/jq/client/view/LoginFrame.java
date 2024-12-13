package com.jq.client.view;

import java.awt.*;
import java.util.Arrays;

import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jq.ConnectionManager;
import com.jq.JQConstants;
import com.jq.MessageListener;
import com.jq.client.model.ClientMessageListener;
import com.jq.utilities.GUITools;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	private static Logger loger = LogManager.getLogger(LoginFrame.class);
	private JLabel header;
	private JButton loginJB;
	private JPanel panelLogin;
	private LoginFrameBackground loginFramebackground = null;
	private LoginFrameCenter loginCenter = null;

	public LoginFrame(ConnectionManager connManager) {
		super("登錄");

		ImageIcon heardIcon = new ImageIcon(this.getClass().getResource("images/loginViewHeader.png"));
		ImageIcon backPic = new ImageIcon(this.getClass().getResource("images/loginBackground.png"));

		header = new JLabel(heardIcon);
		loginJB = new JButton("登錄");

		this.loginFramebackground = new LoginFrameBackground(backPic.getImage());
		this.loginFramebackground.setLayout(new BorderLayout());

		loginCenter = new LoginFrameCenter();
		panelLogin = new JPanel();
		panelLogin.setOpaque(false);

		panelLogin.add(loginJB);

		loginFramebackground.add(header, BorderLayout.NORTH);
		loginFramebackground.add(loginCenter, BorderLayout.CENTER);
		loginFramebackground.add(panelLogin, BorderLayout.SOUTH);

		add(loginFramebackground, BorderLayout.CENTER);

		loginJB.addActionListener(e -> {
			String accountStr = loginCenter.getAccount();
			String password = loginCenter.getPassword();
			if (accountStr == null || accountStr.trim().equals("")) {
				loginCenter.setDisplayMessages("賬號不能為空");
				return;
			}

			if (password == null || password.trim().equals("")) {
				loginCenter.setDisplayMessages("密碼不能為空");
				return;
			}

			loginCenter.setDisplayMessages(" ");

			connManager.connect();

			String loginMessage = connManager.login(accountStr, password);

			if (loginMessage.startsWith(JQConstants.SUCESS_FLAG)) {
				String onlineUsers = loginMessage.replace(JQConstants.SUCESS_FLAG, "");
				String[] friends = null;

				if (!onlineUsers.equals("")) {
					friends = onlineUsers.split(",");
					loger.info(Arrays.toString(friends));
				}

				this.setVisible(false);
				FriendsListFrame friendList = new FriendsListFrame(connManager, friends);
				MessageListener listener = new ClientMessageListener(friendList);
				connManager.setMessageListener(listener);
			} else {
				loginCenter.setDisplayMessages(loginMessage);
			}
		});

		setSize(350, 240);
		setResizable(false);
		GUITools.setFrameCenter(this);
		setVisible(true);
	}
}
