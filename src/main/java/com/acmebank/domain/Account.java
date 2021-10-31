package com.acmebank.domain;

import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

	  @Id
	  private final int accountNumber;
	  
	  private double balance;
	  
	  private String currency;

	  
	 public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Account() {
		this.accountNumber = 0;
		this.balance = 0;
		this.currency = "HKD";

	 }
	  
	 public Account(int accountNumber, double balance, String currency) {
	   this.accountNumber = accountNumber;
	   this.balance = balance;
	   this.currency = currency;
	 }

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
