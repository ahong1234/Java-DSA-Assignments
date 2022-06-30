/**
 *  Name: Alexander Hong
 *  Description: A Class to define and launch a graphical user interface for the expression converter.
 *  The user can enter an expression, select which kind of conversion they want through pushing 
 *  the labeled button, and read the result displayed at the bottom. Error messages are opened in a new window 
 *  which tells the user which kind of error is being encountered. 
 */


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

public class ConverterGUI extends JFrame {
	
	// define labels, text-fields for input, and buttons for conversion type
	private JLabel expressionPrompt = new JLabel("Input Expression ");
	private JTextField input = new JTextField(20);
	private final JButton preToPost = new JButton("Prefix to Postfix");
	private final JButton postToPre = new JButton("Postfix to Prefix");
	private JLabel outputLabel = new JLabel("Result");
	private JTextField output = new JTextField(20);
	
	
	// set the buttons to perform the desired conversion when clicked
	private final ActionListener evaluator = event -> {
		try {
			String expression = input.getText();
			if(event.getSource() == postToPre) {
				String postToPreResult = Converter.fromPostToPre(expression);
				output.setText(postToPreResult);
			}
			if(event.getSource() == preToPost) {
				String preToPostResult = Converter.fromPreToPost(expression);
				output.setText(preToPostResult);
			}
		}	
		// errors are displayed in a new window pop-up
		catch (SyntaxError | IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 
	};
	
	// use GridLayout as layout
	public ConverterGUI() {
		// title
		super("Prefix and Postfix Converter");
		// size
		setSize(400, 150);
		// exit when red x is pressed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// use grid layout
		setLayout(new GridLayout(3, 1));
		
		// top panel - the user's expression is entered here 
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.add(expressionPrompt);
		panel1.add(input);
		add(panel1);
		
		// middle panel - the conversion is executed when a button is pressed
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(preToPost);
		panel2.add(postToPre);
		preToPost.addActionListener(evaluator);
		postToPre.addActionListener(evaluator);
		add(panel2);
		
		
		// bottom panel - display the converted expression here
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		panel3.add(outputLabel);
		panel3.add(output);
		add(panel3);		
	}
	
	// driver code for the GUI
	public static void main(String[] args) {
		ConverterGUI frame = new ConverterGUI();
		frame.setVisible(true);	
	}
}