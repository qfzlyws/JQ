package com.jq.client.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.jq.ConnectionManager;
import com.jq.utilities.GUITools;

public class ChatRoom extends JFrame implements ActionListener {
	private ConnectionManager connManager;
	private String friendAccount = null;
	private JTextArea displayMessages = null;
	private JTextField inputField = null;
	private JButton send = null;
	private JPanel sourthPanel = null;
	private JSplitPane splitPane = null;
	
	public ChatRoom(ConnectionManager connManager,String friendAccount)
	{
		super(friendAccount);
		
		this.connManager = connManager;
		this.friendAccount = friendAccount;
		
		displayMessages = new JTextArea(15,10);
		displayMessages.setEditable(false);
		inputField = new JTextField();
		inputField.setColumns(28);
		send = new JButton("發送");
		sourthPanel = new JPanel();
		
		BorderLayout layout = new BorderLayout();
		sourthPanel.setLayout(layout);
		
		sourthPanel.add(inputField,BorderLayout.CENTER);
		sourthPanel.add(send,BorderLayout.EAST);
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JScrollPane(displayMessages),new JScrollPane(sourthPanel));
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(310);
		splitPane.setDividerSize(7);
		
		this.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		send.addActionListener(this);
		
		this.setSize(400, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		GUITools.setFrameCenter(this);
	}
	
	public void setDisplayMessages(final String messageToDisplay) {
		// display message from event-dispatch thread of execution
		SwingUtilities.invokeLater(new Runnable() { // inner class to ensure GUI
													// updates properly
			public void run() {
				displayMessages.append(messageToDisplay + "\n");
				//displayMessages.setText(messageToDisplay);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String message = inputField.getText();
		
		if(!message.equals("") && message != null)
		{
			JOptionPane.showMessageDialog(this, message);
			connManager.sendMessage(connManager.getAccount().getAccount(), friendAccount, message);
			setDisplayMessages(connManager.getAccount().getAccount() + ":" + message);
			inputField.setText("");
		}
	}
}
