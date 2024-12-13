package com.jq.client.view;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class LogonAccountLists extends JComboBox<Object> {
	
	public LogonAccountLists()
	{
		super();
	}
	
	public LogonAccountLists(Object[] items) {
		super(items);
		
		LogonAccountCell perInfo = new LogonAccountCell();

		this.setRenderer(perInfo);
	}
}
