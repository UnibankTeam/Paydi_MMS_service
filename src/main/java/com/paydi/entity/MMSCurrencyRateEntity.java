package com.paydi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mms_currency_rate")
public class MMSCurrencyRateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "source_currency")
	private String sourceCurrency;
	@Column(name = "target_currency")
	private String targetCurrency;
	@Column(name = "exchange_rate")
	private double exchangeRate;
	@Column(name = "valid_from_date")
	private Date validFromDate;
	@Column(name = "valid_to_date")
	private Date validToDate;

}