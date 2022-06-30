/* Name: Alexander Hong
 * Description: Create the graphical user interface and assign buttons to methods.
 * Display the GUI and process user input to perform binary tree operations. 
 * 
 */

package binary_tree;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*; 

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;  

	public static void main(String[] args) {
		GUI newGUI = new GUI();
		newGUI.setVisible(true);
	}	
	// buttons, labels and text-fields 
	private JLabel prompt = new JLabel("Enter Tree: ");
	private JTextField treeInput = new JTextField(20);
	private JButton make = new JButton("Make Tree");
	private JButton isBalanced = new JButton("Is Balanced?");
	private JButton isFull = new JButton("Is Full?");
	private JButton isProper = new JButton("Is Proper?");
	private JButton height = new JButton("Height");
	private JButton nodes = new JButton("Nodes");
	private JButton inorder = new JButton("Inorder");
	private JLabel OutputLbl = new JLabel("Output: ");
	private JTextField output = new JTextField(20);
	
	// set the buttons to perform the desired conversion when clicked
	private final ActionListener evaluator = event -> {	
		String input = treeInput.getText();	
		try {
			if (input == "" || input.isEmpty()) {
					throw new InvalidTreeSyntax("no input given");
				}
			BinaryTree tree = new BinaryTree(input);
			if(event.getSource() == make) {
				
				output.setText("Tree created. " + tree.toString());	
			}
			if(event.getSource() == isBalanced) {
				if (tree.isBalanced()) {
					output.setText("Tree is balanced");
				}
				else if (!tree.isBalanced()) {
					output.setText("Tree not balanced");
				}
			}
			if(event.getSource() == isFull) {
				if (tree.isFull()) {
					output.setText("Tree is full");
				}
				else if (!tree.isFull()) {
					output.setText("Tree not full");
				}
			}
			if(event.getSource() == isProper) {
				if (tree.isProper()) {
					output.setText("Tree is proper");
				}
				else if (!tree.isProper()) {
					output.setText("Tree not proper");
				}
			}
			if(event.getSource() == height) {
				int height = tree.height();
				output.setText("" + height);
			}
			if(event.getSource() == nodes) {
				int Nodes = tree.nodes();
				output.setText("" + Nodes);
			}
			if(event.getSource() == inorder) {
				String inOrder = tree.inOrder();
				output.setText(inOrder);
			}
		}	
		catch (InvalidTreeSyntax e) {
			JOptionPane.showMessageDialog(getParent(), e.getMessage());
		}	
		};	
	
	public GUI() {
		super("Binary Tree");
		setSize(750, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// use grid layout
		setLayout(new GridLayout(3, 0));
		
		// add input field
		JPanel top = new JPanel();	
		prompt.setSize(200, 800);
		top.add(prompt);
		top.add(treeInput);
		add(top);
		
		// add buttons to middle of frame
		JPanel middle = new JPanel();
		middle.add(make);
		make.addActionListener(evaluator);
		middle.add(isBalanced);
		isBalanced.addActionListener(evaluator);
		middle.add(isFull);
		isFull.addActionListener(evaluator);
		middle.add(isProper);
		isProper.addActionListener(evaluator);
		middle.add(height);
		height.addActionListener(evaluator);
		middle.add(nodes);
		nodes.addActionListener(evaluator);
		middle.add(inorder);
		inorder.addActionListener(evaluator);
		add(middle);
			
		// display the results
		JPanel bottom = new JPanel();
		bottom.add(OutputLbl);
		bottom.add(output);
		add(bottom);
	}			

	
}

