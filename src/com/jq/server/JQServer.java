package com.jq.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import com.jq.JQConstants;

public class JQServer {
	//public static ArrayList<Socket> socketList = new ArrayList<Socket>();
	public static ConcurrentHashMap<String, Socket> clientSocketLists = null;
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(JQConstants.SERVER_PORT,0,InetAddress.getByName(JQConstants.SERVER_ADDRESS));
		
		System.out.println("Server listening on port " + JQConstants.SERVER_PORT + "...");
		clientSocketLists = new ConcurrentHashMap<String,Socket>();
		while(true)
		{
			Socket s = ss.accept();
			
			new Thread(new ServerThread(s)).start();
		}
	}
}
