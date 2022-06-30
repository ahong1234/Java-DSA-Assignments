/* Name: Alexander Hong
 * Description: Define the InvalidTreeSyntax exception 
 * that is thrown when a binary tree is entered incorrectly.
 * 
 */

package binary_tree;

public class InvalidTreeSyntax extends Exception{

	private static final long serialVersionUID = 1L;
	public InvalidTreeSyntax() {
		super();
	}
	public InvalidTreeSyntax(String message) {
		super(message);
	}
}