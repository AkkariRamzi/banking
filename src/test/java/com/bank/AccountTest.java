package com.bank;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bank.entities.Account;
import com.bank.entities.Printer;
import com.bank.entities.Transaction;
import com.bank.exceptions.BalanceInsufficientException;
import com.bank.utilities.DateFormat;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
	private Account account;

	private DateFormat dateformat;
	@Mock
	private Printer printer;

	@Before
	public void setUp() {
		dateformat = new DateFormat();
		account = new Account("1234", new BigDecimal(500), dateformat, printer);

	}

	/**
	 * Deposit success Test
	 */
	@Test
	public void deposit_success() {
		LocalDate date_deposit = LocalDate.of(2022, 4, 22);
		account.deposit(new BigDecimal(50), date_deposit);
		List<Transaction> transactions = account.getTransactions();
		assertEquals(transactions.size(), 1);
		assertEquals(transactions.get(0).getCurrentBalance(), BigDecimal.valueOf(550));
	}

	/**
	 * Deposit Faillure test
	 */
	@Test(expected = IllegalArgumentException.class)
	public void deposit_account_negative_amount() {
		account.setBalance(new BigDecimal(500));
		LocalDate date_withdrawal = LocalDate.of(2022, 4, 22);
		account.deposit(new BigDecimal(50).negate(), date_withdrawal);

	}

	/**
	 * withdrwal success test
	 * 
	 * @throws BalanceInsufficientException
	 */
	@Test
	public void withdrawal() throws BalanceInsufficientException {
		LocalDate withdrawalDate = LocalDate.of(2020, 6, 24);
		account.withdrawal(new BigDecimal(50), withdrawalDate);
		List<Transaction> transactions = account.getTransactions();
		assertEquals(transactions.size(), 1);
		assertEquals(transactions.get(0).getCurrentBalance(), BigDecimal.valueOf(450));
	}

	/**
	 * withdrwal faillure test
	 * 
	 * @throws BalanceInsufficientException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void withdraw_account_negative_amount() throws BalanceInsufficientException {
		account.setBalance(new BigDecimal(500));
		LocalDate withdrawalDate = LocalDate.of(2020, 6, 24);
		account.withdrawal(new BigDecimal(600).negate(), withdrawalDate);
	}

	/**
	 * Print Account history Test
	 */
	@Test
	public void printStatement() {
		account.setBalance(new BigDecimal(500));
		account.printStatement();
		verify(printer).print(account.getTransactions());
	}
}
