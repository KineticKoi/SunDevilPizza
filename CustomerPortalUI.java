import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

// Class to build Customer portal UI pane 
public class CustomerPortalUI extends Pane
{
    // Declaring Variables
    private Label currentOrdersLabel;
    private Label orderHistoryLabel;
    private ScrollPane currentOrderSP;
    private ScrollPane orderHistorySP;
    private List<Order> currentOrderList = new ArrayList<>();
    private List<Order> previousOrderList = new ArrayList<>();
    private Button homeButton;
    
    // Constructor
    CustomerPortalUI(int width, int height)
    {
        // Sets this pane's width
        setWidth(width);
        // Sets this pane's height
        setHeight(height);
        // Sets background to be white
        setStyle("-fx-background-color: #FFFFFF");
        
        // Configure currentOrdersLabel
        configureCurrentOrdersLabel();
        
        // Configure currentOrderSP
        configureCurrentOrderSP();

        // Configure orderHistoryLabel
        configureOrderHistoryLabel();
        
        // Configure orderHistorySP
        configureOrderHistorySP();

        // Configure homeButton
        configureHomeButton();
        
        // Adds everything to pane
        getChildren().addAll(currentOrdersLabel, currentOrderSP, orderHistoryLabel, orderHistorySP, homeButton);
    }

    public void configureHomeButton()
    {
        // Creates new button with text "home"
        homeButton = new ButtonMaker("home");
        // Sets up home buttons handler
        homeButton.setOnAction(new CustomerPortalControlsHandler());
    }

    public void configureOrderHistorySP()
    {
        // Creates new scrollpane
        orderHistorySP = new ScrollPane();
        // Relocates new scrollpane to (100, 600)
        orderHistorySP.relocate(100, 600);
        // Sets scrollpane's preffered wdith to 800
        orderHistorySP.setPrefWidth(800);
        // Sets scrollpane's preffered height to 240
        orderHistorySP.setPrefHeight(240);
        // Sets scrollpane's contents to be a list of previous pizza orders
        orderHistorySP.setContent(createQueuePane(previousOrderList));
    }

    public void configureOrderHistoryLabel()
    {
        // Creates new label with text "Previous Orders"
        orderHistoryLabel = new Label("Previous Orders:");
        // Sets new label's text font and size
        orderHistoryLabel.setFont(new Font("Arial", 40));
        // Relocates label to (100, 520)
        orderHistoryLabel.relocate(100, 520);
    }

    public void configureCurrentOrderSP()
    {
        // Creates new scrollpane
        currentOrderSP = new ScrollPane();
        // Relocates new scrollpane to (100, 160)
        currentOrderSP.relocate(100, 160);
        // Sets scrollpane's preffered width to 800
        currentOrderSP.setPrefWidth(800);
        // Sets scrollpane's preffered height to 240
        currentOrderSP.setPrefHeight(240);
        // Sets scrollpane's contents to be a list of current pizza orders
        currentOrderSP.setContent(createQueuePane(currentOrderList));
    }

    // Method for configuring the current orders label
    public void configureCurrentOrdersLabel()
    {
        // Creates label with text "Current Orders"
        currentOrdersLabel = new Label("Current Orders:");
        // Sets label text font and size
        currentOrdersLabel.setFont(new Font("Arial", 40));
        // Relocates new label to (100, 80)
        currentOrdersLabel.relocate(100, 80);
        // Iterate through customer's orders and filter into currentOrderList or
        // previousOrderList
        iterateOrders();
    }
    
    // Method for iterating through a customer's orders
    public void iterateOrders()
    {
        Customer customer = (Customer)SunDevilPizza.session.getUser();
        // Looping through a customer's complete pizza order history
        for (int i = 0; i < customer.getOrderHistory().size(); i++)
        {
            // Ignoring any previous orders with the status ready
            if (!customer.getOrderHistory().get(i).getStatus().equalsIgnoreCase("READY"))
            {
                // Adding all other orders that are not listed as "READY" to the current list
                currentOrderList.add(customer.getOrderHistory().get(i));
            }
            else
            {
                // Adding all pizza's with status "READY" to the customer's previous order's list
                previousOrderList.add(customer.getOrderHistory().get(i));
            }
        } 
    }
    
    // Method for creating a new queuePane given a list of a customer's orders
    private Pane createQueuePane(List<Order> orderList)
    {
        // Creates new pane
        Pane orderBasePane = new Pane();
        // Sets new pane's preffered width to 700
        orderBasePane.setPrefWidth(780);
        // Setting base int value
        int toppingsSelectionBarBaseY = 5;
        // Creating a selectionBar
        SelectionBar bar;
        // Looping for number of order's in a customer's list
        for (int i = 0; i < orderList.size(); i++)
        {
            // Creating a new selectionBar to be added to the queue pane being constructed which includes an order's number and status 
            bar = new SelectionBar(780, "",
                orderList.get(i).getOrderNumber() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + 
                orderList.get(i).getStatus(), 0, null, 400, 140);
            // Relocating selectionBar to (5, (current value of toppingsSelectionBarBaseY after arithmetic))
            bar.relocate(5, toppingsSelectionBarBaseY);
            // Incrementing integer by 40 each loop iteration
            toppingsSelectionBarBaseY += 40;
            // Adding selectionBar to base pane
            orderBasePane.getChildren().add(bar);
        }
        // Returning completed queue pane
        return orderBasePane;
    }
    
    // Button handler for pane including audible button click sound and home button
    private class CustomerPortalControlsHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            Sounds.playButtonClick();
            if (event.getSource() == homeButton)
            {
                SunDevilPizza.home();
            }
        }
    }
    
}