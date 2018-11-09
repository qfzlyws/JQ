package com.jq.client.model;

import java.io.Serializable;

public class Account implements Serializable{	
	/**
	 * 
	 */
	
	private String account = null;
	private String password = null;
	private String profilePic = null;

	public Account(String account,String password)
	{
		this.account = account;
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	
}
