package com.jq.client.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

@SuppressWarnings("serial")
public class LoginFrameBackground extends JPanel {
	private Image backGroundIcon = null;
	
	public LoginFrameBackground(Image icon)
	{
		this.backGroundIcon = icon;
		this.setOpaque(true);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(backGroundIcon, 0, 0, this);
	}
}