package edu.neu.csye6200.assignments.assignment2.exception;

@SuppressWarnings("serial")
public class UnknownZooAnimal extends Exception{
	
	public UnknownZooAnimal() {
		super();
	}
	
	public UnknownZooAnimal(String message) {
		super(message);
	}

}
