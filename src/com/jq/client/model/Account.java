package com.jq.client.model;

import java.io.Serializable;

public class Account implements Serializable{	
	private static final long serialVersionUID = 1L;
	private String accountName = null;
	private String password = null;
	private String profilePic = null;

	public Account(String accountName,String password)
	{
		this.accountName = accountName;
		this.password = password;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
