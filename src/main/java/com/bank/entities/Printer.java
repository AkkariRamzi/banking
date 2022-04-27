package com.bank.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.bank.utilities.Console;



public class Printer {
	private  Console console;

	public Printer(Console console) {
		this.console = console;
	}

	public void print(List<Transaction> transactions) {
		transactions.stream().map(this::dataLine)
				.collect(Collectors.toCollection(ArrayList::new)).iterator()
				.forEachRemaining(console::printLine);
	}

	private String dataLine(Transaction transaction) {

		return transaction.getOperationType() + " | " + transaction.getDate() + " | " + transaction.getAmount() + " | "
				+ transaction.getCurrentBalance();
	}
}
