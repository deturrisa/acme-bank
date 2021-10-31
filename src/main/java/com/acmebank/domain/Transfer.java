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

	public int fromAccountNumber;
	public int toAccountNumber;
	public double amount;
}
	