package com.acmebank.service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.acmebank.domain.Account;
import com.acmebank.domain.Transfer;
import com.acmebank.repository.AccountRepository;

import javassist.NotFoundException;
import org.springframework.web.server.ResponseStatusException;
@Service
public class AccountService {

  private final AccountRepository accountRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

//TODO validate transfer object
  public List<Account> transfer(Transfer transfer) throws NotFoundException
  {
	
	  Account fromAccount = getByAccountNumber(transfer.fromAccountNumber);
	  Account toAccount = getByAccountNumber(transfer.toAccountNumber);
	 
	  double newFromAccountBalance = fromAccount.getBalance()-transfer.amount;
	  
	  
	  if(newFromAccountBalance < 0) 
	  {
		  throw new UnsupportedOperationException("Insufficient funds forn transaction");
	  }
	  
	  //TODO: @Transactional
	  fromAccount.setBalance(newFromAccountBalance);
	  toAccount.setBalance(toAccount.getBalance()+transfer.amount);
	  
	  accountRepository.save(fromAccount);
	  accountRepository.save(toAccount);
      
	  return getAccounts();
  }
  
  public Account createAccount(int accountNumber, long balance) {
    return accountRepository.save(
        new Account(accountNumber, balance));
  }

  public List<Account> getAccounts() {
    return StreamSupport.stream(
        accountRepository
            .findAll()
            .spliterator(), false)
        .collect(Collectors.toList());
  }

  public Account getByAccountNumber(int accountNumber) throws NotFoundException {
	  
    Account account = accountRepository.findByAccountNumber(accountNumber);
    
    if(account == null) 
    {
    	throw new ResponseStatusException(
    	           HttpStatus.NOT_FOUND, "No details found for account number: "+ accountNumber);
    }
    
    return account;
  }
}
