import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


public class OrderConfirmationUI extends Pane
{
    // Declaring Variables
    private Label headerLabel;
    private Label subLabel;
    private Button confirmationButton;
    
    // Constructor
    OrderConfirmationUI(int width, int height, String orderNumber)
    {
        // Sets this pane width
        setWidth(width);
        // Sets this pane height
        setHeight(height);
        // setting background
        setStyle("-fx-background-color: #FFFFFF");
        // creating label if the order is complete
        headerLabel = new Label("Your order is complete!");
        headerLabel.layoutXProperty().bind(this.widthProperty().subtract(headerLabel.widthProperty()).divide(2));
        headerLabel.setLayoutY(360);
        headerLabel.setFont(new Font("Arial", 40));
        // creating label to confirm it is the correct order number
        subLabel = new Label ("Order confirmation #" + orderNumber);
        subLabel.layoutXProperty().bind(this.widthProperty().subtract(subLabel.widthProperty()).divide(2));
        subLabel.setLayoutY(420);
        subLabel.setFont(new Font("Arial", 24));
        // creating a button that asks for order status
        confirmationButton = new Button("Order Status");
        confirmationButton.setPrefSize(160, 60);
        confirmationButton.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey;");
        confirmationButton.layoutXProperty().bind(this.widthProperty().subtract(confirmationButton.widthProperty()).divide(2));
        confirmationButton.setLayoutY(480);
        confirmationButton.setOnAction(new OrderConfirmationControlsHandler());
        // adding all
        getChildren().addAll(headerLabel, subLabel, confirmationButton);
    }
    
    private class OrderConfirmationControlsHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            // when a button is clicked make a button click sound
            Sounds.playButtonClick();
            // display order confirmation if the confirmation button is hit
            if (event.getSource() == confirmationButton)
            {
                SunDevilPizza.newRoot(new CustomerPortalUI(SunDevilPizza.width, SunDevilPizza.height));
            }
        }
    }
}