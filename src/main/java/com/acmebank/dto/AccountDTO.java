package com.acmebank.dto;

import com.acmebank.domain.Account;

import java.util.UUID;

public class AccountDTO {

  private final int accountNumber;
  private final long balance;


  public AccountDTO(int accountNumber,
		  			long balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
  }

 

  public int getAccountNumber() {
	return accountNumber;
  }



  public long getBalance() {
	return balance;
  }



public static AccountDTO toAccountDTO(Account account) {
    return new AccountDTO(
        account.getAccountNumber(),
        account.getBalance());
  }
}
