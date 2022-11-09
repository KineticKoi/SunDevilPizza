import java.io.FileInputStream;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class WelcomeUI extends Pane {
    //Declaring Variables...
    private ImageView mainLogoBanner;
    private Button startButton;
    private Button loginButton;
    
    //Constructor
    WelcomeUI(int width, int height) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF");
        try {
            Image logoImage = new Image(new FileInputStream("./resources/sdpLogo.png"));// creates an image object to be used
            mainLogoBanner = new ImageView();// creates a image view object to interact with the pane spaces
            mainLogoBanner.setImage(logoImage);// sets the banner to be the sdp logo
            mainLogoBanner.setFitWidth(logoImage.getWidth());// fits the width
            mainLogoBanner.layoutXProperty().bind(this.widthProperty().subtract(mainLogoBanner.getFitWidth()).divide(2)); // defines the horozontal properties
            mainLogoBanner.layoutYProperty().set(140);
        }
        catch(Exception e) {     
        }
        loginButton = new ButtonMaker("login"); // Creates a login button
        loginButton.setOnAction(new WelcomePageControlsHandler()); // when login button is clicked, trigger new login UI
        startButton = new ButtonMaker("order");// Creates an order button
        startButton.layoutXProperty().bind(this.widthProperty().subtract(startButton.widthProperty()).divide(2));// defines horizontal property dependent on the width of the buttons
        startButton.setLayoutY(560);// defines static height of the button
        startButton.setOnAction(new WelcomePageControlsHandler()); // when start button is clicked trigger new pizza builder UI
        getChildren().addAll(mainLogoBanner, loginButton, startButton); // initializing the pane
    }
    
    private class WelcomePageControlsHandler implements EventHandler<javafx.event.ActionEvent> { //control handler for the welcome page
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick(); // when button is clicked play a sound
            if (event.getSource() == loginButton) { // if login button is clicked
                SunDevilPizza.newRoot(new LoginUI("ASURITE", SunDevilPizza.width, SunDevilPizza.height)); // new LoginUI
            }
            else if (event.getSource() == startButton) { // if login button is clicked
                SunDevilPizza.newRoot(new PizzaBuilderUI(SunDevilPizza.width, SunDevilPizza.height)); // new pizza builder UI
                if (SunDevilPizza.session.getUser() == null) { // if there is no user
                    SunDevilPizza.session.setUser(new Customer("-1")); // create temp user
                    SunDevilPizza.session.getUser().setType("customer"); // setting type of the session
                }
            }
        }
    }
}
