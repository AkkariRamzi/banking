package com.bank;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bank.entities.Account;
import com.bank.entities.Printer;
import com.bank.exceptions.BalanceInsufficientException;
import com.bank.utilities.Console;
import com.bank.utilities.DateFormat;

@RunWith(MockitoJUnitRunner.class)
public class PrinterTest {
	Account account;

	@Mock
	Console console;

	@Before
	public void prepare_data() {

		DateFormat dateformat = new DateFormat();
		Printer printer = new Printer(console);
		account = new Account("1234", new BigDecimal(2000), dateformat, printer);

	}

	/**
	 * @throws BalanceInsufficientException
	 * PrinterTest
	 */
	@Test
	public void verify_print_format() throws BalanceInsufficientException {
		LocalDate dateDeposit1 = LocalDate.of(2021, 5, 31);
		LocalDate dateWithdrawal = LocalDate.of(2021, 5, 29);
		LocalDate dateDeposit2 = LocalDate.of(2021, 5, 14);

		account.deposit(BigDecimal.valueOf(100), dateDeposit1);
		account.withdrawal(BigDecimal.valueOf(100), dateWithdrawal);
		account.deposit(BigDecimal.valueOf(100), dateDeposit2);
		account.printStatement();
		verify(console).printLine("deposit | 31/05/2021 | 100 | 2100");
		verify(console).printLine("withdrawal | 29/05/2021 | -100 | 2000");
		verify(console).printLine("deposit | 14/05/2021 | 100 | 2100");

	}
}
