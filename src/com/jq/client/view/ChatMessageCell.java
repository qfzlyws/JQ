package com.jq.client.view;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ChatMessageCell implements ListCellRenderer<JLabel> {

	@Override
	public Component getListCellRendererComponent(JList<? extends JLabel> list, JLabel value, int index,
			boolean isSelected, boolean cellHasFocus) {
		return value;
	}
}