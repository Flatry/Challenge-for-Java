/**
 * 
 */
package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomas
 *
 */
public class MainClass {

	public static void main(String[] args) throws FileNotFoundException {
		
		ValidationCheck a1 = new ValidationCheck();
		a1.validateAllFromFile("data//Entries.txt");
		

	}

}
