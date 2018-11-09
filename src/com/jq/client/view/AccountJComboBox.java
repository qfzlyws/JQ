package com.jq.client.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class AccountJComboBox extends JComboBox {
	
	public AccountJComboBox()
	{
		super();
	}
	
	@SuppressWarnings("unchecked")
	public AccountJComboBox(Object[] items) {
		super(items);
		
		LoginedUsersListCell perInfo = new LoginedUsersListCell();

		this.setRenderer(perInfo);
	}
}
