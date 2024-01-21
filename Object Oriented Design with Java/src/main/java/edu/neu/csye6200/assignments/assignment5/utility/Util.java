package edu.neu.csye6200.assignments.assignment5.utility;

import static edu.neu.csye6200.assignments.assignment5.utility.Constants.REPEAT_CHARACTER;
import static edu.neu.csye6200.assignments.assignment5.utility.Constants.REPEAT_CHARACTER_TIMES;
import static edu.neu.csye6200.assignments.assignment5.utility.Constants.COMMA_DELIMITER;

import java.util.Arrays;
import java.util.List;

public class Util {
	
	public static void printSeparator() {
		System.out.println(getSeparatorString());
	}
	
	public static String getSeparatorString() {
		return "\n" + REPEAT_CHARACTER.repeat(REPEAT_CHARACTER_TIMES) + "\n";
	}
	
	public static List<String> getRecordValuesFromCSVString(String csvString) {
		String[] values = csvString.split(COMMA_DELIMITER);
		return Arrays.asList(values);
	}
}
