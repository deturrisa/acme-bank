package com.acmebank.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class SupportedCurrency {

  @Id
  @GeneratedValue
  @Type(type = "uuid-char")
  private UUID id;

  @Column(unique = true)
  private String currencyCode;

  public SupportedCurrency() {
  }

  public SupportedCurrency(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public UUID getId() {
    return id;
  }

  public String getSupportedCurrencyCode() {
    return currencyCode;
  }
}
