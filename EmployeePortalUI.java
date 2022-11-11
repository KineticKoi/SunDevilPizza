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

// Class to build Employee portal UI pane
public class EmployeePortalUI extends Pane
{    
    // Declaring Variables
    private Label headerLabel;
    private Label message;
    private Button homeButton;
    private List<Customer> queue = new ArrayList<>();
    private ScrollPane queueSP;
    private final String[] opaButtonText = new String[] {"READY TO COOK"};
    private final String[] chefButtonText = new String[] {"COOKING", "READY"};
    private String type;
    
    // Constructor
    EmployeePortalUI(int width, int height, String type)
    {
        // Sets this pane width
        setWidth(width);
        // Sets this pane height
        setHeight(height);
        // Sets pane background color to white
        setStyle("-fx-background-color: #FFFFFF");
        // Sets current employee type to type in constructor parameters
        this.type = type;

        // Creates new button with text "home"
        homeButton = new ButtonMaker("home");
        // Setting up eventhandler for pane's buttons
        homeButton.setOnAction(new AdminPortalControlsHandler());
        
        // Creates new scrollpane
        queueSP = new ScrollPane();
        // Sets scrollpane preffered width to 800
        queueSP.setPrefWidth(800);
        // Sets scrollpane preffered height to 200
        queueSP.setPrefHeight(200);
        // Sets scrollpane centering X value
        queueSP.layoutXProperty().bind(this.widthProperty().subtract(queueSP.getPrefWidth()).divide(2));
        // Moving scrollPane down slightely
        queueSP.layoutYProperty().set(400);
        // Creates a new file array for the file path to customer records file
        File[] customerFileList = new File(SunDevilPizza.customerFilesPath).listFiles();
        // Makes a method call to iterateCustomers and passes in the newly created customerFileList object
        iterateCustomers(customerFileList);
        // Sets scrollpane content by making a method call to create a new queuepane
        queueSP.setContent(createQueuePane());
        
        // Sets label's text to "Queue"
        headerLabel = new Label(type + " Queue:");
        // Sets label's font and text size
        headerLabel.setFont(new Font("Arial", 40));
        // Sets label's centering X value
        headerLabel.layoutXProperty().bind(this.widthProperty().subtract(queueSP.getPrefWidth()).divide(2)); 
        // Moving scrollPane down slightely
        headerLabel.layoutYProperty().set(320); 
        
        message = new Label("");
        message.setFont(new Font("Arial", 32));
        message.layoutXProperty().bind(this.widthProperty().subtract(queueSP.getPrefWidth()).divide(2));
        message.layoutYProperty().set(640);
        
        // Adding everything to pane
        getChildren().addAll(headerLabel, homeButton, queueSP, message);
    }
    
    // Method iterates through customer order records to check whether for which orders have finished
    // being processed and which need to be added to the queue so order processing
    // agents and chefs can interact with them to update pizza statuses
    public void iterateCustomers(File[] files)
    {
        // Looping for amount of customer orders in records
        for (File file : files)
        {
            // Creating new file object by calling the FileManager class
            Customer customer = FileManager.loadCustomer(file.getName().split("[.]")[0]);
            // Looping through customer's order history to verify past order's pizza statuses
            for (int i = 0; i < customer.getOrderHistory().size(); i++)
            {
                // Checks if current employee type is the "Order Processing Agent" and checks if the status of the
                // current indexed order status is set to "Accepted"
                if (type.equalsIgnoreCase("Order Processing Agent") &&
                    customer.getOrderHistory().get(i).getStatus().equalsIgnoreCase("ACCEPTED"))
                {
                    // Adds customer's order to current queue
                    queue.add(customer);
                } 
                // Checks if current employee type is the "Chef" and checks if the status of the current indexed order
                // status is set to "Ready to cook" or "Cooking" before adding customer order to queue
                else if (type.equalsIgnoreCase("Chef") &&
                    customer.getOrderHistory().get(i).getStatus().equals("READY TO COOK") ||
                    customer.getOrderHistory().get(i).getStatus().equals("COOKING"))
                {
                    // Adds customer's order to current queue
                    queue.add(customer);
                }
            }
        }
    }

