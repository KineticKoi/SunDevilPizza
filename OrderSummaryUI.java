
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
    private TextField orderSummaryTextField;
    private TextField asuriteIDField;
    private PasswordField passwordField;
    private TextField emailField;

    OrderSummaryUI(int width, int height) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF");
        
        orderSummaryTextField = new TextField();
        orderSummaryTextField.setPrefWidth(600);
        orderSummaryTextField.setPrefHeight(200);
        orderSummaryTextField.layoutXProperty().bind(this.widthProperty().subtract(orderSummaryTextField.getPrefWidth()).divide(2));
        orderSummaryTextField.layoutYProperty().set(220);
        orderSummaryTextField.setEditable(false);
        orderSummaryTextField.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey;");
        
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
        
        verifyButton = new Button("Verify");
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
        
        getChildren().addAll(orderSummaryLabel, orderSummaryTextField, completePurchaseLabel, asuriteIDField, verifyButton, passwordField, emailField, purchaseButton, backButton);  
    }
    
    //Handler for all UI controls...
    private class OrderSummaryControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == backButton) {
                SunDevilPizza.previousRoot();
            }
            else if(event.getSource() == purchaseButton) {
                SunDevilPizza.newRoot(new OrderConfirmationUI(SunDevilPizza.width, SunDevilPizza.height));
            }
        }
    }
}
