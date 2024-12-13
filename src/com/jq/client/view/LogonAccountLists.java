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
		
		LogonAccountListsCell perInfo = new LogonAccountListsCell();

		this.setRenderer(perInfo);
	}
}
