package com.acmebank.domain;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class Transfer {
	@Min(value=1,message="The withdraw Account Number is invalid") 
	public int fromAccountNumber;
	@Min(value=1,message="The credit Account Number is invalid") 
	public int toAccountNumber;
	@DecimalMin(value = "0.01",message = "Transfer amount must be greater than zero") 
	public double amount;
}
	