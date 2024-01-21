package edu.neu.csye6200.assignments.assignment5.entity;

import static edu.neu.csye6200.assignments.assignment5.utility.Util.getRecordValuesFromCSVString;

import edu.neu.csye6200.assignments.assignment5.utility.DateTimeUtil;
import java.util.Date;
import java.util.List;

public class FoodItem extends Item {
	
	private Date expiry;
	
	public FoodItem(Long id, String name, Double price, Date expiry) {
		super(id, name, price);
		this.expiry = expiry;
	}
	
	public FoodItem(String csvString) {
		super(csvString);
		List<String> recordValues = getRecordValuesFromCSVString(csvString);
		expiry = DateTimeUtil.parseDate(recordValues.get(3));
	}
	
	public Date getExpiry() {
		return expiry;
	}
	
	@Override
	public String toString() {
		return "\nFood Item\t".concat(mapToDelimitedString("\t"));
	}
	
	@Override
	public String mapToDelimitedString(String delimiter) {
		return super.mapToDelimitedString(delimiter)
				.concat(delimiter)
				.concat(DateTimeUtil.formatDate(expiry));
	}
	
}
