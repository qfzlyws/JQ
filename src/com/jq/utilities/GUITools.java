package com.jq.utilities;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GUITools {
	/**
	 * 設置JFrame位于屏幕中央
	 * @param frame
	 */
	public static void setFrameCenter(JFrame frame)
	{
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width/2; // 获取屏幕的宽
		int screenHeight = screenSize.height/2; // 获取屏幕的高
		int height = frame.getHeight();
		int width = frame.getWidth();
		frame.setLocation(screenWidth-width/2, screenHeight-height/2);
	}
}
