package com.jq.client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

@SuppressWarnings("serial")
public class LogonAccountCell extends JPanel implements ListCellRenderer<Object> {
	private ImageIcon icon;
	private String account;
	private Color background;
	private Color foreground;
	
	public LogonAccountCell()
	{
		super();
		this.setOpaque(false);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		
		icon = new ImageIcon(this.getClass().getResource("images/qq_3d.png"));
		account = value.toString();
		
		background = isSelected ? list.getSelectionBackground():list.getBackground();
		
		foreground = isSelected ? list.getSelectionForeground():list.getForeground();
		
		return this;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(background);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(foreground);
		g.drawImage(icon.getImage(),0,0,30,30,this);
		g.drawString(account, 31, 30);
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(30,30);
	}

}
