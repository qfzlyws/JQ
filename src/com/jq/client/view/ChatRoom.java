package com.jq.client.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.jq.ConnectionManager;
import com.jq.utilities.GUITools;

public class ChatRoom extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private ConnectionManager connManager;
	private String friendAccount = null;
	private JTextArea displayMessages = null;
	private JTextField inputField = null;
	private JButton send = null;
	private JPanel sourthPanel = null;
	private JSplitPane splitPane = null;
	private JList<JLabel> messageLists = null;
	private DefaultListModel<JLabel> messageModel = null;
	
	public ChatRoom(ConnectionManager connManager,String friendAccount)
	{
		super(friendAccount);
		
		this.connManager = connManager;
		this.friendAccount = friendAccount;
		
		messageModel = new DefaultListModel<>();
		messageLists = new JList<>(messageModel);
		messageLists.setCellRenderer(new ChatMessageCell());
		
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
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JScrollPane(messageLists),new JScrollPane(sourthPanel));
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(310);
		splitPane.setDividerSize(7);
		
		this.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		send.addActionListener(this);
		
		this.setSize(400, 400);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(true);
		GUITools.setFrameCenter(this);
	}
	
	public void setDisplayMessages(String from,final String messageToDisplay) {
		// display message from event-dispatch thread of execution
		JLabel messageLabel = new JLabel();
		messageLabel.setText(messageToDisplay);
		
		if(from.equals(connManager.getAccount().getAccountName()))
			messageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		else
			messageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		messageModel.addElement(messageLabel);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String message = inputField.getText();
		
		if(!message.equals(""))
		{
			connManager.sendMessage(connManager.getAccount().getAccountName(), friendAccount, message);
			setDisplayMessages(connManager.getAccount().getAccountName(),connManager.getAccount().getAccountName() + ":" + message);
			inputField.setText("");
		}
	}
	
	
}
