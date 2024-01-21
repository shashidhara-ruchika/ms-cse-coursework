package edu.neu.csye6200.classwork.swing;
import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
 private JTextField textField;
 private JButton[] numberBtns = new JButton[10];
 private JButton addBtn, subBtn, mulBtn, divBtn, eqBtn, clsBtn;
 private JPanel panel;
 
 
 private double n1, n2, res;
 private char operator;
 
 public Calculator() {
	 setTitle("Calculator");
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 setSize(400,400);
	 setLocationRelativeTo(null);
	 
	 textField = new JTextField();
	 textField.setPreferredSize(new Dimension(400, 50));
	 textField.setFont(new Font("Arial", Font.ITALIC, 20));
	 textField.setHorizontalAlignment(JTextField.RIGHT);
	 textField.setEditable(false);
	 
	 panel = new JPanel();
	 panel.setLayout(new GridLayout(4,4,10,10));
	 
	 for(int i = 0; i < 10; i++) {
		 numberBtns[i] = new JButton(String.valueOf(i));
		 numberBtns[i].setFont(new Font("Arial", Font.BOLD, 18));
		 numberBtns[i].addActionListener((n) -> textField.setText(textField.getText() + ((JButton) n.getSource()).getText()));
	 }
	 
	 addBtn = createBtn("+");
	 subBtn = createBtn("-");
	 divBtn = createBtn("/");
	 mulBtn = createBtn("*");
	 eqBtn = createBtn("=");
	 clsBtn = createBtn("c");
	 
	 addBtn.addActionListener( a -> OpBtnClick('+'));
	 subBtn.addActionListener( a -> OpBtnClick('-'));
	 divBtn.addActionListener( a -> OpBtnClick('/'));
	 mulBtn.addActionListener( a -> OpBtnClick('*'));
	 eqBtn.addActionListener( a -> calRes());
	 clsBtn.addActionListener( a -> textField.setText(""));
	 
	 panel.add(numberBtns[1]);
	 panel.add(numberBtns[2]);
	 panel.add(numberBtns[3]);
	 panel.add(addBtn);
	 
	 panel.add(numberBtns[4]);
	 panel.add(numberBtns[5]);
	 panel.add(numberBtns[6]);
	 panel.add(subBtn);
	 
	 panel.add(numberBtns[7]);
	 panel.add(numberBtns[8]);
	 panel.add(numberBtns[9]);
	 panel.add(mulBtn);
	 
	 panel.add(numberBtns[0]);
	 panel.add(clsBtn);
	 panel.add(eqBtn);
	 panel.add(divBtn);
	 
	 setLayout(new BorderLayout());
	 add(textField, BorderLayout.NORTH);
	 add(panel, BorderLayout.CENTER);
	 
	 
	 
 }

 private JButton createBtn(String label) {
	 JButton btn = new JButton(label);
	 btn.setFont(new Font("Arial", Font.PLAIN, 18));
	 return btn;
 }
 
 private void OpBtnClick(char op) {
	 n1 = Double.parseDouble(textField.getText());
	 operator = op;
	 textField.setText("");
 }
 
 private void calRes() {
	 n2 = Double.parseDouble(textField.getText());
	 
	 switch (operator) {
	 case '+':
		 res = n1 + n2;
		 break;
	 case '-':
		 res = n1 - n2;
		 break;
	 case '*':
		 res = n1 * n2;
		 break;
	 case '/':
		 if(n2 != 0) {
			 res = n1 / n2;
		 }
		 else {
			 textField.setText("Error");
			 return;
		 }
		 break;
	 }
	 
	 textField.setText(String.valueOf(res));
	 
 }
 
 public static void demo() {
	 SwingUtilities.invokeLater(() -> {
		 Calculator cal = new Calculator();
		 cal.setVisible(true);
	 });
 }
 
 
}
