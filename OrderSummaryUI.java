
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class OrderSummaryUI extends Pane {
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

    OrderSummaryUI(int width, int height, String orderSummary) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF");
        
        orderSummaryTextField = new TextArea();
        orderSummaryTextField.setPrefWidth(600);
        orderSummaryTextField.setPrefHeight(200);
        orderSummaryTextField.layoutXProperty().bind(this.widthProperty().subtract(orderSummaryTextField.getPrefWidth()).divide(2));
        orderSummaryTextField.layoutYProperty().set(220);
        orderSummaryTextField.setEditable(false);
        orderSummaryTextField.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey;");
        orderSummaryTextField.setText(orderSummary);
        
        orderSummaryLabel = new Label("Order Summary:");
        orderSummaryLabel.layoutXProperty().bind(this.widthProperty().subtract(orderSummaryTextField.getPrefWidth()).divide(2));
        orderSummaryLabel.layoutYProperty().set(140);
        orderSummaryLabel.setFont(new Font("Arial", 40));
        
        completePurchaseLabel = new Label("Complete Purchase:");
        completePurchaseLabel.layoutXProperty().bind(this.widthProperty().subtract(orderSummaryTextField.getPrefWidth()).divide(2));
        completePurchaseLabel.layoutYProperty().set(520);
        completePurchaseLabel.setFont(new Font("Arial", 40));
        
        asuriteIDField = new TextField();
        asuriteIDField.setPrefSize(600, 40);
        asuriteIDField.layoutXProperty().bind(this.widthProperty().subtract(asuriteIDField.getPrefWidth()).divide(2));
        asuriteIDField.layoutYProperty().set(600);
        asuriteIDField.setPromptText("ASURITE ID");
        asuriteIDField.setStyle("-fx-background-color: lightgrey;");
        
        passwordField = new PasswordField();
        passwordField.setPrefSize(600, 40);
        passwordField.layoutXProperty().bind(this.widthProperty().subtract(asuriteIDField.getPrefWidth()).divide(2));
        passwordField.layoutYProperty().set(660);
        passwordField.setStyle("-fx-background-color: lightgrey;");
        passwordField.setVisible(false);
        
        signedInAsLabel = new Label("");
        signedInAsLabel.layoutXProperty().bind(this.widthProperty().subtract(orderSummaryTextField.getPrefWidth()).divide(2));
        signedInAsLabel.layoutYProperty().set(600);
        signedInAsLabel.setFont(new Font("Arial", 40));
        signedInAsLabel.setVisible(false);
        
        verifyButton = new Button("Verify ID");
        verifyButton.setPrefSize(160, 40);
        verifyButton.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey;");
        verifyButton.layoutXProperty().bind(this.widthProperty().subtract(verifyButton.widthProperty()).divide(2));
        verifyButton.setLayoutY(660);
        verifyButton.setOnAction(new OrderSummaryControlsHandler());
        
        emailField = new TextField();
        emailField.setPrefSize(600, 40);
        emailField.layoutXProperty().bind(this.widthProperty().subtract(asuriteIDField.getPrefWidth()).divide(2));
        emailField.layoutYProperty().set(720);
        emailField.setPromptText("Email Address");
        emailField.setStyle("-fx-background-color: lightgrey;");
        
        purchaseButton = new Button("Purchase");
        purchaseButton.setPrefSize(160, 40);
        purchaseButton.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey;");
        purchaseButton.layoutXProperty().bind(this.widthProperty().subtract(purchaseButton.widthProperty()).divide(2));
        purchaseButton.setLayoutY(800);
        purchaseButton.setOnAction(new OrderSummaryControlsHandler());
        
        backButton = new ButtonMaker("back");
        backButton.setOnAction(new OrderSummaryControlsHandler());
        
        if (((Customer)SunDevilPizza.session.getUser()).getIDNum() != 0) {
            if (SunDevilPizza.session.getUser().getType().equalsIgnoreCase("CUSTOMER")) {
                asuriteIDField.setVisible(false);
                verifyButton.setVisible(false);
                Customer customer = (Customer)SunDevilPizza.session.getUser();
                signedInAsLabel.setText("Signed in as ASURITE ID: " + customer.getIDNum());
                signedInAsLabel.setVisible(true);
            }
        }
        
        getChildren().addAll(orderSummaryLabel, orderSummaryTextField, completePurchaseLabel, asuriteIDField, signedInAsLabel, verifyButton, passwordField, emailField, purchaseButton, backButton);  
    }
    
    //Handler for all UI controls...
    private class OrderSummaryControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == backButton) {
                SunDevilPizza.previousRoot();
            }
            if (event.getSource() == verifyButton) {
                boolean exists = FileManager.existingCustomer(asuriteIDField.getText() + ".dat");
                if (exists == true) {
                    verifyButton.setVisible(false);
                    passwordField.setPromptText("Enter your password");
                    passwordField.setVisible(true);
                    completePurchaseLabel.setStyle("-fx-text-fill: black;");
                }
                else if (CredentialVerification.isAnAsuriteID(asuriteIDField.getText())){
                    ((Customer)SunDevilPizza.session.getUser()).setIDNum(Integer.parseInt(asuriteIDField.getText()));
                    verifyButton.setVisible(false);
                    passwordField.setPromptText("Create a new password");
                    passwordField.setVisible(true);
                    completePurchaseLabel.setStyle("-fx-text-fill: black;");
                }
                else {
                    completePurchaseLabel.setStyle("-fx-text-fill: red;");
                }
            }
            if(event.getSource() == purchaseButton) {
                boolean exists = FileManager.existingCustomer(asuriteIDField.getText() + ".dat");
                if (!asuriteIDField.getText().equals("") && !passwordField.getText().equals("") && emailField.getText().contains("@") && emailField.getText().contains(".")) {
                    if (exists) {
                        SunDevilPizza.session.setUser(CredentialVerification.loginCheck("asurite", asuriteIDField.getText(), passwordField.getText()));
                    }
                    else {
                        ((Customer)SunDevilPizza.session.getUser()).setPassword(passwordField.getText());
                    }
                    Customer customer = ((Customer)SunDevilPizza.session.getUser());
                    customer.getCurrentOrder().setEmail(emailField.getText());
                    String orderNumber = SunDevilPizza.session.generateOrderNumber();
                    customer.getCurrentOrder().setOrderNumber(orderNumber);
                    customer.addOrder(customer.getCurrentOrder());
                    customer.resetCurrentOrder();
                    FileManager.saveCurrentCustomer();
                    SunDevilPizza.newRoot(new OrderConfirmationUI(SunDevilPizza.width, SunDevilPizza.height, orderNumber));
                }
                else {
                    completePurchaseLabel.setStyle("-fx-text-fill: red;");
                }
            }
        }
    }
}
