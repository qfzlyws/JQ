package com.jq.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.StringTokenizer;

import com.jq.JQConstants;
import com.jq.MessageListener;

public class ServerThread implements Runnable {
	Socket connection = null;
	MessageListener messageListener;
	BufferedReader br;
	String content;
	
	public ServerThread(Socket s) throws IOException
	{
		this.connection = s;
		messageListener = new ServerMessageListener(s);
	}
	
	@Override
	public void run() {
		try
		{
			//獲取當前Socket的輸入流
			br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));			
						
			while((content = br.readLine()) != null)
			{
				System.out.println(content);
				
				StringTokenizer stringTokenizer = new StringTokenizer(content,JQConstants.MESSAGE_SEPARATOR);
				
				messageListener.messageReceived(stringTokenizer.nextToken(), stringTokenizer.nextToken(), stringTokenizer.nextToken());
			}
		}
		catch(IOException ioException)
		{
			ioException.printStackTrace();
		}
		finally
		{
			clearSocket();
			
			try
			{
			if(br != null)
				br.close();
			
			if(connection != null)
				connection.close();
			}
			catch(IOException ie)
			{
				ie.printStackTrace();
			}
		}
	}
	
	/**
	 * 清除保存的Socket
	 */
	private void clearSocket()
	{
		for(String username:JQServer.clientSocketLists.keySet())
		{
			if(JQServer.clientSocketLists.get(username) == connection)
			{
				JQServer.clientSocketLists.remove(username);
				break;
			}
		}
	}
}
