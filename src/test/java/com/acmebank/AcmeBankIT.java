package com.acmebank;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.acmebank.domain.Account;
import com.acmebank.dto.AccountDTO;
import com.acmebank.repository.AccountRepository;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class AcmeBankIT {
	
	  @LocalServerPort
	  private int port;

	  @Autowired
	  private AccountRepository accountRepository;

	  @Before
	  public void setup() {
		  accountRepository.deleteAll();
	  }
	  
	  private TestRestTemplate restTemplate = new TestRestTemplate();
	  
	  private Account givenAccount(int accountNumber, double balance, String currency) {
		    return accountRepository.save(new Account(accountNumber, balance, currency));
		  }
	  
	  @Test
	  public void shouldReturnBalance() {
		Account account = givenAccount(12345678,1000000,"HKD");

		ResponseEntity<AccountDTO> response = getBalance(account.getAccountNumber());
	    assertTrue(response.getStatusCode().is2xxSuccessful());
	    AccountDTO result = response.getBody();
	    assertThat(result.getBalance(), is(1000000));
	  }
	  
	  private ResponseEntity<AccountDTO> getBalance(int accountNumber) {
		    final String url = String.format("http://localhost:%s/customers?email=%s", port, accountNumber);
		    return restTemplate.getForEntity(url, AccountDTO.class);
		  }
}
