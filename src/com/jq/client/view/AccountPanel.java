package com.jq.client.view;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccountPanel extends JPanel {
	private JTextField accountInput = null;
	private JComboBox accountInputList = null;
	
	public AccountPanel()
	{
		super();
		
		accountInput = new JTextField();
		accountInputList = new JComboBox();
		
		accountInput.setColumns(5);;
		
		this.add(accountInput);
		this.add(accountInputList);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(30,80);
	}
	
}
