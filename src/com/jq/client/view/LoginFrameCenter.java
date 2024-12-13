package com.jq.client.view;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class LoginFrameCenter extends JPanel {
	private GridBagConstraints constraints;
	private GridBagLayout layout;
	private JLabel logo;
	private JLabelBeClicked registerJL,forgetJL;
	private LogonAccountLists accountJCB;
	private JPasswordField passwordJPF;
	private JPanel panelBottom;
	private JCheckBox savePassJCB,autoLoginJCB;
	private FlowLayout panelBottomLayout = null;
	private JLabel dispalyMessageJL = null;
	private String[] accounts = null;
	
	public LoginFrameCenter()
	{
		super();
		
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		this.setLayout(layout);
		this.setOpaque(false);
		
		ImageIcon leftLogo = new ImageIcon(this.getClass().getResource("images/JQLogo.png"));
		
		logo = new JLabel(leftLogo);
		
		registerJL = new JLabelBeClicked("注冊賬號");
		forgetJL = new JLabelBeClicked("忘記密碼");
		
		if(accounts == null)
			accountJCB = new LogonAccountLists();
		else
			accountJCB = new LogonAccountLists(accounts);
		
		accountJCB.setEditable(true);
		
		passwordJPF = new JPasswordField();
		savePassJCB = new JCheckBox("記住密碼");
		savePassJCB.setOpaque(false);
		autoLoginJCB = new JCheckBox("自動登錄");
		autoLoginJCB.setOpaque(false);
		
		Insets insert1 = new Insets(0,0,5,5);
		Insets insert2 = new Insets(0,0,0,0);
		
		constraints.fill = GridBagConstraints.BOTH;		
		addComponent(logo,0,0,1,3);
		
		constraints.weightx = 80;
		constraints.weighty = 0;
		constraints.insets = insert1;
		addComponent(accountJCB,0,1,1,1);
		
		constraints.weightx = 20;
		constraints.weighty = 0;		
		addComponent(registerJL,0,2,1,1);
		
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.ipady = 4;
		constraints.insets = insert1;
		addComponent(passwordJPF,1,1,1,1);
		
		constraints.weightx = 0;
		constraints.weighty = 0;		
		constraints.insets = insert2;
		addComponent(forgetJL,1,2,1,1);
		
		panelBottomLayout = new FlowLayout(FlowLayout.LEFT);
		panelBottom = new JPanel(panelBottomLayout);		
		panelBottom.setOpaque(false);
		panelBottom.add(savePassJCB);
		panelBottom.add(autoLoginJCB);
		addComponent(panelBottom,2,1,2,1);
		
		dispalyMessageJL = new JLabel(" ");
		dispalyMessageJL.setForeground(Color.RED);
		dispalyMessageJL.setHorizontalAlignment(JLabel.CENTER);
		
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.ipady = 0;
		constraints.insets = insert2;
		addComponent(dispalyMessageJL,3,0,3,1);
		
	}
	
	public void setDisplayMessages(final String messageToDisplay) {
		// display message from event-dispatch thread of execution
		SwingUtilities.invokeLater(new Runnable() { // inner class to ensure GUI
													// updates properly
			public void run() {
				dispalyMessageJL.setText(messageToDisplay);
			}
		});
	}
	
	private void addComponent(Component component,int row,int column,int width,int height)
	{
		constraints.gridx = column;
		constraints.gridy = row;
		
		constraints.gridwidth = width;
		constraints.gridheight = height;
		
		layout.setConstraints(component, constraints);
		add(component);
	}

	public String getAccount()
	{
		if(accountJCB.getSelectedIndex() == -1)
			return (String)accountJCB.getSelectedItem();
		else
			return (String)accountJCB.getSelectedItem();
	}
	
	public String getPassword()
	{
		return String.valueOf(passwordJPF.getPassword());
	}
	
	public boolean isSavePassChecked()
	{
		return savePassJCB.isSelected();
	}
	
	public boolean isAutoLoginChecked()
	{
		return autoLoginJCB.isSelected();
	}
}
