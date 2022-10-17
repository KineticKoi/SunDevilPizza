
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class AdminPortalUI extends Pane{
    //Declaring Variables...
    private Label headerLabel;
    private Button homeButton;
    
    //Constructor
    AdminPortalUI(int width, int height) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF");
        headerLabel = new Label("ADMIN PORTAL (UNDER CONSTRUCTION):");
        headerLabel.setFont(new Font("Arial", 40));
        headerLabel.layoutXProperty().set(40);
        headerLabel.layoutYProperty().set(40);
        homeButton = new ButtonMaker("home");
        homeButton.setOnAction(new AdminPortalControlsHandler());
        getChildren().addAll(headerLabel, homeButton);
    }
    
    private class AdminPortalControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == homeButton) {
                SunDevilPizza.clearRoots();
            }
        }
    }
}
