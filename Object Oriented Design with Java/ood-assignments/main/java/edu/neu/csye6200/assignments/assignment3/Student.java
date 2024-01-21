package edu.neu.csye6200.assignments.assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Student extends Person {
	
	private Long id;
	private Double gpa;
	private List<Parent> parents;
	private static Integer NUMBER_OF_PARENTS = 2;
	
	public class Parent extends Person {

		public Parent(String firstName, String lastName, Integer age) {
			super(firstName, lastName, age);
		}
		
		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Parent").append("\t").append(this.getFirstName()).append("\t").append(this.getLastName())
					.append("\t").append(this.getAge());
			return stringBuilder.toString();
		}
	}
	
	@SuppressWarnings("serial")
	public class TooManyParentsException extends Exception {
		
		public TooManyParentsException() {
			super();
		}
		
		public TooManyParentsException(String message) {
			super(message);
		}
	}

	public Student(Long id, String firstName, String lastName, Integer age, Double gpa) {
		super(firstName, lastName, age);
		this.id = id;
		this.gpa = gpa;
		this.parents = new ArrayList<Student.Parent>(2);
	}

	public Double getGpa() {
		return gpa;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Student").append("\t").append(this.getFirstName()).append("\t").append(this.getLastName())
				.append("\t").append(this.getAge()).append("\t").append(this.id).append("\t")
				.append(this.gpa);
		this.parents.stream().forEach(parent -> stringBuilder.append("\t").append(parent));
		return stringBuilder.toString();
	}
	
	public void addParent(Parent parent) throws TooManyParentsException  {
		if (parents.size() == Student.NUMBER_OF_PARENTS) {
			throw this.new TooManyParentsException("Cannot add more than " + NUMBER_OF_PARENTS + " Parents for a Student!");
		}
		parents.add(parent);
	}
	
	private static boolean isPersonInstanceOfStudent(Person person) {
		return person instanceof Student;
	}
	
	private static boolean isBothPersonInstaceOfStudent(Person person1, Person person2) {
		return isPersonInstanceOfStudent(person1) && isPersonInstanceOfStudent(person2);
	}

	// Comparable compareTo Method - Natural Sort Order by ID
	@Override
	public int compareTo(Person otherPerson) {

		if (isPersonInstanceOfStudent(otherPerson)) {
			Student otherStudent = (Student) otherPerson;
			return this.getId().compareTo(otherStudent.getId());
		}
		return super.compareTo(otherPerson);
	}
	
	// Static Compare Methods
	public static int compareByGpa(Person person1, Person person2) {
		if (isBothPersonInstaceOfStudent(person1, person2)) {
        	Student student1 = (Student) person1;
        	Student student2 = (Student) person2;
        	return student1.getGpa().compareTo(student2.getGpa());
        } else {
            return 0;
        }
	}
	
	public static int compareById(Person person1, Person person2) {
		if (isBothPersonInstaceOfStudent(person1, person2)) {
        	Student student1 = (Student) person1;
        	Student student2 = (Student) person2;
        	return student1.getId().compareTo(student2.getId());
        } else {
            return 0;
        }
	}
	
	// Static Comparator Classes
	public static class GpaComparator implements Comparator<Person> {
        @Override
        public int compare(Person person1, Person person2) {
            if (isBothPersonInstaceOfStudent(person1, person2)) {
            	Student student1 = (Student) person1;
            	Student student2 = (Student) person2;
            	return student1.getGpa().compareTo(student2.getGpa());
            } else {
                return 0;
            }
        }
    }
	
	public static class IdComparator implements Comparator<Person> {
        @Override
        public int compare(Person person1, Person person2) {
            if (isBothPersonInstaceOfStudent(person1, person2)) {
            	Student student1 = (Student) person1;
            	Student student2 = (Student) person2;
            	return student1.getId().compareTo(student2.getId());
            } else {
                return 0;
            }
        }
    }

}
