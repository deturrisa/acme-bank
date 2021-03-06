package com.acmebank.controller;

import com.acmebank.domain.Account;
import com.acmebank.domain.Transfer;
import com.acmebank.dto.AccountDTO;
import com.acmebank.service.AccountManagerService;
import com.acmebank.views.View;
import com.fasterxml.jackson.annotation.JsonView;

import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import static com.acmebank.dto.AccountDTO.toAccountDTO;

@RestController
@Validated
public class AccountController {

  private final AccountManagerService accountManagerService;

  @Autowired
  public AccountController(AccountManagerService accountManagerService) {
    this.accountManagerService = accountManagerService;
  }

  @PostMapping(value = "/accounts")
  @ResponseBody
  public AccountDTO createAccount(int accountNumber,
                                    long balance, String currency) {
    Account account = accountManagerService
        .createAccount(accountNumber,balance,currency);
    return toAccountDTO(account);
  }
    
  //should put account number in post request but for simplicity added to get
  @JsonView(View.BalanceView.class) 
  @GetMapping(value = "/accounts", params = "accountNumber")
  @ResponseBody
  public AccountDTO getBalance(@RequestParam("accountNumber") int accountNumber) throws ResponseStatusException {
    Account account = accountManagerService.getBalance(accountNumber);
    return toAccountDTO(account);
  }
  
  @JsonView(View.BalanceViewWithNumber.class)
  @PutMapping("/accounts/transfer")
  @ResponseBody
  public List<AccountDTO> transfer(
@Valid@RequestBody Transfer transfer) throws ResponseStatusException{
	  List<AccountDTO> accounts = accountManagerService.transfer(transfer)
      .stream()
      .map(AccountDTO::toAccountDTO)
      .collect(Collectors.toList());
      return accounts;
 }
}
