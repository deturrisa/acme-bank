package com.acmebank.controller;

import com.acmebank.domain.Account;
import com.acmebank.dto.AccountDTO;
import com.acmebank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.acmebank.dto.AccountDTO.toAccountDTO;

@RestController
public class AccountController {

  private final AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping(value = "/accounts")
  @ResponseBody
  public AccountDTO createAccount(int accountNumber,
                                    long balance) {
    Account account = accountService
        .createAccount(accountNumber,balance);
    return toAccountDTO(account);
  }
  
  @GetMapping(value = "/accounts")
  @ResponseBody
  public List<AccountDTO> getAllAccounts() {
    return accountService.getAccounts()
        .stream()
        .map(AccountDTO::toAccountDTO)
        .collect(Collectors.toList());
  }

  @GetMapping(value = "/accounts", params = "accountNumber")
  @ResponseBody
  public AccountDTO getByAccountNumber(@RequestParam("accountNumber") int accountNumber) {
    Account account = accountService.getByAccountNumber(accountNumber);
    return toAccountDTO(account);
  }
}
