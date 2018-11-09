package com.jq.server;

public class MessObj {
	private String prefix;
	private String message;
	
	public MessObj(String content) throws InvalidMess
	{
		if(!isLegalMess(content))
			throw new InvalidMess("無效的輸入信息！");
		
		
		this.prefix = content.substring(0, content.indexOf(":"));
		this.message = content.substring(content.indexOf(":") + 1);
		
	}
	
	public String getPrefix()
	{
		return prefix;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	/**
	 * 判斷客戶端輸入的是否為合法信息
	 * @param msg
	 * @return
	 */
	private boolean isLegalMess(String msg)
	{
		if(msg.toUpperCase().startsWith("USERNAME:"))
		{
			if(msg.matches("[uU][sS][eE][rR][nN][aA][mM][eE]:(\\S+)"))
			{
				//登錄信息
				return Boolean.TRUE;
			}
			else
			{
				return Boolean.FALSE;
			}
		}
		
		return Boolean.TRUE;
	}
	
	
}
