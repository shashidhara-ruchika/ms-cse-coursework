package edu.neu.csye6200.classwork.swing;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple GUI with a button, text fields and text area
 * @author dpeters
 *
 */
public class DemoSimpleGUI {
	public DemoSimpleGUI() {
		JFrame frame = new JFrame();
		
		// exit program, don't just hide window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);	// width,height
		
		// create text field for data entry
		JTextField aTextField = new JTextField();
		aTextField.setBounds(275,25, 200,40);	// x,y,width,height
		frame.add(aTextField);
		
		// create text field for data entry
		JTextField bTextField = new JTextField();
		bTextField.setBounds(575,25, 200,40);	// x,y,width,height
		frame.add(bTextField);
		
		// create text area (in scroll pane) for data display
		JTextArea textArea = new JTextArea("", 5, 20);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(25, 100, 300, 200);	// x,y,width,height
		frame.add(scrollPane);
		
		/*
		 * create buttons for calculator
		 */
		JButton addButton = new JButton("Click Here");
		addButton.setBounds(25, 25, 100, 40);	// x,y,width,height
		addButton.addActionListener( (ActionEvent e) -> {
			textArea.setText("Ouch!!! Don't click so HARD!!!" + " " + aTextField.getText() + " " + bTextField.getText());
		} );
		frame.add(addButton);// adding button on frame
		
		frame.setLayout(null);	// no layout manager
		frame.setVisible(true);	// done last
	}	
	public static void demo() {
		// instantiate object to display form
		new DemoSimpleGUI();
	}
}
