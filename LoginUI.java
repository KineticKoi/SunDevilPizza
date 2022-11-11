import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class LoginUI extends Pane
{
    // Declaring Variables
    private String type;
    private Label headerLabel;
    private TextField userNameField;
    private PasswordField passwordField;
    private Button signInButton;
    private Button customerHubButton;    
    private Button signOutButton;
    private Label loginFailedLabel;
    private Button backButton;
    private Hyperlink employeeSignInLink;
    private int posy = 320;
    
    // Constructor
    LoginUI(String type, int width, int height)
    {
        this.type = type;
        this.setWidth(width);
        this.setHeight(height);

        // Username textfield attributes
        // Creates new textfield for username
        userNameField = new TextField();
        // Creates new textfield for username
        userNameField.setPrefSize(320, 40);
        // Sets textfield's X centering value
        userNameField.layoutXProperty().bind(this.widthProperty().subtract(userNameField.getPrefWidth()).divide(2));
        // Slightely lowers textfield
        userNameField.layoutYProperty().set(posy + 60);
        // Sets prompt text for textfield to show type string from method paramters and "ID" before user types in it
        userNameField.setPromptText(type.toUpperCase() + " ID");
        // Sets textfield background to be light grey
        userNameField.setStyle("-fx-background-color: lightgrey;");
        
        
        // Password field attributes
        // Creates new textfield for password
        passwordField = new PasswordField();
        // Sets textfield preferred size to (320, 40)
        passwordField.setPrefSize(320, 40);
        // Sets textfield's X centering value
        passwordField.layoutXProperty().bind(this.widthProperty().subtract(userNameField.getPrefWidth()).divide(2));
        // Slightely lowers textfield
        passwordField.layoutYProperty().set(posy + 120);
        // Sets prompt text for textfield to show "Password" before user types in it
        passwordField.setPromptText("Password");
        // Sets textfield background to be light grey
        passwordField.setStyle("-fx-background-color: lightgrey;");
        
        // Header label attributes
        // Creates new label with type string from mconstructor parameters and " Login" text
        headerLabel = new Label(type.toUpperCase() + " Login:");
        // Sets label's centering X value
        headerLabel.layoutXProperty().bind(this.widthProperty().subtract(userNameField.getPrefWidth()).divide(2));
        // Slightely lowers label
        headerLabel.layoutYProperty().set(posy);
        // Sets label's text font and size
        headerLabel.setFont(new Font("Arial", 24));
        
        
        // Sign-in button attributes
        // Creates new button with text "Sign In"
        signInButton = new Button("Sign In");
        // Sets button's text color to black and background to light grey
        signInButton.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey;");
        // Sets button's preffered size to (120, 40)
        signInButton.setPrefSize(120, 40);
        // Sets button's X centering value
        signInButton.layoutXProperty().bind(this.widthProperty().subtract(signInButton.getPrefWidth()).divide(2));
        // Slightely lowers button
        signInButton.layoutYProperty().set(posy + 180);
        // Sets up sign in button's control handler for when sign in button is clicked
        signInButton.setOnAction(new LoginControlsHandler());
        
        
        // Customer hub button attributes
        // Creates new button with text "Profile"
        customerHubButton = new Button("Profile");
        // Sets button's text color to black and background to light grey
        customerHubButton.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey;");
        // Sets button's preffered size to (120, 40)
        customerHubButton.setPrefSize(120, 40);
        // Sets buton's X centering value
        customerHubButton.layoutXProperty().bind(this.widthProperty().subtract(signInButton.getPrefWidth()).divide(2));
        // Slightely lowers button
        customerHubButton.layoutYProperty().set(posy + 140);
        // Sets button to be invisible initially
        customerHubButton.setVisible(false);
        // Sets up sign in button's control handler for when sign in button is clicked
        customerHubButton.setOnAction(new LoginControlsHandler());
        
        
        // Sign-out button attributes
        // Creates new button with text "Sign Out"
        signOutButton = new Button("Sign Out");
        // Sets button's text color to black and background to light grey
        signOutButton.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey;");
        // Sets button's preffered size to (120, 40)
        signOutButton.setPrefSize(120, 40);
        // Sets buton's X centering value
        signOutButton.layoutXProperty().bind(this.widthProperty().subtract(signInButton.getPrefWidth()).divide(2));
        // Slightely lowers button
        signOutButton.layoutYProperty().set(posy + 200);
        // Sets button to be invisible initially
        signOutButton.setVisible(false);
        // Sets up sign in button's control handler for when sign in button is clicked
        signOutButton.setOnAction(new LoginControlsHandler());
        
        
        // Employee sign-in hyperlink attributes
        // Sets up new hyperlink for employee users to navigate to their login window
        employeeSignInLink = new Hyperlink("Employee Login →");
        // Sets hyperlink's preffered width to 120
        employeeSignInLink.setPrefWidth(120);
        // Sets hyperlinks X centering value
        employeeSignInLink.layoutXProperty().bind(this.widthProperty().subtract(employeeSignInLink.getPrefWidth()).divide(2));
        // Slightely lowers hyperlink
        employeeSignInLink.layoutYProperty().set(posy + 240);
        // Sets up employee login hyperlink's control handler for when it is clicked
        employeeSignInLink.setOnAction(new LoginControlsHandler());
        // Checks if the user's session type is an employee
        if (type.equalsIgnoreCase("employee"))
        {
            // If user type is not an employee then employee sign in hyperlink is made invisible
            employeeSignInLink.setVisible(false);
        }
        
        
        // Error label attributes
        // Creates label with text "Login Denied" for when user encounters an error when logging in
        loginFailedLabel = new Label("Login Denied");
        // Set's new label text to be red
        loginFailedLabel.setStyle("-fx-text-fill: red;");
        // Sets label's X centering value
        loginFailedLabel.layoutXProperty().bind(this.widthProperty().subtract(loginFailedLabel.getPrefWidth()).divide(2));
        // Slighely lowers label
        loginFailedLabel.layoutYProperty().set(posy + 300);
        // Sets label to be invisible initially
        loginFailedLabel.setVisible(false);
        
        
        // Back button attributes
        // Creates new button with text "back"
        backButton = new ButtonMaker("back");
        // Sets up back button's control handler for when it is clicked
        backButton.setOnAction(new LoginControlsHandler());
        
        // USER ALREADY SIGNED IN
        if (SunDevilPizza.session.getUser() != null && !((Customer)SunDevilPizza.session.getUser()).getIDNum().equals("-1"))
        {
            // "Login" Header label is made invisible
            headerLabel.setVisible(false);
            // Username textfield is made invisible
            userNameField.setVisible(false);
            // Password textfield is made invisible
            passwordField.setVisible(false);
            // "Sign In" button is made invisible
            signInButton.setVisible(false);
            // Employee login hyperlink is made invisible
            employeeSignInLink.setVisible(false);
            // "Sign Out" button is made visible
            signOutButton.setVisible(true);
            // Customer hub button is made visible
            customerHubButton.setVisible(true);
        }
        
        // Add everything to pane.
        this.getChildren().addAll(headerLabel, userNameField, passwordField, signInButton, customerHubButton,
            signOutButton, employeeSignInLink, loginFailedLabel, backButton);
    }
    
    // Handler for all UI controls
    private class LoginControlsHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            // Calls Sounds class to play audio when pane is clicked
            Sounds.playButtonClick();
            // Checks if "Sign In" button is clicked
            if (event.getSource() == signInButton)
            {
                // Checks if type from constructor parameter list is a customer's ASURITEID
                if (type.equalsIgnoreCase("asurite"))
                {
                    // Calls CredentialVerification class to verify customer credentials
                    Customer customer = CredentialVerification.customerLoginCheck(type, userNameField.getText(),
                        passwordField.getText());
                    // Checking if customer login credentials were verified
                    if (customer != null)
                    {
                        // Setting current session user to be a customer
                        SunDevilPizza.session.setUser(customer);
                        // Sending customer user to CustomerPortalUI pane
                        SunDevilPizza.newRoot(new CustomerPortalUI(SunDevilPizza.width, SunDevilPizza.height));
                    }
                    // "Login Denied" red label becomes visible so user knows there was an error while trying to login
                    else
                    {
                        loginFailedLabel.setVisible(true);
                    }
                }
                else
                {
                    // Calls CredentialVerification class to verify employee credentials
                    Employee employee = CredentialVerification.employeeLoginCheck(type, userNameField.getText(),
                        passwordField.getText());
                    // Checking if employee login credentials were verified
                    if (employee != null)
                    {
                        // Setting current session user to be an employee
                        SunDevilPizza.session.setUser(employee);
                        // Checking if current employee type is an order processing agent
                        if (((Employee)SunDevilPizza.session.getUser()).getRole().equalsIgnoreCase("OPA"))
                        {
                            // Sending employee user to EmployeePortalUI pane and setting the employee's role type as
                            // Order Processing Agent for the class
                            SunDevilPizza.newRoot(new EmployeePortalUI(SunDevilPizza.width,
                                SunDevilPizza.height, "Order Processing Agent"));
                        }
                        // Checking if current employee type is a chef
                        if (((Employee)SunDevilPizza.session.getUser()).getRole().equalsIgnoreCase("CHEF"))
                        {
                            // Sending employee user to EmployeePortalUI pane and setting the employee's role type as
                            // Chef for the class
                            SunDevilPizza.newRoot(new EmployeePortalUI(SunDevilPizza.width,
                                SunDevilPizza.height, "Chef"));
                        }
                    }
                    else
                    {
                        //"Login Denied" red label becomes visible so user knows there was an error while trying to login
                        loginFailedLabel.setVisible(true);
                    }
                }
            }
            
            // Checking if "Sign Out" button was clicked
            if (event.getSource() == signOutButton)
            {
                // Setting current user's session to null
                SunDevilPizza.session.setUser(null);
                // Sending user back to the program's main landing page
                SunDevilPizza.home();
            }
            
            // Checking if customer hub button is clicked
            if (event.getSource() == customerHubButton)
            {
                // Sends user to customer hub pane
                SunDevilPizza.newRoot(new CustomerPortalUI(SunDevilPizza.width, SunDevilPizza.height));
            }
            
            // Checking if employee login hyperlink is clicked
            // Employee sign-in hyperlink actions
            if (event.getSource() == employeeSignInLink)
            {
                // Sends user to employee login pane
                SunDevilPizza.newRoot(new LoginUI("EMPLOYEE", SunDevilPizza.width, SunDevilPizza.height));
            }
            
            // Checking if back button is clicked
            // Back button actions (ASURITE )
            if (event.getSource() == backButton)
            {
                // Sends user back to previous pane
                SunDevilPizza.previousRoot();
            }
        }
    }   
}