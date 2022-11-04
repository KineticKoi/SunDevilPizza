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
            Image logoImage = new Image(new FileInputStream("./resources/sdpLogo.png"));
            mainLogoBanner = new ImageView();
            mainLogoBanner.setImage(logoImage);
            mainLogoBanner.setFitWidth(logoImage.getWidth());
            mainLogoBanner.layoutXProperty().bind(this.widthProperty().subtract(mainLogoBanner.getFitWidth()).divide(2));
            mainLogoBanner.layoutYProperty().set(140);
        }
        catch(Exception e) {     
        }
        loginButton = new ButtonMaker("login");
        loginButton.setOnAction(new WelcomePageControlsHandler());
        startButton = new ButtonMaker("order");
        startButton.layoutXProperty().bind(this.widthProperty().subtract(startButton.widthProperty()).divide(2));
        startButton.setLayoutY(560);
        startButton.setOnAction(new WelcomePageControlsHandler());
        getChildren().addAll(mainLogoBanner, loginButton, startButton);
    }
    
    private class WelcomePageControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == loginButton) {
                SunDevilPizza.newRoot(new LoginUI("ASURITE", SunDevilPizza.width, SunDevilPizza.height));
            }
            else if (event.getSource() == startButton) {
                SunDevilPizza.newRoot(new PizzaBuilderUI(SunDevilPizza.width, SunDevilPizza.height));
                if (SunDevilPizza.session.getUser() == null) {
                    SunDevilPizza.session.setUser(new Customer(null));
                    SunDevilPizza.session.getUser().setType("customer");
                }
            }
        }
    }
}