    // Method for employees to update order queue status
    public void updateQueue(String orderNumber, String newStatus, SelectionBar bar)
    {
        // Loops for however many customers have orders in queue
        for(Customer customer : queue)
        {
            // Loops for each customer's order history
            for(Order order : customer.getOrderHistory())
            {
                // Checks if the customer's order number is the same as order that is currently being processed
                if (order.getOrderNumber().equals(orderNumber))
                {
                    // Updates order status to pizza's current status
                    order.setStatus(newStatus);
                }
            }
            // Saves customer's order information to system
            FileManager.saveCustomer(customer);
        }
        // Checking if order is just coming in or if it is ready to be removed from queue
        if (newStatus.equals("READY TO COOK") || newStatus.equals("READY"))
        {
            // Updates queue
            ((Pane)queueSP.getContent()).getChildren().remove(bar);
            // If the order is ready
            if (newStatus.equals("READY"))
            {
                // "Sends email"...
                message.setText("Email sent for order " + orderNumber);
            }
        }
    }
    
    // Method creates pane for chef and order processing agents to make changes to pizza order statuses
    private Pane createQueuePane()
    {
        // Creates new pane
        Pane toppingsBasePane = new Pane();
        // Sets pane's preffered width to 400
        toppingsBasePane.setPrefWidth(780);
        // Setting base int value
        int toppingsSelectionBarBaseY = 5;
        // Looping for customer orders in queue
        for(Customer customer : queue)
        {
            // Looping for each customer's order history
            for(Order order : customer.getOrderHistory())
            {
                // Crreats a selectionBar
                SelectionBar bar;
                // Checking if Order Processing Agent is current session user and if current indexed order in customer
                // list has "ACCEPTED" status 
                if (type.equalsIgnoreCase("Order Processing Agent")
                    && order.getStatus().equalsIgnoreCase("ACCEPTED"))
                {
                    // Creating a new selectionBar to be added to the queue pane being constructed which includes an
                    // order's number and status 
                    bar = new SelectionBar(600, "status",
                        order.getOrderNumber(), 1, opaButtonText, 400, 140);
                    // Relocating selectionBar to (5, (current value of toppingsSelectionBarBaseY after arithmetic))
                    bar.relocate(5, toppingsSelectionBarBaseY);
                    // Incrementing integer by 40 each loop iteration
                    toppingsSelectionBarBaseY += 40;
                    // Adding selectionBar to base pane
                    toppingsBasePane.getChildren().add(bar);
                }
                // Checking if Chef is current session user and if current indexed order in customer list has "READY"
                // status
                else if (type.equalsIgnoreCase("Chef") &&
                    !order.getStatus().equalsIgnoreCase("READY"))
                {
                    // Creating a new selectionBar to be added to the queue pane being constructed which includes an order's number and status
                    bar = new SelectionBar(600, "status",
                        order.getOrderNumber(), 2, chefButtonText, 120, 140);
                    // Relocating selectionBar to (5, (current value of toppingsSelectionBarBaseY after arithmetic))
                    bar.relocate(5, toppingsSelectionBarBaseY);
                    // Incrementing integer by 40 each loop iteration
                    toppingsSelectionBarBaseY += 40;
                    // Adding selectionBar to base pane
                    toppingsBasePane.getChildren().add(bar);
                }
            }
        }
        // Returns completed queue pane
        return toppingsBasePane;
    }
    
    private class AdminPortalControlsHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(javafx.event.ActionEvent event)
        {
            Sounds.playButtonClick();
            if (event.getSource() == homeButton)
            {
                SunDevilPizza.session.setUser(null);
                SunDevilPizza.home();
            }
        }
    }
    
}