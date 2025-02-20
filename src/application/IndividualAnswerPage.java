package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
public class IndividualAnswerPage {
	
    private final DatabaseHelper databaseHelper;

    public IndividualAnswerPage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void show(Stage primaryStage, User user, Question question, Answer answer) throws SQLException {
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
        System.out.println("Content: " + answer.getContent());
        System.out.println("Author: " + answer.getAuthor());
        
        Label header = new Label(answer.getAuthor() + "'s Post");
        Button updateButton = new Button("Update answer");
        Button deleteButton = new Button("Delete answer");
		Label contentText = new Label(answer.getContent());

		
		updateButton.setOnAction(a -> {
			new UpdateAnswerPage(databaseHelper).show(primaryStage, user, question, answer);
		});
		
		deleteButton.setOnAction(a -> {
			databaseHelper.deleteAnswer(answer.getId());
			try {
				new IndividualQuestionPage(databaseHelper).show(primaryStage, user, question);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		// - - - - - - - - - - - - - - - CONTENT - - - - - - - - - - - - - - 
        
        // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		HBox buttonContainer = new HBox();
		buttonContainer.setAlignment(javafx.geometry.Pos.TOP_RIGHT);
        
        if (user.getUserName().equals(answer.getAuthor())) {
        	buttonContainer.getChildren().addAll(updateButton, deleteButton);
        }
        
        VBox centerContent = new VBox(10, header, buttonContainer, contentText);
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