/* Name: Alexander Hong
 * Description: Define the binary tree, nodes, and the operations to
 * create a tree and return information about it.
 * 
 */

package binary_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class BinaryTree {
	
    private final Node root;
    
    // create tree from string
    public BinaryTree(final String input) throws InvalidTreeSyntax {
    	// iterate through the input string
        final PrimitiveIterator.OfInt charsIterator = input.chars().iterator();
        if (charsIterator.hasNext() && charsIterator.nextInt() == '(') {
            root = readNode(charsIterator);
        } else {
            throw new InvalidTreeSyntax("incorrect input: " + input);
        }
    }
    
    // display the tree in prefix parenthesized format
    @Override
    public String toString() {
        return root + "";
    }
    
    // check if the absolute difference between the height of its left and right subtrees is at most 1.
    public boolean isBalanced() { 
    	return recIsBalanced(this.root); 
    }
   
    private boolean recIsBalanced(Node root){
	    //base case
	    if (root == null) {
	    	return true;
	    }
	    // return true if the absolute difference is at most 1
	    return (Math.abs(recHeight(root.left) - recHeight(root.right)) <= 1) &&
	    (recIsBalanced(root.left) && recIsBalanced(root.right)); // and calls recursively
    }
    
    // recursive method to see if binary tree has max Nodes available for its height
    public boolean isFull() {
    	return recIsFull(this.root, recHeight(this.root), 0); 
    }
    
    private boolean recIsFull(Node root, int height, int index) {
	    if (root == null){ 
	    	return true; 
	    }
	    // check if height matches the index 
	    if (root.left == null && root.right == null) { 
	    	return (height == index + 1); 
	    }
	    // one child empty
	    if (root.left == null || root.right == null){ 
	    	return false;
	    }
	    //recursive call to both children
	    return recIsFull(root.left, height, index+1) && recIsFull(root.right, height, index+1);
    }
    
    // display if the binary tree is proper (every node has either 0 or 2 children)
    public boolean isProper() { 
    	return recIsProper(this.root); 
    } 
    
    private boolean recIsProper(Node root) {
    // base case
    if(root == null) {
    	return true;
    }
    
    //returns true or false based on two or zero children
    return ((root.left != null || root.right == null) && (root.left == null || root.right != null))
    && (recIsProper(root.left) && recIsProper(root.right)); 
    }
    
    // return integer of the height of Tree 
    public int height() {
	  return recHeight(this.root)-1; 
    }
    // root starts at 0
    private int recHeight(Node root) {
    // conditional statement. return the max height of left or right tree
    return (root == null) ? 0 : 1 + Math.max(recHeight(root.left), recHeight(root.right));
    }
    
    
    // display the number of nodes 
    public int nodes() { 
    	return recNodes(this.root); 
    }
    private int recNodes(Node root) {
    	return (root == null) ? 0 : 1 + recNodes(root.left) + recNodes(root.right);
    }
    
    
    // display tree in order - left sub-tree, root node, right sub-tree
    public String inOrder() {
    	return recInOrder(this.root);
    }
    private String recInOrder(Node root) {
    	return (root == null) ? "" : "(" + recInOrder(root.left) + root.getInfo() + recInOrder(root.right) + ")";
    }
    
    
    // define the Node
    public static class Node {
        private final String data;
        private final Node left;
        private final Node right;
        //constructor
        public Node(final String data, final List<Node> subNodes) {
            this.data = data;
            this.left = subNodes.size() > 0 ? subNodes.get(0) : null;
            this.right = subNodes.size() > 1 ? subNodes.get(1) : null;
        }
        // get the string contents of the Node
        public String getInfo() {
			// TODO Auto-generated method stub
			return this.data;
		}
        
        // display the Node and children 
		@Override
        public String toString() {
            return "(" + data + emptyIfNull(left) + emptyIfNull(right) + ")";
        }
		// check if a child node is null and display it as an empty string
        private String emptyIfNull(final Node node) {
            return node == null ? "" : node.toString();
        }
    }
    
    // process the input string, create and assign child nodes 
    private static Node readNode(final PrimitiveIterator.OfInt charsIterator) throws InvalidTreeSyntax {
        final StringBuilder value = new StringBuilder();
        // store data in an array list with initial capacity of 2
        final List<Node> subNodes = new ArrayList<>(2);
        while (charsIterator.hasNext()) {
            int ch = charsIterator.nextInt();
            if (ch == '(') {
            	// within the parentheses look for data to assign as sub nodes
                if (subNodes.size() < 2) { // parent can only have up to 2 children
	                    final Node res = readNode(charsIterator);
	                    subNodes.add(res);         
                } else {
                    throw new InvalidTreeSyntax("Tree cannot have more than 2 child nodes.");
                }
            } else if (ch == ')') {
                return new Node(value.toString(), subNodes);
            } else {
                value.append((char) ch);
            }
        }
        throw new InvalidTreeSyntax("Missing closed parenthesis at: " + value.toString());
    }
}