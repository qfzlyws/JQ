package com.jq.client.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import com.jq.JQConstants;
import com.jq.MessageListener;

public class ReceiveMessageThread extends Thread {
	private BufferedReader br;
	
	// MesssageListener to whom messages should be delviered
	private MessageListener messageListener;

	// flag for terminating PacketReceivingThread
	private boolean keepListening = true;
	
	private String receivedMess;

	// PacketReceivingThread constructor
	public ReceiveMessageThread(BufferedReader br,MessageListener listener)
	{
		// invoke superclass constructor to name Thread
		super("ReceiveMessageThread");
		
		// set MessageListener
		messageListener = listener;
		
		// set bufferedreader for client socket
		this.br = br;
	}

	// listen for messages from client socket
	public void run() {
		// listen for messages until stopped
		while (keepListening) {
			
			try {
				receivedMess = br.readLine();
				
				System.out.println("ReceiveMessageThread:" + receivedMess);
			}

			// handle exception when receive times out
			catch (InterruptedIOException interruptedIOExeption) {
				// continue to next iteration to keep listening
				continue;
			} catch (IOException ioException) {
				ioException.printStackTrace();
				break;
			}
			
			// trim extra whitespace from end of message
			receivedMess = receivedMess.trim();

			// tokenize message to retrieve user name and message body
			StringTokenizer tokenizer = new StringTokenizer(receivedMess, JQConstants.MESSAGE_SEPARATOR);

			// ignore messsages that do not contain a user
			// name and message body
			if (tokenizer.countTokens() == 2)
				// send message to MessageListener
				messageListener.messageReceived(tokenizer.nextToken(), "",tokenizer.nextToken());
		} // end while

	} // end method run

	// stop listening for new Messages
	public void stopListening() {
		keepListening = false;
	}
}
