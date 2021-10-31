package com.acmebank.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.acmebank.domain.Account;
import com.acmebank.domain.Transfer;
import com.acmebank.repository.AccountRepository;

import javassist.NotFoundException;

public class AccountManagerServiceTest {
	
	@Test
	public void getBalance_whenReturnsNull_thenThrowResponseStatusException() 
	{
		//Arrange 
		int accountNumber = 12345678;
		
		AccountRepository mockedAccountRepository = mock(AccountRepository.class);
		ValidateTransferRequestService mockedValidateTransferRequestService = mock(ValidateTransferRequestService.class);
		AccountManagerService sut = new AccountManagerService(mockedAccountRepository,mockedValidateTransferRequestService);
    
		when(mockedAccountRepository.findByAccountNumber(accountNumber)).thenReturn(null);
	
		//Act
		//Assert
		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
	    	sut.getBalance(accountNumber);
	    });
	    
	    assertEquals(HttpStatus.NOT_FOUND , exception.getStatus());
	}
	
	@Test
	public void getBalance_whenMockReturnsAccount_thenReturnAccount() throws ResponseStatusException 
	{
		//Arrange 
		
		Account account = new Account(12345678,1000000.00,"HKD");
		
		AccountRepository mockedAccountRepository = mock(AccountRepository.class);
		ValidateTransferRequestService mockedValidateTransferRequestService = mock(ValidateTransferRequestService.class);
		AccountManagerService sut = new AccountManagerService(mockedAccountRepository,mockedValidateTransferRequestService);
        
		when(mockedAccountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(account);
	
		//Act
		Account result = sut.getBalance(account.getAccountNumber());
		
		//Assert
	    assertEquals(account , result);
	}
	
	@Test
	public void transfer_whenInsufficientFunds_thenThrowResponseStatusExceptiont() throws ResponseStatusException 
	{
		//Arrange 
		
		Account account = new Account(12345678,1000000.00,"HKD");
		Transfer transfer = new Transfer(12345678,8888888,account.getBalance()+1);
		
		AccountRepository mockedAccountRepository = mock(AccountRepository.class);
		ValidateTransferRequestService mockedValidateTransferRequestService = mock(ValidateTransferRequestService.class);
		AccountManagerService sut = new AccountManagerService(mockedAccountRepository,mockedValidateTransferRequestService);
        
		when(mockedAccountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(account);
	
		//Act
		//Assert
		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
	    	sut.transfer(transfer);
	    });
	    
	    assertEquals(HttpStatus.BAD_REQUEST , exception.getStatus());		
	}
	
}
