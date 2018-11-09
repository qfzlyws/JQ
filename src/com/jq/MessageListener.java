// MessageLIstener.java
// MessageListener is an interface for classes that wish to
// receive new chat messages.
package com.jq;

public interface MessageListener {
	
	// receeive new chat message
	public void messageReceived(String from,String to,String message);
}
