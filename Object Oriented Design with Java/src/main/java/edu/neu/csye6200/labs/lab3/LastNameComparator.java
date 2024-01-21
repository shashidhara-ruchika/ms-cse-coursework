package edu.neu.csye6200.labs.lab3;

import java.util.Comparator;

class LastNameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getLastName().compareTo(s2.getLastName());
    }
}
