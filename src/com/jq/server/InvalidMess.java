package com.jq.server;

public class InvalidMess extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidMess(String mess)
	{
		super(mess);
	}
}
