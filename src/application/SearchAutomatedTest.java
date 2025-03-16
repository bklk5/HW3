package application;

/**
 * Automated test class for validating answers.
 * It performs multiple test cases and reports the results.
 * 
 * @author Ngoc Minh Thu Le
 * @version 2.0
 */

public class SearchAutomatedTest {
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
		System.out.println("\nSearch Testing Automation");
		
		/************** Start of the test cases **************/
		performTestCase(1, "hello everyone", true);
		performTestCase(2, "111", false);
		performTestCase(3, "", false);
		performTestCase(4, "     ", false);
		performTestCase(5, "Computer science drives modern society, revolutionizing daily life across numerous fields. In healthcare, algorithms and AI analyze medical data, enabling early diagnoses and personalized treatments. Finance benefits from secure transactions and fraud detection through complex algorithms. Education transforms with digital platforms offering accessible, personalized learning and global collaboration. The entertainment industry leverages computer science for special effects and algorithm-driven recommendations. Social media, powered by computer science, fosters global communication and collaboration. Crucial in cybersecurity, computer science protects sensitive information and ensures data integrity. With evolving technology, the demand for skilled computer scientists grows, driving innovations like quantum computing and AI, addressing humanity's most pressing challenges. Thus, computer science is vital for progress and innovation.", false);
		/************** End of the test cases **************/
		
		/************** Test cases semi-automation report footer **************/
		System.out.println("____________________________________________________________________________");
		System.out.println();
		System.out.println("Number of tests passed: "+ numPassed);
		System.out.println("Number of tests failed: "+ numFailed);
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
		String resultText = SearchRecognizer.checkSearch(content);
		/************** Interpret the result and display that interpreted information **************/
		System.out.println(resultText);
		if (resultText != "") {
			
			if (expectedPass) {
				System.out.println("***Failure*** The search <" + content + "> is invalid." + 
						"\nBut it was supposed to be valid, so this is a failure!\n");
				System.out.println("Error message: " + resultText);
				numFailed++;
			}
			// If the test case expected the test to fail then this is a success
			else {			
			System.out.println("***Success*** The search <" + content  + "> is invalid." + 
									"\nBut it was supposed to be invalid, so this is a pass!\n");
			System.out.println("Error message: " + resultText);
			numPassed++;
						
			}
		} else { 
			// If the test case expected the test to pass then this is a success
		if (expectedPass) {	
			System.out.println("***Success*** The search<" + content + "> is valid, so this is a pass!");
					numPassed++;
				}
						// If the test case expected the test to fail then this is a failure
			else {
			System.out.println("***Failure*** The search <" + content + 
					"> was judged as valid" + 
				"\nBut it was supposed to be invalid, so this is a failure!");
							numFailed++;
			}
		
	
	}
		}
}