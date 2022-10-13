
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PizzaBuilder extends Pane {
    //Declaring Variables...
    private Button startButton;
    private Button backButton;
    private Label customizationLabel;
    private Label pizzaToppingsLabel;
    private Label orderSummaryLabel;
    
    //Constructor
    PizzaBuilder(int width, int height) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF");
        customizationLabel = new Label("Pizza Customization:");
        customizationLabel.relocate(100, 100);
        customizationLabel.setFont(new Font("Arial", 48));
        pizzaToppingsLabel = new Label("Pizza Toppings:");
        pizzaToppingsLabel.relocate(100, 600);
        pizzaToppingsLabel.setFont(new Font("Arial", 48));
        orderSummaryLabel = new Label("Order Summary:");
        orderSummaryLabel.relocate(1100, 600);
        orderSummaryLabel.setFont(new Font("Arial", 48));
        backButton = new ButtonMaker("back");
        backButton.setOnAction(new ControlsHandler());
        getChildren().addAll(customizationLabel, pizzaToppingsLabel, orderSummaryLabel, backButton);
    }
    
    private class ControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == backButton) {
                SunDevilPizza.sceneSwitcher(new WelcomePage(SunDevilPizza.width, SunDevilPizza.height));
            }
        }
    }
}
