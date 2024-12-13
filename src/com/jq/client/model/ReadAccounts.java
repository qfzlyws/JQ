package com.jq.client.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadAccounts {
	private static Logger loger = LogManager.getLogger(ReadAccounts.class);
	public static List<Account> accounts = null;
	public static File accountsFile = null;
	
	static
	{
		accountsFile = new File(JQMain.APPPATH + "/resources/Accounts");
	}
	
	@SuppressWarnings("unchecked")
	public static List<Account> readAccounts()
	{			
		if(accountsFile.exists() && accountsFile.length() > 0)
		{
			try(ObjectInputStream objINS = new ObjectInputStream(new FileInputStream(accountsFile));) { 
				accounts = (ArrayList<Account>)objINS.readObject();
			} catch (IOException | ClassNotFoundException e) {
				loger.error("",e);
			}
		}
		
		if(accounts == null)
			accounts = new ArrayList<>();
		
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
				accountsArr[i] = accounts.get(i).getAccountName();
			}
		}
		
		return accountsArr;
	}
}
