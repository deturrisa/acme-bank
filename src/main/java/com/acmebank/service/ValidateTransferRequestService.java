package com.acmebank.service;

import org.springframework.http.HttpStatus;
import com.acmebank.domain.Transfer;

import org.springframework.web.server.ResponseStatusException;
//This could be done with custom annotations but since scope is not big put in service
public class ValidateTransferRequestService {

	
	public void validate(Transfer transfer) {
	
		if(transfer.fromAccountNumber <= 0 || transfer.toAccountNumber <= 0 ) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The withdraw Account Number is invalid");
		}
		
		if(transfer.amount <= 0) 
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Transfer amount must be greater than zero");
		}
		
		if(transfer.toAccountNumber == transfer.fromAccountNumber) 
		{
			throw new ResponseStatusException(
		   	           HttpStatus.BAD_REQUEST, "Cannot transfer to same account"); 
		} 
	}
	
	
}
