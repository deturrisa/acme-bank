package com.acmebank.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.acmebank.domain.Account;

import junit.framework.Assert;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class AccountRepositoryTest {

	public static final String ACME_BANK = "AcmeBank";

	  @Autowired
	  private AccountRepository underTest;
	  
	  @Test
	  public void shouldReturnAllAccountsWithCompany() {
	    Account account1 = givenAccount(
	        12345678,
	        1000000,
	        "HKD");

	    Account account2 = givenAccount(
		        8888888,
		        1000000,
		        "HKD");

	    List<Account> result = getAccounts();
	    assertThat(result, is(Arrays.asList(account1, account2)));
	  }

	  private Account givenAccount(int accountNumber, double balance, String currency) {
	    return underTest.save(new Account(accountNumber, balance, currency));
	  }
	  
	  private List<Account> getAccounts() {
		  return StreamSupport.stream(
				  underTest
			            .findAll()
			            .spliterator(), false)
			        .collect(Collectors.toList());
		  }
}
