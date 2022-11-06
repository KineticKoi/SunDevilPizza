
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

//Class to build Employee portal UI pane
public class EmployeePortalUI extends Pane{
    //Declaring Variables...
    private Label headerLabel;
    private Button homeButton;
    private List<Customer> queue = new ArrayList<>();
    private ScrollPane queueSP;
    private final String[] opaButtonText = new String[] {"READY TO COOK"};
    private final String[] chefButtonText = new String[] {"COOKING", "READY FOR PICKUP"};
    private String type;
    
    //Constructor
    EmployeePortalUI(int width, int height, String type) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF"); //Sets pane background color to white
        this.type = type; //Sets current employee type to type in constructor parameters
        homeButton = new ButtonMaker("home"); //Creates new button with text "home"
        queueSP = new ScrollPane(); //Creates new scrollpane
        queueSP.setPrefWidth(800); //Sets scrollpane width to 800
        queueSP.setPrefHeight(200); //Sets scrolpane height to 200
        queueSP.layoutXProperty().bind(this.widthProperty().subtract(queueSP.getPrefWidth()).divide(2)); //Sets scrollpane centering X value 
        queueSP.layoutYProperty().set(400); //Sets scrollpane centering Y value
        File[] customerFileList = new File(SunDevilPizza.customerFilesPath).listFiles(); //Creates a new file array for the file path to customer records file 
        iterateCustomers(customerFileList); //Makes a method call to iterateCustomers and passes in the newly created customerFileList object
        queueSP.setContent(createQueuePane()); //Sets scrollpane content by making a method call to create a new queuepane 
        headerLabel = new Label(type + " Queue:"); //Sets label's text to "Queue"
        headerLabel.setFont(new Font("Arial", 40)); //Sets label's font and text size
        headerLabel.layoutXProperty().bind(this.widthProperty().subtract(queueSP.getPrefWidth()).divide(2)); //Sets label's centering X value
        headerLabel.layoutYProperty().set(320); //Sets label's centering Y value 
        homeButton.setOnAction(new AdminPortalControlsHandler()); //Setting up eventhandler for pane's buttons
        getChildren().addAll(headerLabel, homeButton, queueSP); //Adding label, home button, and queue pane to main pane 
    }
    
    //Method iterates through customer order records to check whether for which orders have finished being processed and which
    // need to be added to the queue so order processing agents and chefs can interact with them to update pizza statuses
    public void iterateCustomers(File[] files) {
        for (File file : files) {/ //Looping for amount of customer orders in records
            Customer customer = FileManager.loadCustomer(file.getName().split("[.]")[0]); //Creating new 
            for (int i = 0; i < customer.getOrderHistory().size(); i++) { //Looping through each customer's order history to verify what stage their past orders are in 
                if (type.equals("Order Processing Agent") && customer.getOrderHistory().get(i).getStatus().equalsIgnoreCase("ACCEPTED")) {
                    //Checks if current employee type is the "Order Processing Agent" and checks if the status of the current indexed order status is set to "Accepted"
                    queue.add(customer); //Adds customer to current queue
                    break; //Breaks to continue iterating through customer order records
                } 
                else if (type.equalsIgnoreCase("Chef") && customer.getOrderHistory().get(i).getStatus().equals("READY TO COOK") || customer.getOrderHistory().get(i).getStatus().equals("COOKING")) {
                    //Checks if current employee type is the "Chef" and checks if the status of the current indexed
                    //  order status is set to "Ready to cook" or "Cooking"
                    queue.add(customer); //Adds customer to current queue
                    break; //Breaks to continue iterating through customer order records
                }
            }
        } 
    } //End of iterateCustomers method
    
    //Method for employees to update order queue status
    public void updateQueue(String orderNumber, String newStatus, SelectionBar bar) {
        for(Customer customer : queue) { //Loops for however many customers have orders in queue
            for(Order order : customer.getOrderHistory()) { //Loops for each customer's order history
                if (order.getOrderNumber().equals(orderNumber)) { //Checks if the customer's order number is the same as order that is currently being processed
                    order.setStatus(newStatus); //Updates order status to pizza's current status 
                }
            }
            FileManager.saveCustomer(customer); //Saves customer's order information to system
        }
        if (newStatus.equals("READY TO COOK") || newStatus.equals("READY FOR PICKUP")) { //Checking if order is just coming in or if it is ready to be removed from queue
            ((Pane)queueSP.getContent()).getChildren().remove(bar); 
        }
    } //End of updateQueue method
    
    //Method creates pane for chef and order processing agents to make changes to pizza order statuses
    private Pane createQueuePane() {
        Pane toppingsBasePane = new Pane(); //Creates new pane 
        toppingsBasePane.setPrefWidth(400); //Sets pane's preferred width to 400
        int toppingsSelectionBarBaseY = 5; 
        for(Customer customer : queue) { //Looping for customer orders in queue
            for(Order order : customer.getOrderHistory()) { //Looping for each customer's order history
                SelectionBar bar;
                if (type.equalsIgnoreCase("Order Processing Agent") && order.getStatus().equalsIgnoreCase("ACCEPTED")) { //Checking if 
                    bar = new SelectionBar("status", order.getOrderNumber(), 1, opaButtonText, 400, 140);
                    bar.relocate(5, toppingsSelectionBarBaseY); //Relocates selection bar to (5, toppingsSelectionBarBaseY) 
                    toppingsSelectionBarBaseY += 40;
                    toppingsBasePane.getChildren().add(bar); //Adding new selection bar to base pane
                }
                else if (type.equalsIgnoreCase("Chef") && order.getStatus().equalsIgnoreCase("READY TO COOK")) { //Checking if 
                    bar = new SelectionBar("status", order.getOrderNumber(), 2, chefButtonText, 120, 140);
                    bar.relocate(5, toppingsSelectionBarBaseY); //Relocates selection bar to (5, toppingsSelectionBarBaseY)
                    toppingsSelectionBarBaseY += 40;
                    toppingsBasePane.getChildren().add(bar); //Adding new selection bar to base pane
                }
            }
        }
        return toppingsBasePane; //Returning newly created pane to EmployeePortalUI main pane
    } //End of createQueuePane method
    
    //Event handler for pane's home button and audio for clicking 
    private class AdminPortalControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick(); //Calls sound class to play audio when pane is clicked
            if (event.getSource() == homeButton) { //Checks if home button is clicked
                SunDevilPizza.session.setUser(null); //Sets current session to null, logging out admin user
                SunDevilPizza.home(); //Takes user back to main landing page when home button is clicked
            }
        }
    } //End of AdminPortalControlsHandler event handler class
    
} //End of EmployeePortalUI class
