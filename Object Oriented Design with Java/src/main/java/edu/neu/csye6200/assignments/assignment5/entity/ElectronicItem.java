package edu.neu.csye6200.assignments.assignment5.entity;

import static edu.neu.csye6200.assignments.assignment5.utility.Util.getRecordValuesFromCSVString;

import java.util.List;

public class ElectronicItem extends Item {
	
	private Boolean hasLithiumBattery;
	
	public ElectronicItem(Long id, String name, Double price, Boolean hasLithiumBattery) {
		super(id, name, price);
		this.hasLithiumBattery = hasLithiumBattery;
	}
	
	public ElectronicItem(String csvString) {
		super(csvString);
		List<String> recordValues = getRecordValuesFromCSVString(csvString);
		hasLithiumBattery = Boolean.parseBoolean(recordValues.get(3));
	}

	public boolean getHasLithiumBattery() {
		return hasLithiumBattery;
	}
	
	@Override
	public String toString() {
		return "\nElectronic Item\t".concat(mapToDelimitedString("\t"));
	}
	
	@Override
	public String mapToDelimitedString(String delimiter) {
		return super.mapToDelimitedString(delimiter)
				.concat(delimiter)
				.concat(hasLithiumBattery.toString());
	}

}
