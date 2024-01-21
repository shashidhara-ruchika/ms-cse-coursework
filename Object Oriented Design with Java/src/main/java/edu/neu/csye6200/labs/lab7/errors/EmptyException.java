package edu.neu.csye6200.labs.lab7.errors;

public class EmptyException extends Exception {
	public EmptyException() {
		super("EmptyException: Cannot remove elements!");
	}
}
