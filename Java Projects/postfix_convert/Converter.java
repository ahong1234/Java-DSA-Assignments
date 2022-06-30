/**
 *  Name: Alexander Hong
 *  Description: A class to take input from the text-field of the ConverterGUI class and convert it to post-fix or pre-fix notation.
 *  First the expression is converted into an array of strings. Operands and operators do not have to be separated by a space
 *  but operands must be separated by a space when entered consecutively. Using this list, stacks are populated depending on
 *  whether the token is an operator or not and converted expressions are placed at the top of the operand stack with a space between all elements.
 *  The result is popped from the operand stack and returned to the GUI class for placement in the "result" text-field.
 *  Error messages are displayed when: the program tries to pop and empty stack, when there are still elements in the stack 
 *  that haven't been addressed, or when the user submits a blank string. 
 */


import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.*;

public class Converter {	
	
	public static String fromPreToPost(String expression) throws SyntaxError, IOException  {
		// instantiate two stacks needed for the conversion
		Stack<String> reversalStack = new Stack<String>();
		Stack<String> operandStack = new Stack<String>();
		if (!expression.equals("")) {
			// convert the expression into an array of strings 
			List<String> expressionStringArray = tokenize(expression);
			// when you push all items from the array into the stack then they will be in "reverse" order than how they're placed
			for (String token1: expressionStringArray ) {
				reversalStack.push(token1);
			}
			// go through the reversal stack and push operands onto operand stack
				while (!reversalStack.empty()) {
					String token1 = reversalStack.pop();
					if (!isOperator(token1)) {
						operandStack.push(token1);
					}
					// if the an operator is found then pop 2 operands, concatenate them before the operator for post-fix notation
					else {
						try {
							String op1 = operandStack.pop();
							String op2 = operandStack.pop();
							String post = op1 + " " + op2 + " " + token1;
							// push the created string back onto the operand stack
							operandStack.push(post);
						}
						// catch the empty stack exception and display error message in a new window
						catch (EmptyStackException ex) {
							throw new SyntaxError("Trying to call pop on an empty stack. Please check expression.");
						}
					}		
				}		
				String result =  operandStack.pop();
				// return the concatenated string
				if (operandStack.empty()) {
					return result;
				}	
				// if there are still elements in the stack - that could mean the expression is not valid
				else if (!operandStack.empty()) { // else throw new exception
					throw new SyntaxError("Stack hasn't been emptied. Please check expression.");
				}
		}
		// if you put in a blank string then it will detect it and open up a new error window
		else {
			throw new SyntaxError("Nothing entered. Try again");
		}
	
		// return the updated expression
		return operandStack.pop();
		
	}
	
	public static String fromPostToPre(String expression) throws SyntaxError, IOException  {
		// only one stack needed for post-fix to pre-fix as shown by the provided example
		Stack<String> operandStack = new Stack<String>();
		// check to see if the expression is blank - show error message if it is
		if (!expression.equals("")) {
			// convert the expression into an array of strings using the tokenize method
			List<String> expressionStringArray  = tokenize(expression);
			for (String token1: expressionStringArray ) {
				//
				if (token1 == " ") {
					continue;
				}
				else if(!isOperator(token1)) {
					operandStack.push(token1);
				}
				// if the an operator is found then pop 2 operands, concatenate them after the operator for pre-fix notation
				else if (isOperator(token1)) {
					try {
					String op1 = operandStack.pop();
					String op2 = operandStack.pop();
					// create new string and push back onto operand stack
					String post = token1 + " " + op2 + " " + op1;
					operandStack.push(post);
					}
					// catch the empty stack exception and display error message in a new window
					catch (EmptyStackException ex) {
						throw new SyntaxError("Trying to call pop on an empty stack. Please check expression.");
					}
				}		
			}
			
				String result =  operandStack.pop();	
				// if there are still elements in the stack - that could mean the expression is not valid
				if (!operandStack.empty()) { 
					throw new SyntaxError("Stack hasn't been emptied. Please check expression.");
				}
				else {
					return result;
				}
		}	
		// detect if user entered a blank string and display error message 
		else  {
			throw new SyntaxError("Nothing entered. Try again");
		}	
	}
	
	// convert the expression the user input into a list of strings to be handled by the converters
	private static List<String> tokenize(String expression) throws IOException {
		// identify chars without need for spacing between operands and operators
		StreamTokenizer tokenizeExpression = new StreamTokenizer(new StringReader(expression));
		// define the minus and division sign as ordinary chars
		tokenizeExpression.ordinaryChar('-');
		tokenizeExpression.ordinaryChar('/');
		
		// store the parsed strings into a list
		List<String> tokenList = new ArrayList<>();
		
		while (tokenizeExpression.nextToken() != StreamTokenizer.TT_EOF){  // keep taking tokens until EOF - end of file
			if (tokenizeExpression.ttype == StreamTokenizer.TT_NUMBER) { // operand numbers are separated by spaces
				// recognize an integer and parse it as a string into the token list
				tokenList.add(String.valueOf((int)tokenizeExpression.nval));
			}
			else if(tokenizeExpression.ttype == StreamTokenizer.TT_WORD) { // operands can be letters or words separated by spaces
				tokenList.add(tokenizeExpression.sval);
			}
			else { // operators don't need to be separated they are recognized and added to tokenList
				tokenList.add(String.valueOf((char) tokenizeExpression.ttype));
			}
		}
		return tokenList;
	}
	
	// compare strings and return true if they match an operator: +, -, / , *
	static boolean isOperator(String x) {
        switch (x) {
            case "+":    		
            case "-":
            case "/":
            case "*":
            	
            return true;
        }
        return false;
    }

	
}