
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


public class OrderConfirmationUI extends Pane {
    //Declaring Variables...
    private Label headerLabel;
    private Label subLabel;
    private Button confirmationButton;
    
    //Constructor
    OrderConfirmationUI(int width, int height) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF");
        headerLabel = new Label("Your order is complete!");
        headerLabel.layoutXProperty().bind(this.widthProperty().subtract(headerLabel.widthProperty()).divide(2));
        headerLabel.setLayoutY(360);
        headerLabel.setFont(new Font("Arial", 40));
        subLabel = new Label ("Order confirmation #" + SunDevilPizza.session.generateOrderNumber());
        subLabel.layoutXProperty().bind(this.widthProperty().subtract(subLabel.widthProperty()).divide(2));
        subLabel.setLayoutY(420);
        subLabel.setFont(new Font("Arial", 24));
        confirmationButton = new Button("Order Status");
        confirmationButton.setPrefSize(160, 60);
        confirmationButton.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey;");
        confirmationButton.layoutXProperty().bind(this.widthProperty().subtract(confirmationButton.widthProperty()).divide(2));
        confirmationButton.setLayoutY(480);
        confirmationButton.setOnAction(new OrderConfirmationControlsHandler());
        getChildren().addAll(headerLabel, subLabel, confirmationButton);
    }
    
    private class OrderConfirmationControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == confirmationButton) {
                SunDevilPizza.clearRoots();
            }
        }
    }
}
