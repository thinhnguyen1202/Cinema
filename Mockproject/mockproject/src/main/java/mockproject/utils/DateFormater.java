package mockproject.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormater {
	public static Date toSqlDate(String dateTimeString) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate localDate = LocalDate.parse(dateTimeString, dtf);
		return Date.valueOf(localDate);
		
	}
	
	public static Date toSqlDateNow() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		LocalDate localDateNow = LocalDate.now();
		return Date.valueOf(localDateNow);
	}
	
	public static Date toSqlDateYesterday() {
		LocalDate toDay = LocalDate.now();
		LocalDate yesterDay = toDay.minusDays(1);
		
		return Date.valueOf(yesterDay);
	}
	
	public static Date toSqlDatePastSeveday() {
		LocalDate today = LocalDate.now();
		LocalDate past7date = today.minusDays(7);
		
		return Date.valueOf(past7date);
		
	}
	
	public static void main(String[] args) {
	
		LocalDate dateNow = LocalDate.now();
		
		LocalDate yesterday = dateNow.minusDays(1);
		
		System.out.println(yesterday.toString());
		
		
	}
}
