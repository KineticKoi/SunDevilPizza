import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SunDevilPizza extends Application { //Launches the main application
    static final int width = 1920, height = 1080; //Initializes the width and height for the app window
    private static Scene currentScene;
    
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new WelcomePage(width, height), width, height); //Generates a new GUI scene
        stage.setTitle("SunDevil Pizza"); //Sets the window title
        currentScene = scene;
        stage.setScene(scene); //Sets the window scene
        try {
            Image icon = new Image("file:sdpLogoIcon.png");
            Image cursor = new Image("file:cutter.png");
            stage.getIcons().add(icon);
            scene.setCursor(new ImageCursor(cursor));
            String styleSheet = getClass().getResource("sdp.css").toExternalForm();
            scene.getStylesheets().add(styleSheet);
        }
        catch (Exception e) {    
        }
        stage.setFullScreen(true);
        stage.show(); //Shows the window
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void sceneSwitcher(Parent node) {
        currentScene.setRoot(node);
    }
}    
