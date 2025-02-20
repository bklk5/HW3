package application;

import databasePart1.DatabaseHelper;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * AdminPage class represents the user interface for the admin user.
 * This page displays a simple welcome message for the admin.
 */

public class HomePage {
	
	private final DatabaseHelper databaseHelper;

    public HomePage(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
	/**
     * Displays the admin page in the provided primary stage.
     * @param primaryStage The primary stage where the scene will be displayed.
     */
    public void show(Stage primaryStage, User user) {
    	// - - - - - - - - - - - - - - - NAV BAR - - - - - - - - - - - - - - 
    	// Set up buttons for top nav bar 
    	Button homeButton = new Button("Home");
    	Button forumsButton = new Button("Forums");
    	
    	homeButton.setOnAction(a -> new HomePage(databaseHelper).show(primaryStage, user));
    	forumsButton.setOnAction(a -> new Forums(databaseHelper).show(primaryStage, user));
    	
    	// Create the Top Navigation Bar
        ToolBar toolbar = new ToolBar(homeButton, forumsButton);
        // - - - - - - - - - - - - - - - NAV BAR - - - - - - - - - - - - - - 
        
        
        // - - - - - - - - - - - - - - - CONTENT - - - - - - - - - - - - - - 
        Label welcomeText = new Label("Welcome " + user.getUserName() + "!");
    	Button questionButton = new Button("questions page");
	    Button inviteButton = new Button("Invite");
	    Button oneTimePasswordButton = new Button("One Time Password");
	    Button listUsersButton = new Button("List Users");
	    Button removeUsersButton = new Button("Remove Users");
	    Button updateRoleButton = new Button("Update Role");
	    Button logoutButton = new Button("Logout");
	    
	    // styling 
	    welcomeText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
	   
	    // Go to the invite page
        inviteButton.setOnAction(a -> {
            new InvitationPage().show(databaseHelper, primaryStage);
        });
	    // Go to the one time password page
        oneTimePasswordButton.setOnAction(a -> {
            //page and feature need implementation
        	new AdminOnetimePassword().show(databaseHelper, primaryStage);
        	
        });
        // Go to the list of user page
        listUsersButton.setOnAction(a -> {
        	new AdminUserListPage(databaseHelper).show(primaryStage);
        	//page and feature need implementation
        });
        // Go to remove users page
        removeUsersButton.setOnAction(a -> {
        	//page and feature need implementation
        	new DeleteUserPage().show(databaseHelper, primaryStage);
        });
        // Go to update role page
        updateRoleButton.setOnAction(a -> {
        	//page and feature need implementation
        	new UpdateRolesPage().show(databaseHelper, primaryStage);
        });
        // Logout user
        logoutButton.setOnAction(a -> {
        	System.out.println("logging out...");
        	new SetupLoginSelectionPage(databaseHelper).show(primaryStage);
        });
    	
    	questionButton.setOnAction(a -> {
            new Forums(databaseHelper).show(primaryStage, user);
    	});
    	VBox layout = new VBox();
    	
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    // label to display the welcome message for the admin
	    Label adminLabel = new Label("Hello, Admin!");
	    adminLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    // - - - - - - - - - - - - - - - CONTENT END  - - - - - - - - - - - - - - 
	    
	    
	    
        // - - - - - - - - - - - - - - - GENERAL LAYOUT FOR PAGES - - - - - - - - - - - - - - 
        VBox centerContent = new VBox(10, welcomeText, questionButton,inviteButton,oneTimePasswordButton,listUsersButton,removeUsersButton,updateRoleButton, logoutButton);
        centerContent.setStyle("-fx-padding: 20px;");

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(toolbar);      // Add navigation bar
        borderPane.setCenter(centerContent); // Main content area

        // Set the Scene and Show
        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setTitle("Forums");
        primaryStage.setScene(scene);
        primaryStage.show();
        // - - - - - - - - - - - - - - - GENERAL LAYOUT FOR PAGES END - - - - - - - - - - - - - - 
    }
}