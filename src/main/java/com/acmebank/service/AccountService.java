package com.acmebank.service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acmebank.domain.Account;
import com.acmebank.repository.AccountRepository;

@Service
public class AccountService {

  private final AccountRepository accountRepository;

  @Autowired
  public AccountService(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
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

  public Account getByAccountNumber(int accountNumber) {
    return accountRepository.findByAccountNumber(accountNumber);
  }
}
