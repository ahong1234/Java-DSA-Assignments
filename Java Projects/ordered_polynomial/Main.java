/* Name: Alexander Hong
 * Description: Display window for user file selection. Process the file and display 
 * the order of the polynomials whether they are strongly or weakly ordered. 
 */

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
	private static List<Polynomial> polyList = new ArrayList<>();
	public static void main(String[] args) {
		processPolyList();	
		for (Polynomial poly: polyList) {
			System.out.println(poly);
		}
	
	}
	
	public static ArrayList<String> fromFile() throws FileNotFoundException {
		// Create list of expressions and JFileChooser
		ArrayList<String> expressionList = new ArrayList<>();
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir"))); // use current directory 
		int response = chooser.showOpenDialog(null);
		if (response == JFileChooser.APPROVE_OPTION){
			try {
				File file = chooser.getSelectedFile();
			
				Scanner fileIn = new Scanner(file);
				if (file.isFile()){
					while (fileIn.hasNextLine()){
						String expression = fileIn.nextLine();
						expressionList.add(expression);
					}
				}
			}
			catch (NoSuchElementException nse) {
				JOptionPane.showMessageDialog(null,"File is empty!");
			}
			catch(FileNotFoundException fne) {
				JOptionPane.showMessageDialog(null, "File is not found!");
			}
		}
		return expressionList;
	}
	
	public static void processPolyList() {
		try {
			ArrayList<String> a = fromFile();
			for (String element: a) {
				try {
					Polynomial p = new Polynomial(element);
					polyList.add(p);
				
					
				} catch (InvalidPolynomialSyntax e) {
					JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),e.getMessage());
				}			
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* Call to check sorted for the Strong order check */
		System.out.println("Strong Ordered: " + OrderedList.checkSorted(polyList));
		/* Check for Weak order (exponents only) */
		System.out.println("Weak Ordered: " + checkWeakOrder(polyList));
		
	}
	
	public static boolean checkWeakOrder(List<Polynomial> polyList){
		boolean isWeakOrder = true;
		Polynomial previous = polyList.get(polyList.size()-1);
		for(int i = polyList.size()-2; i >= 0; i--){
			if (previous.compareExponents(polyList.get(i)) < 0) {
				
				isWeakOrder = false;			
			}
			previous = polyList.get(i);		
		}
		return isWeakOrder;
		}		
	}