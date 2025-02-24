package application;

public class AnswerAutomatedTest {
	static int numPassed = 0;	// Counter of the number of passed tests
	static int numFailed = 0;	// Counter of the number of failed tests
	
	public static void main(String[] args) {
		/************** Test cases semi-automation report header **************/
		System.out.println("\nAnswer Testing Automation");
		
		/************** Start of the test cases **************/
		performTestCase(1, "hello everyone", true);
		performTestCase(2, "111", false);
		performTestCase(3, "work", false);
		performTestCase(4, "     ", false);
		performTestCase(5, "nothing", true);
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
		String resultText = AnswerRecognizer.checkAnswer(content);
		/************** Interpret the result and display that interpreted information **************/
		System.out.println(resultText);
		if (resultText != "") {
			numPassed++;
		} else { 
			numFailed++;
		}
		
	}
}