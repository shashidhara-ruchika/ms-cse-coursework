package edu.neu.csye6200.assignments.assignment5.entity;

import static edu.neu.csye6200.assignments.assignment5.utility.Util.getRecordValuesFromCSVString;

import java.util.List;

public class Item implements Comparable<Item> {
	
	private Long id;
	private String name;
	private Double price;
	
	public Item(Long id, String name, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public Item(String csvString) {
		try {
			List<String> recordValues = getRecordValuesFromCSVString(csvString);
			id = Long.parseLong(recordValues.get(0));
			name = recordValues.get(1);
			price = Double.parseDouble(recordValues.get(2));
		} catch (NumberFormatException e) {
			id = -1L;
			name = "";
			price = 0.0;
			System.err.println("Exception occured while parsing string to Number format!");
			e.printStackTrace();
		} catch (Exception e) {
			id = -1L;
			name = "";
			price = 0.0;
			System.err.println("Exception occured while parsing string!");
			e.printStackTrace();
		}
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return "\nItem\t\t".concat(mapToDelimitedString("\t"));
	}
	
	public String mapToDelimitedString(String delimiter) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
			.append(id).append(delimiter)
			.append(name).append(delimiter)
			.append(price);
		return stringBuilder.toString();
	}
	
	// Natural Sort Order by ID
	@Override
	public int compareTo(Item otherItem) {
		return this.getId().compareTo(otherItem.getId());
	}
}
