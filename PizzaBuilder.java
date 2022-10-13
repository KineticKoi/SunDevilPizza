import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PizzaBuilder extends Pane {
    //Declaring Variables...
    private Button startButton;
    private Button backButton;
    private Label customizationLabel;
    private Label pizzaToppingsLabel;
    private ScrollPane toppingsSP;
    private Label orderSummaryLabel;
    private String[] toppingsList = new String[] {"Pepperoni", "Sausage", "Mushroom", "Green Pepper", "Sardines", "Tomato", "Deez Nuts"};
    
    //Constructor
    PizzaBuilder(int width, int height) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF");
        customizationLabel = new Label("Pizza Customization:");
        customizationLabel.relocate(100, 80);
        customizationLabel.setFont(new Font("Arial", 40));
        pizzaToppingsLabel = new Label("Pizza Toppings:");
        pizzaToppingsLabel.relocate(100, 520);
        pizzaToppingsLabel.setFont(new Font("Arial", 40));
        toppingsSP = new ScrollPane();
        toppingsSP.relocate(100, 600);
        toppingsSP.setPrefWidth(600);
        toppingsSP.setPrefHeight(200);
        toppingsSP.setContent(createToppingsPane());
        orderSummaryLabel = new Label("Order Summary:");
        orderSummaryLabel.relocate(1000, 520);
        orderSummaryLabel.setFont(new Font("Arial", 40));
        backButton = new ButtonMaker("back");
        backButton.setOnAction(new PizzaBuilderControlsHandler());
        getChildren().addAll(customizationLabel, pizzaToppingsLabel, toppingsSP, orderSummaryLabel, backButton);
    }
   
    private Pane createToppingsPane() {
        Pane toppingsBasePane = new Pane();
        toppingsBasePane.setPrefWidth(400);
        int toppingsSelectionBarBaseY = 5;
        for (String currentTopping : toppingsList) {
            SelectionPane bar = new SelectionPane("topping", currentTopping, 3);
            bar.relocate(5, toppingsSelectionBarBaseY);
            toppingsSelectionBarBaseY += 40;
            toppingsBasePane.getChildren().add(bar);
        }
        return toppingsBasePane;
    }
    
    private class PizzaBuilderControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == backButton) {
                SunDevilPizza.previousRoot();
            }
        }
    }
}
