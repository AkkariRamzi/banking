package com.bank.exceptions;

public class BalanceInsufficientException extends Exception {

	public BalanceInsufficientException(String message) {
		super(message);
	}
}
