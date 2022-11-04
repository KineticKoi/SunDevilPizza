
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

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
        setStyle("-fx-background-color: #FFFFFF");
        this.type = type;
        homeButton = new ButtonMaker("home");
        queueSP = new ScrollPane();
        queueSP.setPrefWidth(800);
        queueSP.setPrefHeight(200);
        queueSP.layoutXProperty().bind(this.widthProperty().subtract(queueSP.getPrefWidth()).divide(2));
        queueSP.layoutYProperty().set(400);
        File[] customerFileList = new File(SunDevilPizza.customerFilesPath).listFiles();
        iterateCustomers(customerFileList);
        queueSP.setContent(createQueuePane());
        headerLabel = new Label(type + " Queue:");
        headerLabel.setFont(new Font("Arial", 40));
        headerLabel.layoutXProperty().bind(this.widthProperty().subtract(queueSP.getPrefWidth()).divide(2));
        headerLabel.layoutYProperty().set(320);
        homeButton.setOnAction(new AdminPortalControlsHandler());
        getChildren().addAll(headerLabel, homeButton, queueSP);
    }
    
    public void iterateCustomers(File[] files) {
        for (File file : files) {
            Customer customer = FileManager.loadCustomer(file.getName().split("[.]")[0]);
            for (int i = 0; i < customer.getOrderHistory().size(); i++) {
                if (type.equals("Order Processing Agent") && customer.getOrderHistory().get(i).getStatus().equalsIgnoreCase("ACCEPTED")) {
                    queue.add(customer);
                    break;
                }
                else if (type.equalsIgnoreCase("Chef") && customer.getOrderHistory().get(i).getStatus().equals("READY TO COOK") || customer.getOrderHistory().get(i).getStatus().equals("COOKING")) {
                    queue.add(customer);
                    break;
                }
            } 
        }
    }
    
    public void updateQueue(String orderNumber, String newStatus, SelectionBar bar) {
        for(Customer customer : queue) {
            for(Order order : customer.getOrderHistory()) {
                if (order.getOrderNumber().equals(orderNumber)) {    
                    order.setStatus(newStatus);
                }
            }
            FileManager.saveCustomer(customer);
        }
        if (newStatus.equals("READY TO COOK") || newStatus.equals("READY FOR PICKUP")) {
            ((Pane)queueSP.getContent()).getChildren().remove(bar);
        }
    }
    
    private Pane createQueuePane() {
        Pane toppingsBasePane = new Pane();
        toppingsBasePane.setPrefWidth(400);
        int toppingsSelectionBarBaseY = 5;
        for(Customer customer : queue) {
            for(Order order : customer.getOrderHistory()) {
                SelectionBar bar;
                if (type.equalsIgnoreCase("Order Processing Agent") && order.getStatus().equalsIgnoreCase("ACCEPTED")) {
                    bar = new SelectionBar("status", order.getOrderNumber(), 1, opaButtonText, 400, 140);
                    bar.relocate(5, toppingsSelectionBarBaseY);
                    toppingsSelectionBarBaseY += 40;
                    toppingsBasePane.getChildren().add(bar);
                }
                else if (type.equalsIgnoreCase("Chef") && order.getStatus().equalsIgnoreCase("READY TO COOK")) {
                    bar = new SelectionBar("status", order.getOrderNumber(), 2, chefButtonText, 120, 140);
                    bar.relocate(5, toppingsSelectionBarBaseY);
                    toppingsSelectionBarBaseY += 40;
                    toppingsBasePane.getChildren().add(bar);
                }
            }
        }
        return toppingsBasePane;
    }
    
    private class AdminPortalControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == homeButton) {
                SunDevilPizza.session.setUser(null);
                SunDevilPizza.home();
            }
        }
    }
}
