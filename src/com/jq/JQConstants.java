package com.jq;

public interface JQConstants {	
	public static final String SERVER_ADDRESS = "172.24.12.168";
	
	// port for Socket connections to DeitelMessengerServer
	public static final int SERVER_PORT = 12345;
	
	// String that indicates disconnect
	public static final String DISCONNECT_STRING = "DISCONNECT";
	
	// String that separates the user name from the message body
	public static final String MESSAGE_SEPARATOR = ">>>";
	
	// String that indicates logining
	public static final String LOGIN_FLAG = "##LOGINING##";
	
	// String that indicates error
	public static final String ERROR_FLAG = "##ERROR##";
	
	// String that indicates sucess
	public static final String SUCESS_FLAG = "##SUCESS##";
	
	// String that indicates search online users
	public static final String WHOISONLINE = "##WHOISONLINE##";
	
	// String that indicates new online user
	public static final String NEWONLINEUSER = "##NEWONLINEUSER##";
}
