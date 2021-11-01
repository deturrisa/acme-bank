package com.acmebank.dto;

import com.acmebank.domain.Account;
import com.acmebank.views.View;
import com.fasterxml.jackson.annotation.JsonView;


public class AccountDTO {

  @JsonView(View.BalanceViewWithNumber.class)
  private final int accountNumber;
  @JsonView(View.BalanceView.class)
  private final double balance;
  @JsonView(View.BalanceView.class)
  private String currency;

  public AccountDTO(int accountNumber,
		  double balance, String currency) {
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.currency = currency;
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
        account.getBalance(),
        account.getCurrency());
  }



public String getCurrency() {
	return currency;
}

public void setCurrency(String currency) {
	this.currency = currency;
}

}
