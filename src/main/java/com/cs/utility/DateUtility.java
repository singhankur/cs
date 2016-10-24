package com.cs.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtility {
	
	public static String getDateWithTimeZone() throws ParseException{
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	    String dateString ;
	    sdf.setTimeZone(TimeZone.getTimeZone("IST"));
	    dateString = sdf.format(new Date()); 
	    System.out.println(dateString);
	    return dateString;
	}
	 
}
