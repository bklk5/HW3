package application;

/**
 * Enhanced automated test class for validating answers.
 * It performs multiple test cases, logs the results, and generates a detailed report.
 * The additional output includes the Percentage of passed cases.
 * 
 * @author Ngoc Minh Thu Le
 * @version 3.0
 */

import java.util.Scanner;

public class EmailRecognizerTestBed {
	/** Counts of the number of passed testcases*/
	static int numPassed = 0;	
	/** Counts of the number of failed  testcases*/
	static int numFailed = 0;	
	
	/*
	 * This mainline displays a header to the console, performs a sequence of
	 * test cases, and then displays a footer with a summary of the results
	 */
	
	public static void main(String[] args) {
		/************** Test cases semi-automation report header **************/
		System.out.println("\nEmail Testing Automation");
		
		/************** Start of the test cases **************/
		performTestCase(1, "sadw@asu.edu", true);
		performTestCase(2, "", false);
		performTestCase(3, "adwad@", false);
		performTestCase(4, " dWQEW@ewfe.foewfewfowefewofewofw", false);
		performTestCase(5, "!0982139219-0-02=1",false);
		performTestCase(6, "adw@asu.school",false);
		/************** End of the test cases **************/
		
		/************** Test cases semi-automation report footer **************/
		System.out.println("____________________________________________________________________________");
		System.out.println();
		System.out.println("Number of tests passed: "+ numPassed);
		System.out.println("Number of tests failed: "+ numFailed);
		System.out.println("Percentage of passed cases: " + numPassed / (numPassed + numFailed) * 100 + "%");
	}
	
	/**
	 * This method sets up the input value for the test from the input parameters,
	 * displays test execution information, invokes precisely the same recognizer
	 * that the interactive JavaFX mainline uses, interprets the returned value,
	 * and displays the interpreted result.
	 *
	 * @param testCase The test case's number on the list.
	 * @param content The input content to be checked.
	 * @param expectedPass The expected outcome (true if valid, false if invalid).
	 */
	
	public static void performTestCase (int testCase, String content, boolean expectedPass) {
		System.out.println("____________________________________________________________________________\n\nTest case: " + testCase);
		System.out.println("Contents input: \"" + content + "\"");
		
		/************** Call the recognizer to process the input **************/
		String resultText = EmailRecognizer.checkForValidEmail(content);
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
