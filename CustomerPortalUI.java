
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class CustomerPortalUI extends Pane{
    //Declaring Variables...
    private Label currentOrdersLabel;
    private Label orderHistoryLabel;
    private ScrollPane currentOrderSP;
    private ScrollPane orderHistorySP;
    private List<Order> currentOrderList = new ArrayList<>();
    private List<Order> previousOrderList = new ArrayList<>();
    private Button homeButton;
    
    //Constructor
    CustomerPortalUI(int width, int height) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF");
        currentOrdersLabel = new Label("Current Orders:");
        currentOrdersLabel.setFont(new Font("Arial", 40));
        currentOrdersLabel.relocate(100, 80);
        iterateOrders();
        currentOrderSP = new ScrollPane();
        currentOrderSP.relocate(100, 160);
        currentOrderSP.setPrefWidth(800);
        currentOrderSP.setPrefHeight(240);
        currentOrderSP.setContent(createQueuePane(currentOrderList));
        orderHistoryLabel = new Label("Previous Orders:");
        orderHistoryLabel.setFont(new Font("Arial", 40));
        orderHistoryLabel.relocate(100, 520);
        orderHistorySP = new ScrollPane();
        orderHistorySP.relocate(100, 600);
        orderHistorySP.setPrefWidth(800);
        orderHistorySP.setPrefHeight(240);
        orderHistorySP.setContent(createQueuePane(previousOrderList));
        homeButton = new ButtonMaker("home");
        homeButton.setOnAction(new CustomerPortalControlsHandler());
        getChildren().addAll(currentOrdersLabel, currentOrderSP, orderHistoryLabel, orderHistorySP, homeButton);
    }
    
    public void iterateOrders() {
        Customer customer = (Customer)SunDevilPizza.session.getUser();
        for (int i = 0; i < customer.getOrderHistory().size(); i++) {
            if (!customer.getOrderHistory().get(i).getStatus().equalsIgnoreCase("READY")) {
                currentOrderList.add(customer.getOrderHistory().get(i));
            }
            else {
               previousOrderList.add(customer.getOrderHistory().get(i));
            }
        } 
    }
    
    private Pane createQueuePane(List<Order> orderList) {
        Pane orderBasePane = new Pane();
        orderBasePane.setPrefWidth(780);
        int toppingsSelectionBarBaseY = 5;
        SelectionBar bar;
        for (int i = 0; i < orderList.size(); i++) {
            bar = new SelectionBar(780, "", orderList.get(i).getOrderNumber() + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + orderList.get(i).getStatus(), 0, null, 400, 140);
            bar.relocate(5, toppingsSelectionBarBaseY);
            toppingsSelectionBarBaseY += 40;
            orderBasePane.getChildren().add(bar);
        }
        return orderBasePane;
    }
    
    private class CustomerPortalControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == homeButton) {
                SunDevilPizza.home();
            }
        }
    }
}
