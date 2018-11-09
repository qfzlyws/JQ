package com.jq.client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import com.jq.client.model.JQMain;

public class LoginedUsersListCell extends JPanel implements ListCellRenderer {
	private ImageIcon icon;
	private String account;
	private Color background;
	private Color foreground;
	
	public LoginedUsersListCell()
	{
		super();
		this.setOpaque(false);
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		icon = new ImageIcon(this.getClass().getResource("images/qq_3d.png"));
		account = value.toString();
		
		background = isSelected ? list.getSelectionBackground():list.getBackground();
		
		foreground = isSelected ? list.getSelectionForeground():list.getForeground();
		
		return this;
	}
	
	public void paintComponent(Graphics g)
	{
		int imageWidth = icon.getImage().getWidth(null);
		int imageHeight = icon.getImage().getHeight(null);
		g.setColor(background);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(foreground);
		g.drawImage(icon.getImage(),0,0,30,30,this);
		g.drawString(account, 31, 30);
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(30,30);
	}

}
