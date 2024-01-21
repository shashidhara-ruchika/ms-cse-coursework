package edu.neu.csye6200.assignments.assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import edu.neu.csye6200.assignments.assignment3.Person.AgeComparator;
import edu.neu.csye6200.assignments.assignment3.Person.LastNameComparator;
import edu.neu.csye6200.assignments.assignment3.Student.GpaComparator;
import edu.neu.csye6200.assignments.assignment3.Student.IdComparator;

public class School {

	private List<Student> studentRoster;
	private List<Person> personRoster;
	
	public School() {
		studentRoster = new ArrayList<Student>();
		personRoster = new ArrayList<Person>();
	}

	public void addStudentToStudentRoster(Student student) {
		studentRoster.add(student);
	}

	public void addStudentToPersonRoster(Student student) {
		personRoster.add(student);
	}

	public List<Person> getPersonRoster() {
		return personRoster;
	}

	public List<Student> getStudentRoster() {
		return studentRoster;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\nSchool\n").append("\nStudent Roster:\n");
		this.studentRoster.stream().forEach(student -> stringBuilder.append(student.toString()).append("\n"));
		stringBuilder.append("\nPerson Roster:\n");
		this.personRoster.stream().forEach(person -> stringBuilder.append(person.toString()).append("\n"));
		return stringBuilder.toString();

	}

	public void printSchoolState(String display) {
		System.out.println(display);
		System.out.println(this.toString());
		Utils.printSeparator();
	}
	
	public static Student recordStringToStudentMapper(List<String> record) {
		Student student = null;
		try {
			student = new Student(
					Long.parseLong(record.get(0)),
					record.get(1),
					record.get(2),
					Integer.parseInt(record.get(3)),
					Double.parseDouble(record.get(4))
				);
			
			student.addParent(
						student.new Parent(
								record.get(5), 
								record.get(6), 
								Integer.parseInt(record.get(7))
							)
					);
			student.addParent(
						student.new Parent(
								record.get(8), 
								record.get(9), 
								Integer.parseInt(record.get(10))
							)
					);
		} catch (Student.TooManyParentsException e) {
			System.err.println("\nException occurred while adding Parents!");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println("\nException occurred while parsing Number Records!");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("\nException occured: ");
			e.printStackTrace();
		}
		return student;
	}
	
	public List<Student> getStudentListFromCSV(String filename) {
		List<List<String>> studentRecords = Utils.readCSV(filename, true);
		
		System.out.println("Create List of Student Objects from String Records\n");
		
		List<Student> studentList = studentRecords.stream()
				.map(School::recordStringToStudentMapper)
				.collect(Collectors.toList());
		
		studentList.stream().forEach(System.out::println);
		System.out.println();
		Utils.printSeparator();
		
		return studentList;
	}
	
	private static AgeComparator ageComparator = new AgeComparator();
	private static LastNameComparator lastNameComparator = new LastNameComparator();
	private static IdComparator idComparator = new IdComparator();
	private static GpaComparator gpaComparator = new GpaComparator();
	
	public void sortStudentRosterById() {
		studentRoster.sort(null);
	}
	
	public void sortPersonRosterById() {
		personRoster.sort(idComparator);
	}

	public void sortStudentRosterByAge() {
		studentRoster.sort(Student::compareByAge);
	}
	
	public void sortPersonRosterByAge() {
		personRoster.sort(ageComparator);
	}
	
	public void sortStudentRosterByFirstName() {
		studentRoster.sort(Student::compareByFirstName);
	}
	
	public void sortPersonRosterByFirstName() {
		personRoster.sort(Person::compareByFirstName);
	}
	
	public void sortStudentRosterByLastName() {
		studentRoster.sort(lastNameComparator);
	}
	
	public void sortPersonRosterByLastName() {
		personRoster.sort(lastNameComparator);
	}
	
	public void sortStudentRosterByGpa() {
		studentRoster.sort(gpaComparator);
	}
	
	public void sortPersonRosterByGpa() {
		personRoster.sort(gpaComparator);
	}
	
	public static void demo() {

		Utils.printSeparator();

		School school = new School();
		school.printSchoolState("Initialization");
		
		List<Student> studentList = school.getStudentListFromCSV("src/resources/assignment3/student.csv");
		
		if (studentList.size() == 0) {
			return;
		}
		
		studentList.stream().forEach(school::addStudentToStudentRoster);
		school.printSchoolState("Add Students to Student Roster");
		
		studentList.stream().forEach(school::addStudentToPersonRoster);
		school.printSchoolState("Add Students to Person Roster");
		
//		school.sortStudentRosterById();
		school.studentRoster.sort(Comparator.comparing(Student::getId));
		school.printSchoolState("Sort Student Roster by ID");
		
		school.sortPersonRosterById();
		school.printSchoolState("Sort Person Roster by ID");
		
		school.sortStudentRosterByAge();
		school.printSchoolState("Sort Student Roster by Age");
		
		school.sortPersonRosterByAge();
		school.printSchoolState("Sort Person Roster by Age");
		
		school.sortStudentRosterByFirstName();
		school.printSchoolState("Sort Student Roster by First Name");
		
		school.sortPersonRosterByFirstName();
		school.printSchoolState("Sort Person Roster by First Name");
		
		school.sortStudentRosterByLastName();
		school.printSchoolState("Sort Student Roster by Last Name");
		
		school.sortPersonRosterByLastName();
		school.printSchoolState("Sort Person Roster by Last Name");
		
		school.sortStudentRosterByGpa();
		school.printSchoolState("Sort Student Roster by GPA");
		
		school.sortPersonRosterByGpa();
		school.printSchoolState("Sort Person Roster by GPA");

	}

}
