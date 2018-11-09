// SocketMessageManager.java
// SocketMessageManager communicates with a DeitelMessengerServer using
// Sockets and MulticastSockets.
package com.jq.client.model;

import java.net.*;
import java.io.*;

import com.jq.ConnectionManager;
import com.jq.JQConstants;
import com.jq.MessageListener;
import com.jq.client.view.FriendsList;

public class SocketConnectionManager implements ConnectionManager {
	private Account account;
	private Socket clientSocket;
	private BufferedReader br;
	private PrintWriter ps;
	private String serverAddress;

	// Thread for receiving messages
	private ReceiveMessageThread receivingThread;

	// flag indicating connection status
	private boolean connected = false;

	// SocketConnectionManager constructor
	public SocketConnectionManager(String address) {
		serverAddress = address;
	}

	// connect to server and send messsages to given MessageListener
	@Override
	public void connect() {
		// if already connected,return immediately
		if (connected)
			return;

		// open Socket connection to DeitelMessengerServer
		try {
			clientSocket = new Socket(InetAddress.getByName(serverAddress), JQConstants.SERVER_PORT);
			//clientSocket.setSoTimeout(5000);

			br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(),"UTF-8"));
			ps = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(),"UTF-8"));

			// update connected flag
			connected = true;
		}

		// handle exception connection to server
		catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	// disconnect from server and unregister given MessageListener
	@Override
	public void disconnect() {
		// if not connected,return immediately
		if (!connected)
			return;

		// stop listening thread and disconnect from server
		try {
			// notify server that client is disconnectiong
			ps.println(JQConstants.DISCONNECT_STRING);
			ps.flush();

			// wait 10 seconds for disconnect message to be sent
			// join(10000);

			// stop receivingThread and remove given MessageListener
			receivingThread.stopListening();

			// close outgoing Socket
			clientSocket.close();
		}

		// handle exception disconnecting from server
		catch (IOException ioException) {
			// ioException.printStackTrace();
		}

		// update connected flag
		connected = false;
	}

	// send message to server
	@Override
	public void sendMessage(String from, String to, String message) {
		// if not connected,return immediately
		if (!connected)
			return;

		ps.println(from + JQConstants.MESSAGE_SEPARATOR + to + JQConstants.MESSAGE_SEPARATOR + message);
		ps.flush();

	}

	public String login(String account, String password) {
		if (!connected)
			return "无法连接到服务器!";

		try {
			// 開始登錄
			sendMessage(account, JQConstants.LOGIN_FLAG, password);
			String reply = br.readLine();

			if (reply.startsWith(JQConstants.SUCESS_FLAG)) {
				// 獲取在線用戶
				sendMessage(account, JQConstants.WHOISONLINE, JQConstants.WHOISONLINE);

				String onlineUserStr = br.readLine();
				
				this.account = new Account(account, password);

				return JQConstants.SUCESS_FLAG + onlineUserStr;

			} else {
				return reply.replace(JQConstants.ERROR_FLAG, "");
			}
		} catch (IOException ioException) {
			return "登录失败!";
		}
	}
	
	public void setMessageListener(MessageListener listener)
	{		
		receivingThread = new ReceiveMessageThread(br, listener);
		receivingThread.start();
	}
	
	public Account getAccount()
	{
		return account;
	}
	
	/*private String getUTF8Message(String message)
	{
		String utf8Mess;
		
		try {
			utf8Mess = new String(message.getBytes(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			utf8Mess = null;
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return utf8Mess;
	}*/

}
