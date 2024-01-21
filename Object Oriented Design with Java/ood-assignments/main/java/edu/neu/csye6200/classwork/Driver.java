package edu.neu.csye6200.classwork;

import edu.neu.csye6200.classwork.lamdasort.LambdaSort;
import edu.neu.csye6200.classwork.swing.Calculator;
import edu.neu.csye6200.classwork.swing.DemoSimpleGUI;
import edu.neu.csye6200.classwork.threads.Threads;
import edu.neu.csye6200.classwork.udp.SimpleSwingDemo;

public class Driver {
	public static void main(String[] args) {
		System.out.println("============Main Execution Start===================\n\n");

//        LambdaSort.demo();
//		Calculator.demo();
//		DemoSimpleGUI.demo();
//		SimpleSwingDemo.demo();
		Threads.demo();
		
		
		 
		System.out.println("\n\n============Main Execution End===================");
	}
}
