package com.jq;

import com.jq.client.model.Account;

/**
 * 連接管理器
 * @author IT-DM
 *
 */
public interface ConnectionManager {
	// connect to message server and route incoming messages
	// to given MessageListener
	public void connect();
	
	// disconnect from message server and stop routing
	// incoming messages to given MessageListener
	public void disconnect();
	
	// send chat message to message server
	public void sendMessage(String from,String to,String message);
	
	// login to server
	public String login(String account,String password);
	
	public Account getAccount();
	
	public void setMessageListener(MessageListener listener);
}
