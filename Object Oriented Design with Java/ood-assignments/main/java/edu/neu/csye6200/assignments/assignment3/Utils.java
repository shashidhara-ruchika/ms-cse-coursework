package edu.neu.csye6200.assignments.assignment3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

	private static String COMMA_DELIMITER = ",";
	private static Integer REPEAT_CHARACTER_TIMES = 120;

	public static List<List<String>> readCSV(String filename, boolean skipHeader) {

		List<List<String>> records = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;

			if (skipHeader) {
				line = br.readLine();
			}

			while ((line = br.readLine()) != null) {
				String[] values = line.split(COMMA_DELIMITER);
				records.add(Arrays.asList(values));
			}
			
		} catch(FileNotFoundException e) {
			System.err.println(filename + " file not found exception occured!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Exception occured while reading the file!");
			e.printStackTrace();
		} catch(Exception e) {
			System.err.println("Exception occured: ");
			e.printStackTrace();
		} finally {
			System.out.println("Read String Records from " + filename + " file\n");
			records.stream().forEach(System.out::println);
			System.out.println();
			Utils.printSeparator();
		}
		return records;
	}
	
	public static void printSeparator() {
		System.out.println("-".repeat(REPEAT_CHARACTER_TIMES) + "\n");
	}
	
}
