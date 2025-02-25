package application;

import java.util.Scanner;

/*******
 * The mainline of a console terminal implementation of a name
 * Recognizer testbed
 */

public class NameRecognizerTestBed{
	
	static int numPassed = 0;	// Counter of the number of passed tests
	static int numFailed = 0;	// Counter of the number of failed tests
	
	public static void main(String[] args) {
		/************** Test cases semi-automation report header **************/
		System.out.println("\nName Testing Automation");
		
		/************** Start of the test cases **************/
		performTestCase(1, "charly", true);
		performTestCase(2, "james1", false);
		performTestCase(3, "John colt", true);
		performTestCase(4, "james-richard", true);
		performTestCase(5, "",false);
		performTestCase(6, "a",false);
		performTestCase(7, "charlycharlycharlycharlycharlycharlycharlycharlycharlycharlycharlyvcharlycharly",false);
		performTestCase(8, "charl ",false);
		performTestCase(9, "charl-",false);
		/************** End of the test cases **************/
		
		/************** Test cases semi-automation report footer **************/
		System.out.println("____________________________________________________________________________");
		System.out.println();
		System.out.println("Number of tests passed: "+ numPassed);
		System.out.println("Number of tests failed: "+ numFailed);
	}
	
	public static void performTestCase (int testCase, String content, boolean expectedPass) {
		System.out.println("____________________________________________________________________________\n\nTest case: " + testCase);
		System.out.println("Contents input: \"" + content + "\"");
		
		/************** Call the recognizer to process the input **************/
		String resultText = NameRecognizer.checkForValidName(content);
		/************** Interpret the result and display that interpreted information **************/
		if (resultText != "") {
			
			if (expectedPass) {
				System.out.println("***Failure*** The email <" + content + "> is invalid." + 
						"\nBut it was supposed to be valid, so this is a failure!\n");
				System.out.println("Error message: " + resultText);
				numFailed++;
			}
			// If the test case expected the test to fail then this is a success
			else {			
			System.out.println("***Success*** The email <" + content  + "> is invalid." + 
									"\nBut it was supposed to be invalid, so this is a pass!\n");
			System.out.println("Error message: " + resultText);
			numPassed++;
						
			}
		} else { 
			// If the test case expected the test to pass then this is a success
		if (expectedPass) {	
			System.out.println("***Success*** The email <" + content + "> is valid, so this is a pass!");
					numPassed++;
				}
						// If the test case expected the test to fail then this is a failure
			else {
			System.out.println("***Failure*** The email <" + content + 
					"> was judged as valid" + 
				"\nBut it was supposed to be invalid, so this is a failure!");
							numFailed++;
			}
			
		}
		
	}
}
