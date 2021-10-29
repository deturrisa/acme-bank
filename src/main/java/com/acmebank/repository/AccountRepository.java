package com.acmebank.repository;

import com.acmebank.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository
    extends CrudRepository<Account, Double> {

  Account findByAccountNumber(int accountNumber);

  void save(double amount);
  
}
