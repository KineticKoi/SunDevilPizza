import java.io.FileInputStream;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WelcomeUI extends Pane
{
    // Declaring Variables
    private ImageView mainLogoBanner;
    private Button startButton;
    private Button loginButton;
    private int clickCount = 0;
    private Label counter;
    
    // Constructor
    WelcomeUI(int width, int height)
    {
        // Sets this pane width
        setWidth(width); 
        // Sets this pane height
        setHeight(height);
        setStyle("-fx-background-color: #FFFFFF");
        counter = new Label("");
        counter.setFont(new Font("Arial", 24));
        counter.relocate(180, 920);
        try
        {
            // creates an image object to be used
            Image logoImage = new Image(new FileInputStream("./resources/sdpLogo.png"));
            // creates a image view object to interact with the pane spaces
            mainLogoBanner = new ImageView();
            // sets the banner to be the sdp logo
            mainLogoBanner.setImage(logoImage);
            // fits the width
            mainLogoBanner.setFitWidth(logoImage.getWidth());
            // defines the horozontal properties
            mainLogoBanner.layoutXProperty().bind(this.widthProperty().subtract(mainLogoBanner.getFitWidth()).divide(2));
            mainLogoBanner.layoutYProperty().set(140);
            // Balasooriya mode easter egg
            mainLogoBanner.setOnMouseClicked(event ->
            {
                if (clickCount < 420)
                {
                    clickCount++;
                }
                if (clickCount >= 69)
                {
                    try
                    {
                        // pulls the pizza cutter image from the resource folder and sets the cursor as
                        // the pizza cutter
                        Image cursor = new Image(new FileInputStream(SunDevilPizza.resourcesPath + "bala.png"));
                        // initalizes new cursor
                        this.getScene().setCursor(new ImageCursor(cursor));
                    }
                    catch (Exception e)
                    {
                        // if an exception is caught print in the console log
                        System.out.println(e);
                    }
                }
                if (clickCount == 420)
                {
                    try
                    {
                        // pulls the pizza cutter image from the resource folder and sets the cursor as
                        // the pizza cutter
                        Image cursor = new Image(new FileInputStream(SunDevilPizza.resourcesPath + "cutter.png"));
                        // initalizes new cursor
                        this.getScene().setCursor(new ImageCursor(cursor));
                    }
                    catch (Exception e)
                    {
                        // if an exception is caught print in the console log
                        System.out.println(e);
                    }
                    try
                    {
                        // sets the icon on the stage  
                        Image icon = new Image(new FileInputStream(SunDevilPizza.resourcesPath + "bala.png"));
                        ((Stage)this.getScene().getWindow()).getIcons().add(icon);
                    }
                    catch (Exception e)
                    {
                        // if an exception is caught print in the console log
                        System.out.println(e);
                    }
                }
                counter.setText(String.valueOf(clickCount));
            });
        }
        catch(Exception e)
        {

        }
        // Creates a login button
        loginButton = new ButtonMaker("login");
        // when login button is clicked, trigger new login UI
        loginButton.setOnAction(new WelcomePageControlsHandler());
        // Creates an order button
        startButton = new ButtonMaker("order");
        // defines horizontal property dependent on the width of the buttons
        startButton.layoutXProperty().bind(this.widthProperty().subtract(startButton.widthProperty()).divide(2));
        // defines static height of the button
        startButton.setLayoutY(560);
        // when start button is clicked trigger new pizza builder UI
        startButton.setOnAction(new WelcomePageControlsHandler());
        // initializing the pane
        getChildren().addAll(mainLogoBanner, loginButton, startButton, counter);
    }
    
    // control handler for the welcome page
    private class WelcomePageControlsHandler implements EventHandler<ActionEvent> 
    {
        @Override
        public void handle(ActionEvent event) 
        {
            // when button is clicked play a sound
            Sounds.playButtonClick();
            // if login button is clicked
            if (event.getSource() == loginButton) 
            {
                // new LoginUI
                SunDevilPizza.newRoot(new LoginUI("ASURITE", SunDevilPizza.width, SunDevilPizza.height));
            }
            // if login button is clicked
            else if (event.getSource() == startButton) 
            {
                // new pizza builder UI
                SunDevilPizza.newRoot(new PizzaBuilderUI(SunDevilPizza.width, SunDevilPizza.height)); 
                // if there is no user
                if (SunDevilPizza.session.getUser() == null)
                { 
                    // create temp user
                    SunDevilPizza.session.setUser(new Customer("-1")); 
                    // setting type of the session
                    SunDevilPizza.session.getUser().setType("customer"); 
                }
            }
        }
    }
}