package com.acmebank.domain;

import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

	  @Id
	  private final int accountNumber;
	  
	  private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");
	  
	  private final double balance;

	  public Account() {
		this.accountNumber = 0;
		this.balance = 0;

	  }
	  
	  public Account(int accountNumber, double balance) {
	    this.accountNumber = accountNumber;
	    this.balance = Double.parseDouble(decimalFormat.format(balance));;
	  }

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return Double.parseDouble(decimalFormat.format(balance));
	}

	
}
