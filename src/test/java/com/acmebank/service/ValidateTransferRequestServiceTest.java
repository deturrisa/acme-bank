package com.acmebank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.acmebank.domain.Transfer;

public class ValidateTransferRequestServiceTest {

	@Test
	public void whenFromAccountNumberIsInvalid_thenThrowResponseStatusException() {
		
		//Arrange
		
		ValidateTransferRequestService sut = new ValidateTransferRequestService();
		Transfer transfer = new Transfer(0,12345678,500000.0);
		//Act
		
		//Assert
		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
	    	sut.validate(transfer);
	    });
	    
	    assertEquals(HttpStatus.BAD_REQUEST , exception.getStatus());
	}
	
	@Test
	public void whenToAccountNumberIsInvalid_thenThrowResponseStatusException() {
		
		//Arrange
		
		ValidateTransferRequestService sut = new ValidateTransferRequestService();
		Transfer transfer = new Transfer(12345678,0,500000.0);
		//Act
		
		//Assert
		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
	    	sut.validate(transfer);
	    });
	    
	    assertEquals(HttpStatus.BAD_REQUEST , exception.getStatus());
	}
	
	@Test
	public void whenAccountsAreEqual_thenThrowResponseStatusException() {
		
		//Arrange
		
		ValidateTransferRequestService sut = new ValidateTransferRequestService();
		Transfer transfer = new Transfer(12345678,12345678,500000);
		//Act
		
		//Assert
		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
	    	sut.validate(transfer);
	    });
	    
	    assertEquals(HttpStatus.BAD_REQUEST , exception.getStatus());
	}
}
