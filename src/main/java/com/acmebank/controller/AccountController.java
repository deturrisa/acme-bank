package com.acmebank.controller;

import com.acmebank.domain.Account;
import com.acmebank.domain.Transfer;
import com.acmebank.dto.AccountDTO;
import com.acmebank.service.AccountService;

import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import static com.acmebank.dto.AccountDTO.toAccountDTO;

@RestController
@Validated
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
  public AccountDTO getByAccountNumber(@RequestParam("accountNumber") int accountNumber) throws NotFoundException {
    Account account = accountService.getByAccountNumber(accountNumber);
    return toAccountDTO(account);
  }
  
  @PutMapping("/accounts/transfer")
  @ResponseBody
  public ResponseEntity<List<AccountDTO>> transfer(
@Valid@RequestBody Transfer transfer) throws NotFoundException{
	  List<AccountDTO> accounts = accountService.transfer(transfer)
      .stream()
      .map(AccountDTO::toAccountDTO)
      .collect(Collectors.toList());
      return ResponseEntity.ok(accounts);
 }
}
