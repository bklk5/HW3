package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import databasePart1.*;

/**
 * The SetupAdmin class handles the setup process for creating an administrator account.
 * This is intended to be used by the first user to initialize the system with admin credentials.
 */
public class IndividualQuestionPage {
	
    private final DatabaseHelper databaseHelper;

    public IndividualQuestionPage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void show(Stage primaryStage, User user, Question question) throws SQLException {
    	try {
            databaseHelper.connectToDatabase(); // Connect to the database
            if (databaseHelper.isDatabaseEmpty()) {
            	new FirstPage(databaseHelper).show(primaryStage);
            } else {
            	databaseHelper.printQuestions();
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
    	
    	System.out.println(question.getId());
    	
    	// - - - - - - - - - - - - - - - NAV BAR - - - - - - - - - - - - - - 
    	// Set up buttons for top nav bar 
    	Button homeButton = new Button("Home");
    	Button forumsButton = new Button("Forums");
    	
    	homeButton.setOnAction(a -> new HomePage(databaseHelper).show(primaryStage, user));
    	forumsButton.setOnAction(a -> new Forums(databaseHelper).show(primaryStage, user));
    	
    	// Create the Top Navigation Bar
        ToolBar toolbar = new ToolBar(homeButton, forumsButton);
        // - - - - - - - - - - - - - - - NAV BAR - - - - - - - - - - - - - - 
        
        // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        // - - - - - - - - - - - - - - - CONTENT - - - - - - - - - - - - - - 
        Button updateButton = new Button("Update Question");
        Button deleteButton = new Button("Delete Question");
        Label questionText = new Label(question.getTitle());
		Label authorText = new Label(question.getAuthor());
		Label contentText = new Label(question.getContent());
		Button answerButton = new Button("Answer Question");
		
		updateButton.setOnAction(a -> {
			new UpdateQuestionPage(databaseHelper).show(primaryStage, user, question);
		});
		
		deleteButton.setOnAction(a -> {
			databaseHelper.deleteQuestion(question.getId());
			new Forums(databaseHelper).show(primaryStage, user);
		});
		
		answerButton.setOnAction(a -> {
			new CreateAnswer(databaseHelper).show(primaryStage, user, question);
		});
		
		ObservableList<String> items = FXCollections.observableArrayList();
    	ListView<String> listView = new ListView<>(items);
    	
    	AnswersList aList = new AnswersList();
    	aList.setAnswers(databaseHelper.readAnswersByQuestionId(question.getId()));
    	
    	for (Answer a : aList.getAnswers()) {
    		items.add("ID: " + a.getId() + "    |    " + a.getAuthor() + " said : " + a.getContent());
    	}
    	
    	// Handle button for listview upon clicking on individual list element 
        listView.setOnMouseClicked(a -> {
        	if (a.getClickCount() >= 2) {
                String selectedItem = listView.getSelectionModel().getSelectedItem();
                int id = Integer.parseInt(selectedItem.split(" ")[1]);
                System.out.println("ANSWER ID IS " + id);
                
                if(selectedItem != null) {
                	try {
                		// take person to page of question
						Answer answer = databaseHelper.readAnswerById(id);
						new IndividualAnswerPage(databaseHelper).show(primaryStage, user, question, answer);
					} catch (SQLException e) {
						e.printStackTrace();
					}
                }
        	}
        });
		// - - - - - - - - - - - - - - - CONTENT - - - - - - - - - - - - - - 
        
        // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        
        VBox centerContent = new VBox(10, updateButton, deleteButton, authorText, questionText, contentText, answerButton, listView);
        centerContent.setStyle("-fx-padding: 20px;");

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(toolbar);      // Add navigation bar
        borderPane.setCenter(centerContent); // Main content area

        // Set the Scene and Show
        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setTitle("Forums");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}