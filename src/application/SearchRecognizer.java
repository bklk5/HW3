package application;

public class SearchRecognizer {
	
	public static String checkSearch(String content) {
		
		// check if length of email valid 
		if (content.length() == 0 || content.isEmpty()) {
			return "content field cannot be empty";
		}
		
		if (content.length() < 5 || content.length() > 100) {
			return "content must be between 5 and 100 characters";
		}
        
        return "";
	}
}
