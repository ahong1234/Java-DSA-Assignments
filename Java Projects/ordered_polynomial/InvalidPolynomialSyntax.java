/* Name: Alexander Hong
 * Description: Unchecked exception class 
 */

public class InvalidPolynomialSyntax extends RuntimeException {
	private static final long serialVersionUID = 1L;
	InvalidPolynomialSyntax(String msg) {
		super(msg);
	}

	public InvalidPolynomialSyntax() {
		super();
	}
	
	
}

