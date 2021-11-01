package com.acmebank.service;


import java.util.Arrays;
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
  public List<Account> transfer(Transfer transfer) throws ResponseStatusException
  {
	  validateTransferRequestService.validate(transfer);
	  
	  Account fromAccount = getBalance(transfer.fromAccountNumber);
	  
	  double newFromAccountBalance = fromAccount.getBalance()-transfer.amount;
	  
	  
	  if(newFromAccountBalance < 0) 
	  {
		  throw new ResponseStatusException(
   	           HttpStatus.BAD_REQUEST, "Insufficient funds for transfer");
	  }
	  
	  //can withdraw funds now deposit funds into toAccount
	  
	  Account toAccount = getBalance(transfer.toAccountNumber);
	  
	  double newToAccountBalance = toAccount.getBalance()+transfer.amount;
	  
	  fromAccount.setBalance(newFromAccountBalance);
	  toAccount.setBalance(newToAccountBalance);
	  
	  accountRepository.save(fromAccount);
	  accountRepository.save(toAccount);
	  
	  return Arrays.asList(fromAccount, toAccount);

  }
  
  public Account createAccount(int accountNumber, double balance, String currency) {
    return accountRepository.save(
        new Account(accountNumber, balance,currency));
  }

  public Account getBalance(int accountNumber) throws ResponseStatusException {
	  
    Account account = accountRepository.findByAccountNumber(accountNumber);
    
    if(account == null) 
    {
    	throw new ResponseStatusException(
    	           HttpStatus.NOT_FOUND, "No details found for account number: "+ accountNumber);
    }
    
    return account;
  }
  
}
