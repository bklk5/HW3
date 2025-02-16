package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import databasePart1.DatabaseHelper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.Node;


public class Forums {
	private final DatabaseHelper databaseHelper;

    public Forums(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void show(Stage primaryStage, User user) {  
    	// Set up buttons for top nav bar 
    	Button homeButton = new Button("Home");
    	Button forumsButton = new Button("Forums");
    	
    	homeButton.setOnAction(a -> new HomePage(databaseHelper).show(primaryStage, user));
    	forumsButton.setOnAction(a -> new Forums(databaseHelper).show(primaryStage, user));
    	
    	// Create the Top Navigation Bar
        ToolBar toolbar = new ToolBar(homeButton, forumsButton);
    	
    	// Set up listview to show list of question titles
    	ObservableList<String> items = FXCollections.observableArrayList();
    	ListView<String> listView = new ListView<>(items);
    	
    	QuestionsList questionList = new QuestionsList();

        
        try {
            databaseHelper.connectToDatabase(); // Connect to the database

            if (databaseHelper.isDatabaseEmpty()) {
                new FirstPage(databaseHelper).show(primaryStage);
                return; // Exit early if database is empty
            } else {
            	
            	// Add question titles to listview 
            	questionList.setQuestions(databaseHelper.getQuestionTitles());
            	
            	for (Question q : questionList.getQuestions()) {
            		items.add("ID: " + q.getId() + "      |      Title: " + q.getTitle());
            	}
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Create Button to Navigate to Questions & Answers Page
        Button questionButton = new Button("Ask a question");
        questionButton.setOnAction(a -> {
        	new CreateQuestion(databaseHelper).show(primaryStage, user);
        });
        
        // Handle button for listview upon clicking
        listView.setOnMouseClicked(a -> {
        	if (a.getClickCount() >= 2) {
                String selectedItem = listView.getSelectionModel().getSelectedItem();
                int id = Integer.parseInt(selectedItem.split(" ")[1]);
                
                if(selectedItem != null) {
                	try {
                		// take person to page of question
						Question q = databaseHelper.readQuestionById(id);
						new IndividualQuestionPage(databaseHelper).show(primaryStage, user, q);
					} catch (SQLException e) {
						e.printStackTrace();
					}
                }
        	}
        });
        

        
        VBox centerContent = new VBox(10, new Label("Questions"), questionButton, listView);
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
