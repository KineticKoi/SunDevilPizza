
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import static jdk.internal.org.jline.terminal.Terminal.MouseTracking.Button;

// class for displaying the order summary
public class OrderSummaryUI extends Pane
{    
    // Defining variables
    private Button backButton;
    private Button forwardButton;
    private Button purchaseButton;
    private Button verifyButton;
    private Label orderSummaryLabel;
    private Label completePurchaseLabel;
    private Label signedInAsLabel;
    private TextArea orderSummaryTextField;
    private TextField asuriteIDField;
    private PasswordField passwordField;
    private TextField emailField;
    private boolean alreadySignedIn = false;

    OrderSummaryUI(int width, int height, String orderSummary)
    {
        // Sets this pane width
        setWidth(width);
        // Sets this pane height
        setHeight(height);
        setStyle("-fx-background-color: #FFFFFF");
        
        // creating a new non editable text area that will display the order summary
        orderSummaryTextField = new TextArea();
        // 600 x 200 px size
        orderSummaryTextField.setPrefWidth(600);
        orderSummaryTextField.setPrefHeight(200);
        // setting the size of the text feild in relation to the overall layout
        orderSummaryTextField.layoutXProperty().bind(this.widthProperty().
            subtract(orderSummaryTextField.getPrefWidth()).divide(2));
        orderSummaryTextField.layoutYProperty().set(220);
        orderSummaryTextField.setEditable(false);
        orderSummaryTextField.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey;");
        orderSummaryTextField.setText(orderSummary);
        
        // Order summary label to denote which page the user is on
        orderSummaryLabel = new Label("Order Summary:");
        // setting horizontal label in relation to everything else
        orderSummaryLabel.layoutXProperty().bind(this.widthProperty().
            subtract(orderSummaryTextField.getPrefWidth()).divide(2));
        // setting static width
        orderSummaryLabel.layoutYProperty().set(140);
        orderSummaryLabel.setFont(new Font("Arial", 40));
        
        // complete purchase label
        completePurchaseLabel = new Label("Complete Purchase:");
        completePurchaseLabel.layoutXProperty().bind(this.widthProperty().subtract(orderSummaryTextField.getPrefWidth()).divide(2));
        completePurchaseLabel.layoutYProperty().set(520);
        completePurchaseLabel.setFont(new Font("Arial", 40));
        
        // initiating a user editable text field which will take a string input from the user to save as an ASURITE ID
        asuriteIDField = new TextField();
        asuriteIDField.setPrefSize(600, 40);
        asuriteIDField.layoutXProperty().bind(this.widthProperty().subtract(asuriteIDField.getPrefWidth()).divide(2));
        asuriteIDField.layoutYProperty().set(600);
        asuriteIDField.setPromptText("ASURITE ID");
        asuriteIDField.setStyle("-fx-background-color: lightgrey;");
        
        // initiating a user editable text field which will take a string input from the user to save as an
        // unique PASSWORD
        passwordField = new PasswordField();
        passwordField.setPrefSize(600, 40);
        passwordField.layoutXProperty().bind(this.widthProperty().subtract(asuriteIDField.getPrefWidth()).divide(2));
        passwordField.layoutYProperty().set(660);
        passwordField.setStyle("-fx-background-color: lightgrey;");
        passwordField.setVisible(false);
        
        // Invisible label to be hidden from the user
        signedInAsLabel = new Label("");
        signedInAsLabel.layoutXProperty().bind(this.widthProperty().subtract(orderSummaryTextField.getPrefWidth()).divide(2));
        signedInAsLabel.layoutYProperty().set(600);
        signedInAsLabel.setFont(new Font("Arial", 40));
        signedInAsLabel.setVisible(false);
        
        // Button to verify that the ASURITE ID is valid
        verifyButton = new Button("Verify ID");
        verifyButton.setPrefSize(160, 40);
        verifyButton.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey;");
        verifyButton.layoutXProperty().bind(this.widthProperty().subtract(verifyButton.widthProperty()).divide(2));
        verifyButton.setLayoutY(660);
        verifyButton.setOnAction(new OrderSummaryControlsHandler());
        
        // initiating a user editable text field which will take a string input from the
        // user to save as an unique EMAIL to send updates to the user
        emailField = new TextField();
        emailField.setPrefSize(600, 40);
        emailField.layoutXProperty().bind(this.widthProperty().subtract(asuriteIDField.getPrefWidth()).divide(2));
        emailField.layoutYProperty().set(720);
        emailField.setPromptText("Email Address");
        emailField.setStyle("-fx-background-color: lightgrey;");
        
        // A PURCHASE button to complete the transaction
        purchaseButton = new Button("Purchase");
        purchaseButton.setPrefSize(160, 40);
        purchaseButton.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey;");
        purchaseButton.layoutXProperty().bind(this.widthProperty().subtract(purchaseButton.widthProperty()).divide(2));
        purchaseButton.setLayoutY(800);
        purchaseButton.setOnAction(new OrderSummaryControlsHandler());
        
        // A BACK button to navigate the user across roots
        backButton = new ButtonMaker("back");
        backButton.setOnAction(new OrderSummaryControlsHandler());
        
        // If the user is logged in already
        if (!((Customer)SunDevilPizza.session.getUser()).getIDNum().equals("-1"))
        {
            // hide the field where a ASURITE id is required
            asuriteIDField.setVisible(false);
            verifyButton.setVisible(false);
            // getting the user ID
            Customer customer = (Customer)SunDevilPizza.session.getUser();
            // displaying the USER's ASURITE ID
            signedInAsLabel.setText("Signed in as ASURITE ID: " + customer.getIDNum());
            // The user is signed in
            alreadySignedIn = true;
            // visual confirmation that user is signed in
            signedInAsLabel.setVisible(true);
        }
        // adding all
        getChildren().addAll(orderSummaryLabel, orderSummaryTextField, completePurchaseLabel, asuriteIDField, signedInAsLabel, verifyButton, passwordField, emailField, purchaseButton, backButton);  
    }
    
