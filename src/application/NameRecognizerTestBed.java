package application;

import java.util.Scanner;

/*******
 * The mainline of a console terminal implementation of a name
 * Recognizer testbed
 */

public class NameRecognizerTestBed{
	
	static String inputLine;

	public static void main(String[] args) {

		System.out.println("Welcome to the Name Recognizer Testbed\n");
        System.out.println("Please enter a name or an empty line to stop.");

		// Associate the system keyboard with a Scanner object
		Scanner keyboard = new Scanner(System.in);
		// As long as there is a next line, read it 
		while (keyboard.hasNextLine()) {
			inputLine = keyboard.nextLine();		// Fetch the next line
			if (inputLine.length() == 0) {			// If the length of the trimmed line is zero, stop the loop
				System.out.println("\n*** Empty input line detected, the loop stops.");
				keyboard.close();					// Display the reason for terminating the loop.
				System.exit(0);
			}
			// Input has been provided, check if name is valid or not
 			String errMessage = NameRecognizer.checkForValidName(inputLine);
 			
 			// If errMessage empty, there is no error
			if (errMessage != "") {
				
				// Not empty, display error message
				System.out.println(errMessage);
				
				// Fetch the index where the processing of the input stopped
				if (NameRecognizer.nameRecognizerIndexofError <= -1) return;	// Should never happen
				// Display the input line so the user can see what was entered		
				System.out.println(inputLine);
				// Display the line up to the error and the display an up arrow
				System.out.println(inputLine.substring(0,NameRecognizer.nameRecognizerIndexofError) + "\u21EB");
			}
			else {
				
				System.out.println("Success! The name is valid.");
			}
			
	        System.out.println("\nPlease enter name or an empty line to stop.");

		}
	}
}
