package com.acmebank.dto;

import com.acmebank.domain.Account;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountDTO {

  private final int accountNumber;
  private final double balance;


  public AccountDTO(int accountNumber,
		  double balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
  }

 

  public int getAccountNumber() {
	return accountNumber;
  }



  public double getBalance() {
	return balance;
  }



public static AccountDTO toAccountDTO(Account account) {
    return new AccountDTO(
        account.getAccountNumber(),
        account.getBalance());
  }
}
