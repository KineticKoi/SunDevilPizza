import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PizzaBuilderUI extends Pane
{
    // Declaring Variables
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
    
    // Constructor
    PizzaBuilderUI(int width, int height)
    {
        // Sets this pane width
        setWidth(width);
        // Sets this pane height
        setHeight(height);
        // setting background color
        setStyle("-fx-background-color: #FFFFFF");
        // setting default labels and buttons
        customizationLabel = new Label("Pizza Base Customization:");
        customizationLabel.relocate(100, 80);
        customizationLabel.setFont(new Font("Arial", 40));
        pizzaToppingsLabel = new Label("Pizza Toppings:");
        pizzaToppingsLabel.relocate(100, 520);
        pizzaToppingsLabel.setFont(new Font("Arial", 40));
        timeSelectionLabel = new Label("Pickup Time:");
        timeSelectionLabel.relocate(100, 830);
        timeSelectionLabel.setFont(new Font("Arial", 40));
        // creating a choice box for the time to pick up the order
        timePicker = new ChoiceBox<>();
        // order pickup options
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
        // calling the control handler when a button is pressed
        timePicker.setOnAction(new PizzaBuilderControlsHandler());
        toppingsSP = new ScrollPane();
        toppingsSP.relocate(100, 600);
        toppingsSP.setPrefWidth(600);
        toppingsSP.setPrefHeight(170);
        // creating a topping pane
        toppingsSP.setContent(createToppingsPane());
        // importing the selection bar for pizza size
        sizeSelector = new LargeSelectionBar("size", "Size:", 3, sizeButtonText);
        sizeSelector.relocate(100, 160);
        // importing selection bar the pizza base
        pizzaBaseSelector = new LargeSelectionBar("type", "Type:", 3, typeButtonText);
        pizzaBaseSelector.relocate(100, 320);
        // creating an order summary label
        orderSummaryLabel = new Label("Order Summary:");
        orderSummaryLabel.relocate(1220, 520);
        orderSummaryLabel.setFont(new Font("Arial", 40));
        // creating a text field for the order summary
        orderSummaryTextField = new TextArea();
        orderSummaryTextField.setPrefWidth(600);
        orderSummaryTextField.setPrefHeight(170);
        orderSummaryTextField.relocate(1220, 600);
        // making sure that only the program can output to the text field, not the user
        orderSummaryTextField.setEditable(false);
        // button for moving back between nodes
        backButton = new ButtonMaker("back");
        backButton.setOnAction(new PizzaBuilderControlsHandler());
        // button for moving forward btween nodes
        forwardButton = new ButtonMaker("forward");
        forwardButton.setOnAction(new PizzaBuilderControlsHandler());
        getChildren().addAll(customizationLabel, sizeSelector, pizzaBaseSelector, pizzaToppingsLabel, toppingsSP,
            timeSelectionLabel, timePicker, orderSummaryLabel, orderSummaryTextField, backButton, forwardButton);
    }
   
    private Pane createToppingsPane()
    {
        // creating a pane for the toppings
        Pane toppingsBasePane = new Pane();
        // setting width of the topping pane
        toppingsBasePane.setPrefWidth(400);
        // horizontal of the toppings selection bar
        int toppingsSelectionBarBaseY = 5;
        // defining the toppings for each topping in the list
        for (String currentTopping : toppingsList)
        {
            // new selection bar for the toppings
            SelectionBar bar = new SelectionBar(600, "topping",
                currentTopping, 3, toppingButtonText, 280, 60);
            // setting space for the selection bar
            bar.relocate(5, toppingsSelectionBarBaseY);
            toppingsSelectionBarBaseY += 40;
            toppingsBasePane.getChildren().add(bar);
        }
        return toppingsBasePane;
    }
    
    // method to ensure that the order summary is current
    public void refreshOrderSummary()
    {
        // getting the pizza object for the order
        Pizza currentPizza = ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza();
        // creating an order summary
        String orderSummary = "Type: " + currentPizza.getType() + "\n" + "Size: " +
            currentPizza.getSize() + "\n" + "Toppings: \n\t";
        // for loop to delineate toppings by newlines and tabs
        for (int i = 0; i < currentPizza.getToppings().size(); i++)
        {
            orderSummary = orderSummary + currentPizza.getToppings().get(i) + "\n\t";
        }
        // adding the pickup time to be displayed after two newlines
        orderSummary = orderSummary + "\n\nPickup Time: " +
            ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPickupTime();
        orderSummaryTextField.setText(orderSummary);
    }
    
    // Handler for all UI controls
    private class PizzaBuilderControlsHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            Sounds.playButtonClick();
            if (event.getSource() == backButton)
            {
                SunDevilPizza.previousRoot();
            }
            else if (event.getSource() == forwardButton)
            {
                // Reset labels to black
                customizationLabel.setStyle("-fx-text-fill: black;");
                timeSelectionLabel.setStyle("-fx-text-fill: black;");
                // Get the current pizza
                Pizza currentPizza = ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza();
                // If the pizza customization options are null
                if (currentPizza.getSize() == null || currentPizza.getType() == null)
                {
                    // Change label to red
                    customizationLabel.setStyle("-fx-text-fill: red;");
                }
                // If the pickup time option is null
                if (timePicker.getValue() == null)
                { 
                    // Change label to red
                    timeSelectionLabel.setStyle("-fx-text-fill: red;");
                }
                if (currentPizza.getSize() != null && currentPizza.getType() != null && timePicker.getValue() != null)
                {
                    if (SunDevilPizza.nextRoot() == false)
                    {
                        SunDevilPizza.newRoot(new OrderSummaryUI(SunDevilPizza.width,
                            SunDevilPizza.height, orderSummaryTextField.getText()));
                    }
                }
            }
            else if (event.getSource() == timePicker)
            {
                ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().setPickupTime(timePicker.getValue());
                refreshOrderSummary();
            }
        }
    }
}