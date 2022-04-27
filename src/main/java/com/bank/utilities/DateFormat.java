package com.bank.utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {
	public static DateTimeFormatter DD_MM_YYYY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public String dateToString(LocalDate date) {
		return date.format(DD_MM_YYYY);
	}
}