    // Handler for all UI controls
    private class OrderSummaryControlsHandler implements EventHandler<ActionEvent>
    {
        @Override
        // every time a button is clicked make a button click noise
        public void handle(ActionEvent event)
        {
            Sounds.playButtonClick();
            // go back to the previous root if BACK is clicked
            if (event.getSource() == backButton)
            {
                SunDevilPizza.previousRoot();
            }
            // if the VERIFY button is clicked
            if (event.getSource() == verifyButton)
            {
                // check if there is an existing customer that has the same ASURITE ID
                boolean exists = FileManager.existingCustomer(asuriteIDField.getText());
                // if there is an existing user
                if (exists == true)
                { 
                    // verify button is no longer visible as now we are requesting a password
                    verifyButton.setVisible(false);
                    passwordField.setPromptText("Enter your password");
                    // password field is visible and prompts user to enter password
                    passwordField.setVisible(true);
                    completePurchaseLabel.setStyle("-fx-text-fill: black;");
                }
                // if the user does not exist and the ASURITE ID is correctly formatted
                else if (CredentialVerification.isAnAsuriteID(asuriteIDField.getText()))
                {
                    SunDevilPizza.session.setUser(new Customer(asuriteIDField.getText()));
                    verifyButton.setVisible(false);
                    // user is prompted to to create a password
                    passwordField.setPromptText("Create a new password");
                    passwordField.setVisible(true);
                    completePurchaseLabel.setStyle("-fx-text-fill: black;");
                }
                else
                {
                    // if there is an error the button is red
                    completePurchaseLabel.setStyle("-fx-text-fill: red;");
                }
            }
            // if purcase button is clicked
            if(event.getSource() == purchaseButton)
            {
                // if the email field is populated and if the user is signed in or the feilds are populated
                if (emailField.getText().contains("@") && emailField.getText().contains(".") &&
                    (alreadySignedIn || !asuriteIDField.getText().equals("") && !passwordField.getText().equals("")))
                {
                    // create a customer
                    Customer customer;
                    // check if it is an existing customer
                    if (FileManager.existingCustomer(asuriteIDField.getText()))
                    {
                        customer = CredentialVerification.customerLoginCheck("asurite",
                            asuriteIDField.getText(), passwordField.getText());
                        // if it is, set the session to that customer
                        if (customer != null)
                        {
                            // set it to the already signed in customer
                            SunDevilPizza.session.setUser(customer);
                        }
                        else
                        {
                            completePurchaseLabel.setStyle("-fx-text-fill: red;");
                            return;
                        }
                    }
                    // get the user password to create a new customer
                    else if (!passwordField.getText().equals(""))
                    {
                        ((Customer)SunDevilPizza.session.getUser()).setPassword(passwordField.getText());
                    }
                    // set customer properties including current order, order number, and order status
                    customer = ((Customer)SunDevilPizza.session.getUser());
                    customer.getCurrentOrder().setEmail(emailField.getText());
                    String orderNumber = SunDevilPizza.session.generateOrderNumber();
                    customer.getCurrentOrder().setOrderNumber(orderNumber);
                    customer.getCurrentOrder().setStatus("ACCEPTED");
                    customer.addOrder(customer.getCurrentOrder());
                    customer.resetCurrentOrder();
                    // save the customer
                    FileManager.saveCustomer(customer);
                    // new customer confirmation login
                    SunDevilPizza.newRoot(new OrderConfirmationUI(SunDevilPizza.width,
                        SunDevilPizza.height, orderNumber));
                }
                else
                {
                    completePurchaseLabel.setStyle("-fx-text-fill: red;");
                }
            }
        }
    }
}