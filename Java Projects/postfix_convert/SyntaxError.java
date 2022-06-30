/**
 *  Name: Alexander Hong
 *  Description: A Class to define an exception called Syntax Error which is thrown 
 *  when incorrect syntax is input by the user into the converter.
 */


class SyntaxError extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SyntaxError() { 
		super(); 
	}
	
	public SyntaxError(String message){ 
		super(message); 
	}
}
