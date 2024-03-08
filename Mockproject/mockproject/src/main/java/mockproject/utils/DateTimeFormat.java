package mockproject.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormat {

	public static Timestamp getNow() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
		Timestamp returnValue = Timestamp.valueOf(dtf.format(now).toString());
		return returnValue;
	}

	public static Timestamp parseTimeWithDateAndHours(String date, String hours) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

		String dateTimeLine = date + " " + hours;

		LocalDateTime dateTime = LocalDateTime.parse(dateTimeLine, dtf);
		Timestamp returnValue = Timestamp.valueOf(dateTime);
		return returnValue;
	}

	public static Timestamp plusTheTimeStampValueByHours(Timestamp start_time, int hours) {
		Timestamp end_time = Timestamp.valueOf(start_time.toLocalDateTime().plusHours(hours));
		return end_time;
	}

	public static Timestamp plusTheTimeStampValueByMinutes(Timestamp start_time, int mins) {
		Timestamp end_time = Timestamp.valueOf(start_time.toLocalDateTime().plusMinutes(mins));
		return end_time;
	}
	
	public static Time timeStampToTime(Timestamp start_time) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		Time returnValue = Time.valueOf(LocalTime.parse(start_time.toString().subSequence(11, 19),dtf));
		return returnValue;
	}
	
	

	public static void main(String[] args) {
		
		
		System.out.println(getNow().toString());
		

	}
	
	
}
