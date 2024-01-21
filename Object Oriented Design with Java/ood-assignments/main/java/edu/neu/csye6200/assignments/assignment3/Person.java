package edu.neu.csye6200.assignments.assignment3;

import java.util.Comparator;

public class Person implements Comparable<Person> {

	private String firstName;
	private String lastName;
	private Integer age;

	public Person(String firstName, String lastName, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Person").append("\t").append(this.firstName).append("\t").append(this.lastName)
				.append("\t").append(this.age);
		return stringBuilder.toString();
	}
	
	// Comparable compareTo Method - Natural Sort Order by Age
	@Override
	public int compareTo(Person otherPerson) {
		return this.getAge().compareTo((otherPerson.getAge()));
	}
	
	// Static Compare Methods
	public static int compareByAge(Person person1, Person person2) {
		return person1.getAge().compareTo(person2.getAge());
	}
	
	public static int compareByFirstName(Person person1, Person person2) {
		return person1.getFirstName().compareTo(person2.getFirstName());
	}
	
	public static int compareByLastName(Person person1, Person person2) {
		return person1.getLastName().compareTo(person2.getLastName());
	}
	
	// Static Comparator Classes
	public static class AgeComparator implements Comparator<Person> {

		@Override
		public int compare(Person person1, Person person2) {
			return person1.getAge().compareTo(person2.getAge());
		}
	}

	public static class FirstNameComparator implements Comparator<Person> {

		@Override
		public int compare(Person person1, Person person2) {
			return person1.getFirstName().compareTo(person2.getFirstName());
		}

	}

	public static class LastNameComparator implements Comparator<Person> {

		@Override
		public int compare(Person person1, Person person2) {
			return person1.getLastName().compareTo(person2.getLastName());
		}

	}

}
