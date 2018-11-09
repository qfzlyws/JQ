package com.jq.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import com.jq.JQConstants;
import com.jq.MessageListener;

public class ServerMessageListener implements MessageListener {
	private Socket clientSocket;
	private Socket toClient;
	private PrintWriter fromPS;
	private PrintWriter toPS;

	public ServerMessageListener(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		fromPS = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(),"UTF-8"),true);
	}

	@Override
	public void messageReceived(String from, String to, String message) {

		if (to.equals(JQConstants.LOGIN_FLAG)) // 用戶登錄
		{
			if (JQServer.clientSocketLists.containsKey(from)) {
				fromPS.println(JQConstants.ERROR_FLAG + "用戶：" + from + "已經登錄！");
				
				return;
			}

			// 驗證用戶賬號密碼是否正確
			// UserService.checkUser(String username,String passsword);

			// 將用戶名和Socket加入Map對象中
			refreshOnlineUsers(from);
			JQServer.clientSocketLists.put(from, clientSocket);
			fromPS.println(JQConstants.SUCESS_FLAG);
		} else if (to.equals(JQConstants.WHOISONLINE)) {
			fromPS.println(onlineUsers(from));
		} else // 發送消息給特定的用戶
		{
			toClient = getSocket(to);

			if (toClient == null || toClient.isClosed()) {
				fromPS.println(JQConstants.ERROR_FLAG + "用戶:" + to + "不在線！");
				return;
			}

			try {
				toPS = new PrintWriter(new OutputStreamWriter(toClient.getOutputStream(),"UTF-8"),true);
				toPS.println(from + JQConstants.MESSAGE_SEPARATOR + message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void refreshOnlineUsers(String currentUser) {
		for (String username : JQServer.clientSocketLists.keySet()) {
			if (!username.equals(currentUser)) {
				try {
					toPS = new PrintWriter(new OutputStreamWriter(JQServer.clientSocketLists.get(username).getOutputStream(),"UTF-8"),true);					
					toPS.println(JQConstants.NEWONLINEUSER + JQConstants.MESSAGE_SEPARATOR + currentUser);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * 獲取在線用戶
	 */
	private String onlineUsers(String currentUser) {
		String onlineUsers = null;

		for (String username : JQServer.clientSocketLists.keySet()) {
			if (!username.equals(currentUser)) {

				if (onlineUsers == null)
					onlineUsers = username;
				else
					onlineUsers = onlineUsers + "," + username;
			}
		}

		return onlineUsers;
	}

	/**
	 * 根據用戶名稱查找對應的Socket
	 * 
	 * @param username
	 * @return
	 */
	private Socket getSocket(String username) {
		return JQServer.clientSocketLists.get(username);
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
