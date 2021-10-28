package com.acmebank.domain;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class Transfer {
	@Min(1) 
	public int fromAccountNumber;
	@Min(1) 
	public int toAccountNumber;
	@DecimalMin("0.01") 
	public double amount;
}
	