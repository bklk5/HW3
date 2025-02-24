package application;

public class QuestionAutomatedTest {
	static int numPassed = 0;	// Counter of the number of passed tests
	static int numFailed = 0;	// Counter of the number of failed tests
	
	public static void main (String[] args) {
		/************** Test cases semi-automation report header **************/
		System.out.println("\nQuestion Testing Automation");
		
		/************** Start of the test cases **************/
		performTestCase(1, "Homework", "hhhhhhhhhhhhhh", "home", true);
		performTestCase(2, "Phase 1", "What is the requirement", "teamphase", true);
		performTestCase(3, "123", "what to do", "none", false);
		performTestCase(4, "teamphase", "111", "team", false);
		performTestCase(5, "", "111", "no", false);
		/************** End of the test cases **************/
		
		/************** Test cases semi-automation report footer **************/
		System.out.println("____________________________________________________________________________");
		System.out.println();
		System.out.println("Number of tests passed: "+ numPassed);
		System.out.println("Number of tests failed: "+ numFailed);
	}
	
	public static void performTestCase(int testCase, String title, String content, String category, boolean expectedPass) {
		System.out.println("____________________________________________________________________________\n\nTest case: " + testCase);
		System.out.println("Title input: \"" + title + "\"");
		System.out.println("Content input: \"" + content + "\"");
		System.out.println("Category input: \"" + category + "\"");
		
		/************** Call the recognizer to process the input **************/
		String resultText = QuestionRecognizer.checkQuestion(title, content, category);
		/************** Interpret the result and display that interpreted information **************/
		System.out.println(resultText);
		if (resultText != "") {
			numPassed++;
		} else { 
			numFailed++;
		}
		
	}
	
}