
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class AdminPortal extends Pane{
    //Declaring Variables...
    private Label headerLabel;
    private Button backButton;
    
    //Constructor
    AdminPortal(int width, int height) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF");
        backButton = new ButtonMaker("back");
        backButton.setOnAction(new AdminPortalControlsHandler());
        getChildren().addAll(backButton);
    }
    
    private class AdminPortalControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == backButton) {
                SunDevilPizza.previousRoot();
            }
        }
    }
}
