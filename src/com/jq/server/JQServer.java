package com.jq.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jq.JQConstants;

public class JQServer {
	private static Logger loger = LogManager.getLogger(JQServer.class);
	public static ConcurrentMap<String, Socket> clientSocketLists = null;

	public static void main(String[] args) {
		try (ServerSocket ss = new ServerSocket(JQConstants.SERVER_PORT, 0,
				InetAddress.getByName(JQConstants.SERVER_ADDRESS))) {

			loger.info("Server listening on port {}",JQConstants.SERVER_PORT + "...");
			
			clientSocketLists = new ConcurrentHashMap<>();
			while (true) {
				Socket s = ss.accept();

				new Thread(new ServerThreadTask(s)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
