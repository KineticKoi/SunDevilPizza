import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PizzaBuilderUI extends Pane {
    //Declaring Variables...
    private final String[] toppingsList = new String[] {"Mushroom", "Onion", "Olives", "Extra Cheese"};
    private final String[] sizeButtonText = new String[] {"Small", "Medium", "Large"};
    private final String[] typeButtonText = new String[] {"Cheese", "Pepperoni", "Vegetable"};
    private final String[] toppingButtonText = new String[] {"Lt", "Reg", "Ex"};
    private String[] timeOptions = new String[] {"12:00PM", "12:15PM", "12:30PM", "12:45PM", 
                                                    "1:00PM", "1:15PM", "1:30PM", "1:45PM", 
                                                    "2:00PM", "2:15PM", "2:30PM", "2:45PM", 
                                                    "3:00PM", "3:15PM", "3:30PM", "3:45PM", 
                                                    "4:00PM", "4:15PM", "4:30PM", "4:45PM", 
                                                    "5:00PM", "5:15PM", "5:30PM", "5:45PM", 
                                                    "6:00PM", "6:15PM", "6:30PM", "6:45PM",
                                                    "7:00PM", "7:15PM", "7:30PM", "7:45PM",
                                                    "8:00PM", "8:15PM", "8:30PM", "8:45PM",
                                                    "9:00PM", "9:15PM", "9:30PM", "9:45PM"};
    private Button backButton;
    private Button forwardButton;
    private Label customizationLabel;
    private Label pizzaToppingsLabel;
    private Label timeSelectionLabel;
    private ChoiceBox<String> timePicker;
    private ScrollPane toppingsSP;
    private LargeSelectionBar sizeSelector;
    private LargeSelectionBar pizzaBaseSelector;
    private Label orderSummaryLabel;
    private TextArea orderSummaryTextField;
    private String pizzaSize;
    private String pizzaBase;
    private String[] toppings;
    
    //Constructor
    PizzaBuilderUI(int width, int height) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF");
        customizationLabel = new Label("Pizza Base Customization:");
        customizationLabel.relocate(100, 80);
        customizationLabel.setFont(new Font("Arial", 40));
        pizzaToppingsLabel = new Label("Pizza Toppings:");
        pizzaToppingsLabel.relocate(100, 520);
        pizzaToppingsLabel.setFont(new Font("Arial", 40));
        timeSelectionLabel = new Label("Pickup Time:");
        timeSelectionLabel.relocate(100, 830);
        timeSelectionLabel.setFont(new Font("Arial", 40));
        timePicker = new ChoiceBox<>();
        timePicker.getItems().addAll("12:00PM", "12:15PM", "12:30PM", "12:45PM", 
                                    "1:00PM", "1:15PM", "1:30PM", "1:45PM", 
                                    "2:00PM", "2:15PM", "2:30PM", "2:45PM", 
                                    "3:00PM", "3:15PM", "3:30PM", "3:45PM", 
                                    "4:00PM", "4:15PM", "4:30PM", "4:45PM", 
                                    "5:00PM", "5:15PM", "5:30PM", "5:45PM", 
                                    "6:00PM", "6:15PM", "6:30PM", "6:45PM",
                                    "7:00PM", "7:15PM", "7:30PM", "7:45PM",
                                    "8:00PM", "8:15PM", "8:30PM", "8:45PM",
                                    "9:00PM", "9:15PM", "9:30PM", "9:45PM");
        timePicker.setMinSize(240, 40);
        timePicker.relocate(100, 900);
        timePicker.setOnAction(new PizzaBuilderControlsHandler());
        toppingsSP = new ScrollPane();
        toppingsSP.relocate(100, 600);
        toppingsSP.setPrefWidth(600);
        toppingsSP.setPrefHeight(170);
        toppingsSP.setContent(createToppingsPane());
        sizeSelector = new LargeSelectionBar("size", "Size:", 3, sizeButtonText);
        sizeSelector.relocate(100, 160);
        pizzaBaseSelector = new LargeSelectionBar("type", "Type:", 3, typeButtonText);
        pizzaBaseSelector.relocate(100, 320);
        orderSummaryLabel = new Label("Order Summary:");
        orderSummaryLabel.relocate(1220, 520);
        orderSummaryLabel.setFont(new Font("Arial", 40));
        orderSummaryTextField = new TextArea();
        orderSummaryTextField.setPrefWidth(600);
        orderSummaryTextField.setPrefHeight(170);
        orderSummaryTextField.relocate(1220, 600);
        orderSummaryTextField.setEditable(false);
        backButton = new ButtonMaker("back");
        backButton.setOnAction(new PizzaBuilderControlsHandler());
        forwardButton = new ButtonMaker("forward");
        forwardButton.setOnAction(new PizzaBuilderControlsHandler());
        getChildren().addAll(customizationLabel, sizeSelector, pizzaBaseSelector, pizzaToppingsLabel, toppingsSP, timeSelectionLabel, timePicker, orderSummaryLabel, orderSummaryTextField, backButton, forwardButton);
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
    
    public void refreshOrderSummary() {
        Pizza currentPizza = ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza();
        String orderSummary = "Type: " + currentPizza.getType() + "\n" + "Size: " + currentPizza.getSize() + "\n" + "Toppings: \n\t";
        for (int i = 0; i < currentPizza.getToppings().size(); i++) {
            orderSummary = orderSummary + currentPizza.getToppings().get(i) + "\n\t";
        }
        orderSummary = orderSummary + "\n\nPickup Time: " + ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPickupTime();
        orderSummaryTextField.setText(orderSummary);
    }
    
    //Handler for all UI controls...
    private class PizzaBuilderControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == backButton) {
                SunDevilPizza.previousRoot();
            }
            else if (event.getSource() == forwardButton) {     
                Pizza currentPizza = ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza();
                if (currentPizza.getSize() != null && currentPizza.getType() != null) {
                    if (SunDevilPizza.nextRoot() == false) {
                        SunDevilPizza.newRoot(new OrderSummaryUI(SunDevilPizza.width, SunDevilPizza.height, orderSummaryTextField.getText()));
                    }
                }
                else {
                    customizationLabel.setStyle("-fx-text-fill: red;");
                }
            }
            else if (event.getSource() == timePicker) {
                ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().setPickupTime(timePicker.getValue());
                refreshOrderSummary();
            }
        }
    }
}
