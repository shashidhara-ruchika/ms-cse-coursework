package edu.neu.csye6200.labs.lab3;

public class Student implements Comparable<Student> {
	
	private Integer age;
	private String firstName;
	private String lastName;
	
	private Long id;
	private Double gpa;
	
	
	public Student(Long id, String firstName, String lastName, Integer age, Double gpa) {
     this.id = id;
     this.age = age;
     this.firstName = firstName;
     this.lastName = lastName;
     this.gpa = gpa;
 }
	
	 public Long getId() {
	        return id;
	    }

 public Integer getAge() {
     return age;
 }

 public String getFirstName() {
     return firstName;
 }
 
 public String getLastName() {
     return lastName;
 }

 public Double getGpa() {
     return gpa;
 }
 
 @Override
 public String toString() {
 	StringBuilder stringBuilder = new StringBuilder();
 	stringBuilder.append("Student")
 	.append("\t").append(id)
 	.append("\t").append(firstName)
 	.append("\t").append(lastName)
 	.append("\t").append(age)
 	.append("\t").append(gpa);
 	 return stringBuilder.toString();
 }
    

	@Override
	public int compareTo(Student o) {
		return this.gpa.compareTo(o.gpa);
	}
	
}
