package edu.neu.csye6200.assignments.assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static edu.neu.csye6200.assignments.assignment4.Person.Constants.COMMA_DELIMITER;
import static edu.neu.csye6200.assignments.assignment4.Person.Constants.REPEAT_CHARACTER;
import static edu.neu.csye6200.assignments.assignment4.Person.Constants.REPEAT_CHARACTER_TIMES;
import static edu.neu.csye6200.assignments.assignment4.Person.Util.printSeparator;
import static edu.neu.csye6200.assignments.assignment4.Person.Util.sortGenericList;
import static edu.neu.csye6200.assignments.assignment4.Person.Util.sortGenericListStream;

public class Person implements Comparable<Person> {
	
	private Long id;
	private Integer age;
	private String firstName;
	private String lastName;
	private String parentFirstName;
	private String parentLastName;
	
	public Person(Long idLong, Integer ageInteger, 
			String firstNameString, String lastNameString, 
			String parentFirstNameString, String parentLastNameString) {
		this.id = idLong;
		this.age = ageInteger;
		this.firstName = firstNameString;
		this.lastName = lastNameString;
		this.parentFirstName = parentFirstNameString;
		this.parentLastName = parentLastNameString;
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
	
	public String getParentFirstName() {
		return parentFirstName;
	}
	
	public String getParentLastName() {
		return parentLastName;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\nPerson\t")
			.append(id).append("\t")
			.append(age).append("\t")
			.append(firstName).append("\t")
			.append(lastName).append("\t")
			.append(parentFirstName).append("\t")
			.append(parentLastName).append("\t\n");
		return stringBuilder.toString();
	}
	
	// Natural Sort by ID
	@Override
	public int compareTo(Person otherPerson) {
		return this.getId().compareTo(otherPerson.getId());
	}		
	
	public static class Student extends Person {
		
		private Long studentId;
		private Double gpa;
		
		public Student(Long idLong, Integer ageInteger, 
				String firstNameString, String lastNameString, 
				String parentFirstNameString, String parentLastNameString, 
				Long studentIdLong, Double gpaDouble) {
			super(idLong, ageInteger, firstNameString, lastNameString, 
					parentFirstNameString, parentLastNameString);
			this.studentId = studentIdLong;
			this.gpa = gpaDouble;
		}
		
		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("\nStudent\t")
			.append(this.getId()).append("\t")
			.append(this.getAge()).append("\t")
			.append(this.getFirstName()).append("\t")
			.append(this.getLastName()).append("\t")
			.append(this.getParentFirstName()).append("\t")
			.append(this.getParentLastName()).append("\t")
			.append(this.studentId).append("\t")
			.append(this.gpa).append("\t");
			return stringBuilder.toString();
		}
		
		public Long getStudentId() {
			return studentId;
		}
		
		public Double getGpa() {
			return gpa;
		}
		
		// Natural Sort Order by GPA
		@Override
		public int compareTo(Person otherPerson) {
			if (otherPerson instanceof Student) {
				Student otherStudent = (Student) otherPerson;
				return this.getGpa().compareTo(otherStudent.getGpa());
			}
			return super.compareTo(otherPerson);
		}
		
		public static Student csvStringToStudentMapper(String csvString) {
			String[] values = csvString.split(COMMA_DELIMITER);
			List<String> recordValues = Arrays.asList(values);
			Student student = new Student(null, REPEAT_CHARACTER_TIMES, csvString, csvString, csvString, csvString, null, null);
			try {
				student = new Student(
							Long.parseLong(recordValues.get(0)),
							Integer.parseInt(recordValues.get(1)),
							recordValues.get(2),
							recordValues.get(3),
							recordValues.get(4),
							recordValues.get(5),
							Long.parseLong(recordValues.get(6)),
							Double.parseDouble(recordValues.get(7))
						);
			} catch (NumberFormatException e) {
				System.err.println("Exception occured while parsing CSV Record to Number Format!");
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Exception occured while parsing CSV Record!");
				e.printStackTrace();
			}
			return student;
		}
		
		public static String studentToCsvStringMapper(Student student) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder
				.append(student.getId().toString()).append(COMMA_DELIMITER)
				.append(student.getAge().toString()).append(COMMA_DELIMITER)
				.append(student.getFirstName()).append(COMMA_DELIMITER)
				.append(student.getLastName()).append(COMMA_DELIMITER)
				.append(student.getParentFirstName()).append(COMMA_DELIMITER)
				.append(student.getParentLastName()).append(COMMA_DELIMITER)
				.append(student.getStudentId().toString()).append(COMMA_DELIMITER)
				.append(student.getGpa().toString());
			return stringBuilder.toString();
		}
    
	}
	
	
	public static class Teacher extends Person {
		
		private Double hourlyWage;
		
		public Teacher(Long idLong, Integer ageInteger, 
				String firstNameString, String lastNameString, 
				String parentFirstNameString, String parentLastNameString,
				Double hourlyWageDouble) {
			super(idLong, ageInteger, firstNameString, lastNameString, 
					parentFirstNameString, parentLastNameString);
			this.hourlyWage = hourlyWageDouble;
		}
		
		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("\nTeacher\t")
			.append(this.getId()).append("\t")
			.append(this.getAge()).append("\t")
			.append(this.getFirstName()).append("\t")
			.append(this.getLastName()).append("\t")
			.append(this.getParentFirstName()).append("\t")
			.append(this.getParentLastName()).append("\t")
			.append(this.hourlyWage).append("\t");
			return stringBuilder.toString();
		}
		
		public Double getHourlyWage() {
			return hourlyWage;
		}
		
		// Natural Sort order by Hourly Wage
		@Override
		public int compareTo(Person otherPerson) {
			if (otherPerson instanceof Teacher) {
				Teacher otherTeacher = (Teacher) otherPerson;
				return this.getHourlyWage().compareTo(otherTeacher.getHourlyWage());
			}
			return super.compareTo(otherPerson);
		}
	}
	
	public static class FileUtil {
		
		public static List<String> readFromFile(String filename, boolean skipHeader) {

			List<String> records = new ArrayList<>();

			try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
				String line;

				if (skipHeader) {
					line = bufferedReader.readLine();
				}

				while ((line = bufferedReader.readLine()) != null) {
					records.add(line);					
				}
				
			} catch(FileNotFoundException e) {
				System.err.println(filename + " file not found exception occured!");
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("Exception occured while reading the file!");
				e.printStackTrace();
			} catch(Exception e) {
				System.err.println("Exception occured: ");
				e.printStackTrace();
			} finally {
				System.out.println("Read CSV String Records from " + filename + " file\n");
				records.stream().forEach(System.out::println);
			}
			return records;
		}
		
		public static void writeToFile(String filename, List<String> recordLines, Boolean shouldAppendToFile) {
		    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, shouldAppendToFile))) {
		    	 	for (String record : recordLines) {
		    		bufferedWriter.write(record);
		    		bufferedWriter.newLine();
		    	}
		    } catch (IOException e) {
		        System.err.println("Exception occurred while writing to the file: " + e.getMessage());
		        e.printStackTrace();
		    } finally {
		        System.out.println("Write CSV String Records to " + filename + " file\n");
		        recordLines.stream().forEach(System.out::println);
		    }
		}

	}
	
	public static class Constants {
		
		public static final Integer REPEAT_CHARACTER_TIMES = 120;
		public static final String REPEAT_CHARACTER = "-";
		public static final String COMMA_DELIMITER = ",";
		
	}
	
	public static class Util {
		
		public static void printSeparator() {
			System.out.println(getSeparatorString());
		}
		
		public static String getSeparatorString() {
			return "\n" + REPEAT_CHARACTER.repeat(REPEAT_CHARACTER_TIMES) + "\n";
		}
		
		public static <T>void sortGenericList(List<T> objectList, Comparator<T> c) {
			objectList.sort(c);
		}
		
		public static <T> List<T> sortGenericListStream(List<T> objectList, Comparator<T> c) {
			return objectList.stream().sorted(c).collect(Collectors.toList());
		}
		
	}
	
	public static class School {
		
		private List<Student> studentList;
		private List<Teacher> teacherList;
		
		public School(List<String> studentStringRecords, List<Teacher> teacherList) {
			this.studentList = new ArrayList<Person.Student>();
			List<Student> studentList = this.getStudentListFromCsvRecords(studentStringRecords);
			studentList.stream().forEach(this::addStudentToStudentList);
			this.teacherList = teacherList;
		}
		
		public List<Student> getStudentList() {
			return studentList;
		}
		
		public List<Teacher> getTeacherList() {
			return teacherList;
		}
		
		public void addStudentToStudentList(Student student) {
			studentList.add(student);
		}
		
		public void addTeacherToTeacherList(Teacher teacher) {
			teacherList.add(teacher);
		}
		
		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("\nSchool\n")
				.append("\nTeacher List");
			this.teacherList.stream().forEach(teacher -> stringBuilder.append(teacher.toString()));
			stringBuilder.append("\n");
			stringBuilder.append("\nStudent List");
			this.studentList.stream().forEach(student -> stringBuilder.append(student.toString()));
			stringBuilder.append("\n");
			return stringBuilder.toString();
		}
		
		public void sortStudentListById() {
//			sortGenericList(getStudentList(), Comparator.comparing(Student::getId));
			studentList = sortGenericListStream(studentList, Comparator.comparing(Student::getId));
		}
		
		public void sortStudentListByFirstName() {
//			sortGenericList(getStudentList(), Comparator.comparing(Student::getFirstName));
			studentList = sortGenericListStream(studentList, Comparator.comparing(Student::getFirstName));
		}
		
		public void sortStudentListByLastName() {
//			sortGenericList(getStudentList(), Comparator.comparing(Student::getLastName));
			studentList = sortGenericListStream(studentList, Comparator.comparing(Student::getLastName));
		}
		
		public void sortStudentListByStudentId() {
//			sortGenericList(getStudentList(), Comparator.comparing(Student::getStudentId));
			studentList = sortGenericListStream(studentList, Comparator.comparing(Student::getStudentId));
		}
		
		public void sortStudentListByGpa() {
//			sortGenericList(getStudentList(), Comparator.comparing(Student::getGpa));
			studentList = sortGenericListStream(studentList, Comparator.comparing(Student::getGpa));
		}
		
		public List<Student> getStudentListFromCsvRecords(List<String> csvRecords) {
			return csvRecords
					.stream()
					.map(Student::csvStringToStudentMapper)
					.collect(Collectors.toList());
		}
		
		public List<String> getCsvRecordsFromStudentList() {
			return this.studentList
					.stream()
					.map(Student::studentToCsvStringMapper)
					.collect(Collectors.toList());
		}
		
	}

	
	public static void demo() {
		
		printSeparator();
		
		String path = "src/resources/assignment4/";
		
		String studentInputFileName = path + "student.csv";
		String studentOutputFileName = path + "student_output.csv";
		
		Teacher teacher = new Teacher(78L, 56, "Tay", "Han", "Jackie", "Chan", 45.98);
		System.out.println("Create Teacher Object\n" + teacher.toString());
		printSeparator();
		
		List<String> csvStudentRecordList = FileUtil.readFromFile(studentInputFileName, false);
		
		School school = new School(csvStudentRecordList, List.of(teacher));
		System.out.println("\nIntitialization\n" + school.toString());
		printSeparator();
		
		FileUtil.writeToFile(
				studentOutputFileName, 
				List.of("ID,Age,First Name,Last Name,Parent First Name,Parent Last Name,Student ID,GPA"), 
				false);
		printSeparator();
		
		school.sortStudentListById();
		System.out.println("Sort Student List By ID\n" + school.toString());
		FileUtil.writeToFile(
				studentOutputFileName, 
				school.getCsvRecordsFromStudentList(),
				true
			);
		printSeparator();
		
		school.sortStudentListByStudentId();
		System.out.println("Sort Student List By Student ID\n" + school.toString());
		FileUtil.writeToFile(
				studentOutputFileName, 
				school.getCsvRecordsFromStudentList(),
				true
			);
		printSeparator();
		
		school.sortStudentListByLastName();
		System.out.println("Sort Student List By Last Name\n" + school.toString());
		FileUtil.writeToFile(
				studentOutputFileName, 
				school.getCsvRecordsFromStudentList(),
				true
			);
		printSeparator();
		
		school.sortStudentListByFirstName();
		System.out.println("Sort Student List By First Name\n" + school.toString());
		FileUtil.writeToFile(
				studentOutputFileName, 
				school.getCsvRecordsFromStudentList(),
				true
			);
		printSeparator();
		
		school.sortStudentListByGpa();
		System.out.println("Sort Student List By GPA\n" + school.toString());
		FileUtil.writeToFile(
				studentOutputFileName, 
				school.getCsvRecordsFromStudentList(),
				true
			);
		printSeparator();
		
// File Input from src/main/files/student.csv:
//		4,23,Loius,Tom,Troy,Austin,5961,3.2
//		1,22,Harry,Styles,Marie,Styles,9834,3.5
//		2,20,Zayn,Malik,Yaser,Malik,3554,3.6
//		3,26,Liam,Payne,Geoff,Payne,4578,3.3
//
//
// File Output to src/main/files/student_output.csv:
//	 	 First Line is header(Line 1), since there are 4 Student Objects in the List:
//				Lines 2-5:   Student List Output Sorted By ID
//				Lines 6-9:   Student List Output Sorted By Student ID
//				Lines 10-13: Student List Output Sorted By Last Name
//				Lines 14-17: Student List Output Sorted By First Name
//				Lines 18-21: Student List Output Sorted By GPA
//
//		 ID,Age,First Name,Last Name,Parent First Name,Parent Last Name,Student ID,GPA
//		 1,22,Harry,Styles,Marie,Styles,9834,3.5
//		 2,20,Zayn,Malik,Yaser,Malik,3554,3.6
//		 3,26,Liam,Payne,Geoff,Payne,4578,3.3
//		 4,23,Loius,Tom,Troy,Austin,5961,3.2
//		 2,20,Zayn,Malik,Yaser,Malik,3554,3.6
//		 3,26,Liam,Payne,Geoff,Payne,4578,3.3
//		 4,23,Loius,Tom,Troy,Austin,5961,3.2
//		 1,22,Harry,Styles,Marie,Styles,9834,3.5
//		 2,20,Zayn,Malik,Yaser,Malik,3554,3.6
//		 3,26,Liam,Payne,Geoff,Payne,4578,3.3
//		 1,22,Harry,Styles,Marie,Styles,9834,3.5
//		 4,23,Loius,Tom,Troy,Austin,5961,3.2
//		 1,22,Harry,Styles,Marie,Styles,9834,3.5
//		 3,26,Liam,Payne,Geoff,Payne,4578,3.3
//		 4,23,Loius,Tom,Troy,Austin,5961,3.2
//		 2,20,Zayn,Malik,Yaser,Malik,3554,3.6
//		 4,23,Loius,Tom,Troy,Austin,5961,3.2
//		 3,26,Liam,Payne,Geoff,Payne,4578,3.3
//		 1,22,Harry,Styles,Marie,Styles,9834,3.5
//		 2,20,Zayn,Malik,Yaser,Malik,3554,3.6
//
//
// Console Output:		
//				============Main Execution Start===================
//
//
//
//				------------------------------------------------------------------------------------------------------------------------
//
//				Create Teacher Object
//
//				Teacher	78	56	Tay	Han	Jackie	Chan	45.98	
//
//				------------------------------------------------------------------------------------------------------------------------
//
//				Read CSV String Records from src/main/files/student.csv file
//
//				4,23,Loius,Tom,Troy,Austin,5961,3.2
//				1,22,Harry,Styles,Marie,Styles,9834,3.5
//				2,20,Zayn,Malik,Yaser,Malik,3554,3.6
//				3,26,Liam,Payne,Geoff,Payne,4578,3.3
//
//				Intitialization
//
//				School
//
//				Teacher List
//				Teacher	78	56	Tay	Han	Jackie	Chan	45.98	
//
//				Student List
//				Student	4	23	Loius	Tom	Troy	Austin	5961	3.2	
//				Student	1	22	Harry	Styles	Marie	Styles	9834	3.5	
//				Student	2	20	Zayn	Malik	Yaser	Malik	3554	3.6	
//				Student	3	26	Liam	Payne	Geoff	Payne	4578	3.3	
//
//
//				------------------------------------------------------------------------------------------------------------------------
//
//				Write CSV String Records to src/main/files/student_output.csv file
//
//				ID,Age,First Name,Last Name,Parent First Name,Parent Last Name,Student ID,GPA
//
//				------------------------------------------------------------------------------------------------------------------------
//
//				Sort Student List By ID
//
//				School
//
//				Teacher List
//				Teacher	78	56	Tay	Han	Jackie	Chan	45.98	
//
//				Student List
//				Student	1	22	Harry	Styles	Marie	Styles	9834	3.5	
//				Student	2	20	Zayn	Malik	Yaser	Malik	3554	3.6	
//				Student	3	26	Liam	Payne	Geoff	Payne	4578	3.3	
//				Student	4	23	Loius	Tom	Troy	Austin	5961	3.2	
//
//				Write CSV String Records to src/main/files/student_output.csv file
//
//				1,22,Harry,Styles,Marie,Styles,9834,3.5
//				2,20,Zayn,Malik,Yaser,Malik,3554,3.6
//				3,26,Liam,Payne,Geoff,Payne,4578,3.3
//				4,23,Loius,Tom,Troy,Austin,5961,3.2
//
//				------------------------------------------------------------------------------------------------------------------------
//
//				Sort Student List By Student ID
//
//				School
//
//				Teacher List
//				Teacher	78	56	Tay	Han	Jackie	Chan	45.98	
//
//				Student List
//				Student	2	20	Zayn	Malik	Yaser	Malik	3554	3.6	
//				Student	3	26	Liam	Payne	Geoff	Payne	4578	3.3	
//				Student	4	23	Loius	Tom	Troy	Austin	5961	3.2	
//				Student	1	22	Harry	Styles	Marie	Styles	9834	3.5	
//
//				Write CSV String Records to src/main/files/student_output.csv file
//
//				2,20,Zayn,Malik,Yaser,Malik,3554,3.6
//				3,26,Liam,Payne,Geoff,Payne,4578,3.3
//				4,23,Loius,Tom,Troy,Austin,5961,3.2
//				1,22,Harry,Styles,Marie,Styles,9834,3.5
//
//				------------------------------------------------------------------------------------------------------------------------
//
//				Sort Student List By Last Name
//
//				School
//
//				Teacher List
//				Teacher	78	56	Tay	Han	Jackie	Chan	45.98	
//
//				Student List
//				Student	2	20	Zayn	Malik	Yaser	Malik	3554	3.6	
//				Student	3	26	Liam	Payne	Geoff	Payne	4578	3.3	
//				Student	1	22	Harry	Styles	Marie	Styles	9834	3.5	
//				Student	4	23	Loius	Tom	Troy	Austin	5961	3.2	
//
//				Write CSV String Records to src/main/files/student_output.csv file
//
//				2,20,Zayn,Malik,Yaser,Malik,3554,3.6
//				3,26,Liam,Payne,Geoff,Payne,4578,3.3
//				1,22,Harry,Styles,Marie,Styles,9834,3.5
//				4,23,Loius,Tom,Troy,Austin,5961,3.2
//
//				------------------------------------------------------------------------------------------------------------------------
//
//				Sort Student List By First Name
//
//				School
//
//				Teacher List
//				Teacher	78	56	Tay	Han	Jackie	Chan	45.98	
//
//				Student List
//				Student	1	22	Harry	Styles	Marie	Styles	9834	3.5	
//				Student	3	26	Liam	Payne	Geoff	Payne	4578	3.3	
//				Student	4	23	Loius	Tom	Troy	Austin	5961	3.2	
//				Student	2	20	Zayn	Malik	Yaser	Malik	3554	3.6	
//
//				Write CSV String Records to src/main/files/student_output.csv file
//
//				1,22,Harry,Styles,Marie,Styles,9834,3.5
//				3,26,Liam,Payne,Geoff,Payne,4578,3.3
//				4,23,Loius,Tom,Troy,Austin,5961,3.2
//				2,20,Zayn,Malik,Yaser,Malik,3554,3.6
//
//				------------------------------------------------------------------------------------------------------------------------
//
//				Sort Student List By GPA
//
//				School
//
//				Teacher List
//				Teacher	78	56	Tay	Han	Jackie	Chan	45.98	
//
//				Student List
//				Student	4	23	Loius	Tom	Troy	Austin	5961	3.2	
//				Student	3	26	Liam	Payne	Geoff	Payne	4578	3.3	
//				Student	1	22	Harry	Styles	Marie	Styles	9834	3.5	
//				Student	2	20	Zayn	Malik	Yaser	Malik	3554	3.6	
//
//				Write CSV String Records to src/main/files/student_output.csv file
//
//				4,23,Loius,Tom,Troy,Austin,5961,3.2
//				3,26,Liam,Payne,Geoff,Payne,4578,3.3
//				1,22,Harry,Styles,Marie,Styles,9834,3.5
//				2,20,Zayn,Malik,Yaser,Malik,3554,3.6
//
//				------------------------------------------------------------------------------------------------------------------------
//
//
//
//				============Main Execution End===================

		
	}

}
