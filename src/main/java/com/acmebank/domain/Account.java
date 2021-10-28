package com.acmebank.domain;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

	  @Id
	  private final int accountNumber;
	  
	  private final double balance;

	  public Account() {
		this.accountNumber = 0;
		this.balance = 0;

	  }
	  
	  public Account(int accountNumber, double balance) {
		DecimalFormat decim = new DecimalFormat("0.00");
	    this.accountNumber = accountNumber;
	    this.balance = Double.parseDouble(decim.format(balance));;
	  }

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	
}
