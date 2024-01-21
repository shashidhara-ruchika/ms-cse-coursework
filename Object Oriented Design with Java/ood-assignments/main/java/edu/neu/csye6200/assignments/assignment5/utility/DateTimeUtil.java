package edu.neu.csye6200.assignments.assignment5.utility;

import static edu.neu.csye6200.assignments.assignment5.utility.Constants.DATE_FORMAT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	
	public static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	public static Date parseDate(String dateString) {
	    try {
	        return DateTimeUtil.dateFormat.parse(dateString);
	    } catch (ParseException e) {
	    	System.err.println("Parsing Exception occured while parsing Date: ");
	        e.printStackTrace();
	        return null; 
	    } catch (Exception e) {
	    	System.err.println("Other Exception occured while parsing Date: ");
	    	e.printStackTrace();
	    	return null;
	    }
	}

	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

}
