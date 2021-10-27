package com.acmebank.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

	  @Id
	  private final int accountNumber;
	  
	  private final long balance;

	  public Account() {
		this.accountNumber = 0;
		this.balance = 0;
	  }
	  
	  public Account(int accountNumber, long balance) {
	    this.accountNumber = accountNumber;
	    this.balance = balance;
	  }

	public int getAccountNumber() {
		return accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	
}
