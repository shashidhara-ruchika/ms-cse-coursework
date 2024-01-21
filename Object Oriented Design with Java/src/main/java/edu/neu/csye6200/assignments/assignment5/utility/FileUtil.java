package edu.neu.csye6200.assignments.assignment5.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	
	public static List<String> readCSVRecordsFromFile(String filename, boolean skipHeader) {

		List<String> records = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
			String line;

			if (skipHeader) {
				line = bufferedReader.readLine();
			}

			while ((line = bufferedReader.readLine()) != null) {
				records.add(line);					
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
			System.out.println("Read CSV String Records from " + filename + " file\n");
			records.stream().forEach(System.out::println);
		}
		return records;
	}
	
	public static void writeCSVRecordsToFile(String filename, List<String> recordLines, Boolean shouldAppendToFile) {
	    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, shouldAppendToFile))) {
	    	 	for (String record : recordLines) {
	    		bufferedWriter.write(record);
	    		bufferedWriter.newLine();
	    	}
	    } catch (IOException e) {
	        System.err.println("Exception occurred while writing to the file: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        System.out.println("Write CSV String Records to " + filename + " file\n");
	        recordLines.stream().forEach(System.out::println);
	    }
	}

}
