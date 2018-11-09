package com.jq.client.model;

import javax.swing.JFrame;

import com.jq.ConnectionManager;
import com.jq.JQConstants;
import com.jq.client.view.*;

public class JQMain {

	public final static String appPath = System.getProperty("user.dir");
	
	public static void main(String[] args) {
		ConnectionManager connManager = new SocketConnectionManager(JQConstants.SERVER_ADDRESS);
		
		LoginFrame loginFrame = new LoginFrame(connManager);
		
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
