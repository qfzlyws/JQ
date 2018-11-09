package com.jq.client.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteAccounts {
	
	public static void saveAccountFile(Account account)
	{
		try {
			ReadAccounts.accounts.add(account);
			
			ObjectOutputStream objOS = new ObjectOutputStream(new FileOutputStream(ReadAccounts.accountsFile));
			objOS.writeObject(ReadAccounts.accounts);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
