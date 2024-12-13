package com.jq.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jq.JQConstants;
import com.jq.MessageListener;

public class ServerThreadTask implements Runnable {
	private static Logger loger = LogManager.getLogger(ServerThreadTask.class);
	private Socket connection = null;
	private MessageListener messageListener;

	public ServerThreadTask(Socket s){
		this.connection = s;
		messageListener = new ServerMessageListener(s);
	}

	@Override
	public void run() {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));) {
			String content = null;
			while ((content = br.readLine()) != null) {
				StringTokenizer stringTokenizer = new StringTokenizer(content, JQConstants.MESSAGE_SEPARATOR);

				messageListener.messageReceived(stringTokenizer.nextToken(), stringTokenizer.nextToken(),
						stringTokenizer.nextToken());
			}
		} catch (IOException e) {
			loger.error("",e);
		}
		
		clearSocket();

		if (connection != null)
			try {
				connection.close();
			} catch (IOException e) {
				loger.error("",e);
			}
	}

	/**
	 * 清除保存的Socket
	 */
	private void clearSocket() {
		for (String username : JQServer.clientSocketLists.keySet()) {
			if (JQServer.clientSocketLists.get(username) == connection) {
				JQServer.clientSocketLists.remove(username);
				loger.info("Sockets number:{}",JQServer.clientSocketLists.size());
				break;
			}
		}
	}
}
