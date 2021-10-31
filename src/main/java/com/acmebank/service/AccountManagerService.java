package com.acmebank.service;


import java.util.List;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acmebank.domain.Account;
import com.acmebank.domain.Transfer;
import com.acmebank.repository.AccountRepository;

import javassist.NotFoundException;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountManagerService {

  private final AccountRepository accountRepository;
  
  private ValidateTransferRequestService validateTransferRequestService;

  @Autowired
  public AccountManagerService(AccountRepository accountRepository, ValidateTransferRequestService validateTransferRequestService) {
    this.accountRepository = accountRepository;
    this.validateTransferRequestService = validateTransferRequestService;
  }

  @Transactional
  public List<Account> transfer(Transfer transfer) throws NotFoundException
  {
	  validateTransferRequestService.validate(transfer);
	  
	  Account fromAccount = getBalance(transfer.fromAccountNumber);
	  Account toAccount = getBalance(transfer.toAccountNumber);
	 
	  double newFromAccountBalance = fromAccount.getBalance()-transfer.amount;
	  
	  
	  if(newFromAccountBalance < 0) 
	  {
		  throw new ResponseStatusException(
   	           HttpStatus.BAD_REQUEST, "Insufficient funds for transfer");
	  }
	  
	  //TODO: @Transactional
	  fromAccount.setBalance(newFromAccountBalance);
	  toAccount.setBalance(toAccount.getBalance()+transfer.amount);
	  
	  accountRepository.save(fromAccount);
	  accountRepository.save(toAccount);
      
	  return getAccounts();
  }
  
  public Account createAccount(int accountNumber, long balance, String currency) {
    return accountRepository.save(
        new Account(accountNumber, balance,currency));
  }

  public List<Account> getAccounts() {
    return StreamSupport.stream(
        accountRepository
            .findAll()
            .spliterator(), false)
        .collect(Collectors.toList());
  }

  public Account getBalance(int accountNumber) throws NotFoundException {
	  
    Account account = accountRepository.findByAccountNumber(accountNumber);
    
    if(account == null) 
    {
    	throw new ResponseStatusException(
    	           HttpStatus.NOT_FOUND, "No details found for account number: "+ accountNumber);
    }
    
    return account;
  }
  
  //This could go in a custom @Valid implementation
  private void checkEqualAccountNumbers(Transfer transfer) 
  {
	  if(transfer.toAccountNumber == transfer.fromAccountNumber) 
	  {
		  throw new ResponseStatusException(
	   	           HttpStatus.BAD_REQUEST, "Cannot transfer to same account"); 
	  } 
  }
}
