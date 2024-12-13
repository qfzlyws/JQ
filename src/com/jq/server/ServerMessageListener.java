package com.jq.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jq.JQConstants;
import com.jq.MessageListener;

public class ServerMessageListener implements MessageListener {
	private static Logger loger = LogManager.getLogger(ServerMessageListener.class);
	private Socket clientSocket;

	public ServerMessageListener(Socket clientSocket){
		this.clientSocket = clientSocket;
	}

	@Override
	public void messageReceived(String from, String to, String message) {

		PrintWriter fromPS;
		PrintWriter toPS;
		Socket toClient;

		try {
			fromPS = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8),
					true);

			if (to.equals(JQConstants.LOGIN_FLAG)) // 用戶登錄
			{
				if (JQServer.clientSocketLists.containsKey(from)) {
					fromPS.println(JQConstants.ERROR_FLAG + "用戶：" + from + "已經登錄！");
					return;
				}

				// 將用戶名和Socket加入Map對象中
				refreshOnlineUsers(from);
				JQServer.clientSocketLists.put(from, clientSocket);
				fromPS.println(JQConstants.SUCESS_FLAG);
			} else if (to.equals(JQConstants.WHOISONLINE)) {
				fromPS.println(onlineUsers(from));
			} else { // 發送消息給特定的用戶
				toClient = getSocket(to);
				if (toClient == null || toClient.isClosed()) {
					fromPS.println(JQConstants.ERROR_FLAG + "用戶:" + to + "不在線！");
					return;
				}

				toPS = new PrintWriter(new OutputStreamWriter(toClient.getOutputStream(), StandardCharsets.UTF_8),
						true);
				toPS.println(from + JQConstants.MESSAGE_SEPARATOR + message);
			}
		} catch (IOException e) {
			loger.error("",e);
		}
	}

	private void refreshOnlineUsers(String currentUser) throws IOException {
		for (String username : JQServer.clientSocketLists.keySet()) {
			if (!username.equals(currentUser)) {
				PrintWriter toPS = new PrintWriter(
						new OutputStreamWriter(JQServer.clientSocketLists.get(username).getOutputStream(),StandardCharsets.UTF_8),
						true);
				toPS.println(JQConstants.NEWONLINEUSER + JQConstants.MESSAGE_SEPARATOR + currentUser);
			}
		}
	}

	/**
	 * 獲取在線用戶
	 */
	private String onlineUsers(String currentUser) {
		StringBuilder onlineUsers = new StringBuilder();

		for (String username : JQServer.clientSocketLists.keySet()) {
			if (!username.equals(currentUser)) {
				if(onlineUsers.indexOf(",") == -1) {
					onlineUsers.append(username);
				}else
					onlineUsers.append("," + username);
			}
		}

		return onlineUsers.toString();
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
}
