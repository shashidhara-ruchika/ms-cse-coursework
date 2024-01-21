package edu.neu.csye6200.labs.lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class School {
	
	private List<Student> studentList;
	
	public School() {
		studentList = new ArrayList<Student>();
	}
	
	public void addStudent(Student student) {
     studentList.add(student);
 }
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (Student student : this.studentList) {
			stringBuilder.append(student.toString()).append("\n");
		}
		return stringBuilder.toString();
	}
	
	public void printStudentList() {
		System.out.println(this.toString());
	}
	
	public void sortByID() {
//     Collections.sort(studentList, new IdComparator()); 
//     studentList.sort(Comparator.comparing(Student::getId));
		studentList = studentList.stream().sorted(Comparator.comparing(Student::getId)).collect(Collectors.toList());
 }

 public void sortByAge() {
//     Collections.sort(studentList, new AgeComparator());
//     studentList.sort(Comparator.comparing(Student::getAge));
 	studentList = studentList.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.toList());
 }

 public void sortByLastName() {
//     Collections.sort(studentList, new LastNameComparator());
// 	studentList.sort(Comparator.comparing(Student::getLastName));
 	studentList = studentList.stream().sorted(Comparator.comparing(Student::getLastName)).collect(Collectors.toList());
 }
 
 public void sortByGPA() {
// 	studentList.sort(null);
 	studentList = studentList.stream().sorted().collect(Collectors.toList());
 }
 
 public static void demo() {
 	
 	School school = new School();
     school.addStudent(new Student(101L, "John", "Smith", 20, 3.5));
     school.addStudent(new Student(102L, "Alice", "Johnson", 22, 3.2));
     school.addStudent(new Student(103L, "Bob", "William", 21, 3.9));
     
     System.out.println("\nInitital Student List\n");
     school.printStudentList();
     
     System.out.println("\nSorting by Comparable Ascending GPA Student List\n");
     school.sortByGPA();
     school.printStudentList();
     
     System.out.println("\nSorting by Comparator Ascending ID Student List\n");
     school.sortByID();
     school.printStudentList();
     
     System.out.println("\nSorting by Comparator Ascending Age Student List\n");
     school.sortByAge();
     school.printStudentList();
     
     System.out.println("\nSorting by Comparator Ascending LastName Student List\n");
     school.sortByLastName();
     school.printStudentList();
     
 }
	
}

