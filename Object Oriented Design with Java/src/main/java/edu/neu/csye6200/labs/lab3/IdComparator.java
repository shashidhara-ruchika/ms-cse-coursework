package edu.neu.csye6200.labs.lab3;

import java.util.Comparator;

public class IdComparator implements Comparator<Student> {
	@Override
    public int compare(Student s1, Student s2) {
        return Long.compare(s1.getId(), s2.getId());
    }

}