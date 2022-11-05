
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

//Class builds admin portal pane including setting label text
// and button that takes users back to home page
public class AdminPortalUI extends Pane{
    //Declaring Variables...
    private Label headerLabel;
    private Button homeButton;
    
    //Constructor
    AdminPortalUI(int width, int height) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF"); //Sets pane background to white
        headerLabel = new Label("ADMIN PORTAL (UNDER CONSTRUCTION):"); //Sets pane label text
        headerLabel.setFont(new Font("Arial", 40)); //Sets label text font type and size
        headerLabel.layoutXProperty().set(40); //Sets label X value
        headerLabel.layoutYProperty().set(40); //Sets label Y value
        homeButton = new ButtonMaker("home"); //Sets button text
        homeButton.setOnAction(new AdminPortalControlsHandler()); //Call to event handler
        getChildren().addAll(headerLabel, homeButton); //Adding home button and label to pane
    }
    
    //Event handler for pane's home button and audio for clicking
    private class AdminPortalControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick(); //Calls sound class to play audio when pane is clicked
            if (event.getSource() == homeButton) {
                SunDevilPizza.home(); //Takes user back to main landing page when home button is clicked
            }
        }
    }
    
} //End of AdminPortalUI class
