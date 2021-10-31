package com.acmebank.domain;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class Transfer {
	public Transfer(int fromAccountNumber, int toAccountNumber, double amount) {
		super();
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
		this.amount = amount;
	}
	//@Min(value=1,message="The withdraw Account Number is invalid") 
	public int fromAccountNumber;
	//@Min(value=1,message="The credit Account Number is invalid") 
	public int toAccountNumber;
	//@DecimalMin(value = "0.01",message = "Transfer amount must be greater than zero") 
	public double amount;
}
	