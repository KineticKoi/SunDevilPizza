import java.io.FileInputStream;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class WelcomePage extends Pane {
    //Declaring Variables...
    private ImageView mainLogoBanner;
    private Button startButton;
    private Button loginButton;
    
    //Constructor
    WelcomePage(int width, int height) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        setStyle("-fx-background-color: #FFFFFF");
        try {
            Image logoImage = new Image(new FileInputStream("sdpLogo.png"));
            mainLogoBanner = new ImageView();
            mainLogoBanner.setImage(logoImage);
            mainLogoBanner.setFitWidth(logoImage.getWidth());
            mainLogoBanner.layoutXProperty().bind(this.widthProperty().subtract(mainLogoBanner.getFitWidth()).divide(2));
            mainLogoBanner.layoutYProperty().set(140);
        }
        catch(Exception e) {     
        }
        loginButton = new ButtonMaker("login");
        loginButton.setOnAction(new ButtonHandler());
        startButton = new ButtonMaker("order");
        startButton.layoutXProperty().bind(this.widthProperty().subtract(startButton.widthProperty()).divide(2));
        startButton.setLayoutY(600);
        startButton.setOnAction(new ButtonHandler());
        getChildren().addAll(mainLogoBanner, loginButton, startButton);
    }
    
    private class ButtonHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == loginButton) {
                SunDevilPizza.sceneSwitcher(new LoginUI("ASURITE", SunDevilPizza.width, SunDevilPizza.height));
            }
            else if (event.getSource() == startButton) {
                SunDevilPizza.sceneSwitcher(new PizzaBuilder(SunDevilPizza.width, SunDevilPizza.height));
            }
            
        }
    }
}
