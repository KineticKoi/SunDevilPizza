
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

//Class builds customer portal UI pane
public class CustomerPortalUI extends Pane{
    //Declaring Variables...
    private Label headerLabel;
    private Button homeButton;
    
    //Constructor
    CustomerPortalUI(int width, int height) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF"); //Sets background to be white
        headerLabel = new Label("CUSTOMER PORTAL (UNDER CONSTRUCTION):"); //Sets pane label text
        headerLabel.setFont(new Font("Arial", 40)); //Sets label text font and size
        headerLabel.layoutXProperty().set(40); //Sets pane's X value
        headerLabel.layoutYProperty().set(40); //Sets pane's Y value
        homeButton = new ButtonMaker("home"); //Creates new button with "home" text 
        homeButton.setOnAction(new CustomerPortalControlsHandler()); //Sets up pane's button handler
        getChildren().addAll(headerLabel, homeButton); //Adds label and home button to pane
    }
    
    //Event handler for pane's home button and audio for clicking
    private class CustomerPortalControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick(); //Calls sound class to play audio when pane is clicked
            if (event.getSource() == homeButton) { //Checking if home button is clicked
                SunDevilPizza.home(); //Takes user back to main landing page when home button is clicked
            }
        }
    }
    
} //End of CustomerPortalUI class

