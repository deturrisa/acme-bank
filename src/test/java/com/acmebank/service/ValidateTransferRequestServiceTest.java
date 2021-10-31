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
	public void whenExceptionThrown_thenAssertionSucceeds() {
		
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
}
