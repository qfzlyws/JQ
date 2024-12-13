package com.jq.client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class FriendCell extends JPanel implements ListCellRenderer<Object> {
	private ImageIcon icon;
	private String account;
	private Color backgroundColor;
	private Color foregroundColor;
	
	public FriendCell()
	{
		super();
		this.setOpaque(false);
		
		Border borderEmpty = BorderFactory.createEmptyBorder(1,	1, 1, 1);
		
		this.setBorder(borderEmpty);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		icon = new ImageIcon(this.getClass().getResource("images/qq_3d.png"));
		account = value.toString();
		
		backgroundColor = isSelected ? list.getSelectionBackground():list.getBackground();
		
		foregroundColor = isSelected ? list.getSelectionForeground():list.getForeground();
		
		return this;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(foregroundColor);
		g.drawImage(icon.getImage(),0,0,40,40,this);
		g.drawString(account, 41, 20);
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(40,40);
	}
	
	public String getAccount()
	{
		return account;
	}
}
