import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PizzaBuilderUI extends Pane {
    //Declaring Variables...
    private final String[] toppingsList = new String[] {"Pepperoni", "Sausage", "Mushroom", "Green Pepper", "Sardines", "Tomato", "Deez Nuts"};
    private final String[] sizeButtonText = new String[] {"S", "M", "L"};
    private final String[] typeButtonText = new String[] {"C", "M", "V"};
    private final String[] toppingButtonText = new String[] {"Lt", "Reg", "Ex"};
    private Button backButton;
    private Button forwardButton;
    private Label customizationLabel;
    private Label pizzaToppingsLabel;
    private ScrollPane toppingsSP;
    private LargeSelectionBar sizeSelector;
    private LargeSelectionBar pizzaBaseSelector;
    private Label orderSummaryLabel;
    private TextField orderSummaryTextField;
    private String pizzaSize;
    private String pizzaBase;
    private String[] toppings;
    
    //Constructor
    PizzaBuilderUI(int width, int height) {
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
        sizeSelector = new LargeSelectionBar("size", "Size:", 3, sizeButtonText);
        sizeSelector.relocate(100, 160);
        pizzaBaseSelector = new LargeSelectionBar("type", "Type:", 3, typeButtonText);
        pizzaBaseSelector.relocate(100, 320);
        orderSummaryLabel = new Label("Order Summary:");
        orderSummaryLabel.relocate(1000, 520);
        orderSummaryLabel.setFont(new Font("Arial", 40));
        orderSummaryTextField = new TextField();
        orderSummaryTextField.setPrefWidth(600);
        orderSummaryTextField.setPrefHeight(200);
        orderSummaryTextField.relocate(1000, 600);
        orderSummaryTextField.setEditable(false);
        backButton = new ButtonMaker("back");
        backButton.setOnAction(new PizzaBuilderControlsHandler());
        forwardButton = new ButtonMaker("forward");
        forwardButton.setOnAction(new PizzaBuilderControlsHandler());
        getChildren().addAll(customizationLabel, sizeSelector, pizzaBaseSelector, pizzaToppingsLabel, toppingsSP, orderSummaryLabel, orderSummaryTextField, backButton, forwardButton);
    }
   
    private Pane createToppingsPane() {
        Pane toppingsBasePane = new Pane();
        toppingsBasePane.setPrefWidth(400);
        int toppingsSelectionBarBaseY = 5;
        for (String currentTopping : toppingsList) {
            SelectionBar bar = new SelectionBar("topping", currentTopping, 3, toppingButtonText);
            bar.relocate(5, toppingsSelectionBarBaseY);
            toppingsSelectionBarBaseY += 40;
            toppingsBasePane.getChildren().add(bar);
        }
        return toppingsBasePane;
    }
    
    //Handler for all UI controls...
    private class PizzaBuilderControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == backButton) {
                SunDevilPizza.previousRoot();
            }
            if (event.getSource() == forwardButton) {
                if (SunDevilPizza.nextRoot() == false) {
                    SunDevilPizza.newRoot(new OrderSummaryUI(SunDevilPizza.width, SunDevilPizza.height));
                }
            }
        }
    }
}
