package com.jq.client.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteAccounts {

	private WriteAccounts() {
		
	}
	public static void saveAccountFile(Account account) {
		try (ObjectOutputStream objOS = new ObjectOutputStream(new FileOutputStream(ReadAccounts.accountsFile));) {
			ReadAccounts.accounts.add(account);
			objOS.writeObject(ReadAccounts.accounts);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
