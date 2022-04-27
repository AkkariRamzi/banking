package com.bank.entities;

import java.math.BigDecimal;

public class Transaction {
	private String account;
	private String date;
	private BigDecimal amount;
	private String operationType;
	private BigDecimal currentBalance;

	public Transaction(String account, String date, BigDecimal amount, String operationType,
			BigDecimal currentBalance) {
		this.account = account;
		this.date = date;
		this.amount = amount;
		this.operationType = operationType;
		this.currentBalance = currentBalance;
	}

	public String getAccount() {
		return account;
	}

	public String getDate() {
		return date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getOperationType() {
		return operationType;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}
}