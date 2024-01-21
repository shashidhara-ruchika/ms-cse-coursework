package edu.neu.csye6200.assignments.assignment5.entity;

import static edu.neu.csye6200.assignments.assignment5.utility.Util.getRecordValuesFromCSVString;

import java.util.List;

public class ServiceItem extends Item {

	private Integer duration;
	
	public ServiceItem(Long id, String name, Double price, Integer duration) {
		super(id, name, price);
		this.duration = duration;
	}
	
	public ServiceItem(String csvString) {
		super(csvString);
		List<String> recordValues = getRecordValuesFromCSVString(csvString);
		try {
			duration = Integer.parseInt(recordValues.get(3));
		} catch (NumberFormatException e) {
			duration = -1;
			System.err.println("Exception occured while parsing string to Number format!");
			e.printStackTrace();
		} catch (Exception e) {
			duration = -1;
			System.err.println("Exception occured while parsing string!");
			e.printStackTrace();
		}
	}
	
	public Integer getDuration() {
		return duration;
	}
	
	@Override
	public String toString() {
		return "\nService Item\t".concat(mapToDelimitedString("\t"));
	}
	
	@Override
	public String mapToDelimitedString(String delimiter) {
		return super.mapToDelimitedString(delimiter)
				.concat(delimiter)
				.concat(duration.toString());
	}
}
