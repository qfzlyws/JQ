package com.jq.client.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadAccounts {
	public static ArrayList<Account> accounts = null;
	public static File accountsFile = null;
	
	static
	{
		accountsFile = new File(JQMain.appPath + "/resources/Accounts");
	}
	
	public static ArrayList<Account> readAccounts()
	{	
		ObjectInputStream objINS = null;
		
		if(accountsFile.exists() && accountsFile.length() > 0)
		{
			try {
				objINS = new ObjectInputStream(new FileInputStream(accountsFile));
				accounts = (ArrayList<Account>)objINS.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally
			{
				try {
					objINS.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(accounts == null)
			accounts = new ArrayList<Account>();
		
		return accounts;
	}
	
	public static String[] getAccountArray()
	{
		String[] accountsArr = null;
		
		if(!accounts.isEmpty())
		{
			accountsArr = new String[accounts.size()];
							
			for(int i = 0; i < accountsArr.length;i++)
			{
				accountsArr[i] = accounts.get(i).getAccount();
			}
			
		}
		
		return accountsArr;
	}
}
