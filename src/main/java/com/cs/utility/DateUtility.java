package com.cs.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class DateUtility {
	
	public static String getDateWithTimeZone() throws ParseException{
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    String dateString ;
	    sdf.setTimeZone(TimeZone.getTimeZone("IST"));
	    dateString = sdf.format(new Date()); 
	    System.out.println(dateString);
	    return dateString;
	}
	
	public static void main(String[] args) {
		try {
			getDateWithTimeZone();
			 System.out.println(getHoursFromDate("2016-10-24 10:32:25.525"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getPrevious7DaysDate("2016-10-24");
		System.out.println(getTimeDiff("2017-03-19 12:52:20.477"));

	}
	
	public static String getHoursFromDate(String dateString){
		String times = dateString.split(" ")[1];
		return times.split(":")[0];
	}
	
	public static List<String> getPrevious7DaysDate(String dateString){
		
		//creating DateFormat for converting time from local timezone to GMT
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//getting GMT timezone, you can get any timezone e.g. UTC
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		List<String> previous7Dates = new ArrayList<>();
		for(int i=0;i<7;i++){
			previous7Dates.add(sdf.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);
		}
		 System.out.println(previous7Dates);
		return previous7Dates;
	}
	public static String getDateFromDate(String createdDate) {
		return createdDate.split(" ")[0];
	}
	public static long getTimeDiff(String createdDate) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		long diffInMinutes =0;
		try {
			String currentTime = getDateWithTimeZone();
			Date current = sdf.parse(currentTime);
			Date previous = sdf.parse(createdDate);
			long duration  = current.getTime() - previous.getTime();
			diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return diffInMinutes;
	}
	 
}
