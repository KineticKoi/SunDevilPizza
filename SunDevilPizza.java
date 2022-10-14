import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SunDevilPizza extends Application { //Launches the main application
    static final int width = 1920, height = 1080; //Initializes the width and height for the app window
    private static List<Parent> previousRootNodes = new ArrayList<>();
    private static Scene currentScene;
    
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new WelcomePage(width, height), width, height); //Generates a new GUI scene
        stage.setTitle("SunDevil Pizza"); //Sets the window title
        currentScene = scene;
        stage.setScene(scene); //Sets the window scene
        try { //Custom cursor, icons, and CSS
            Image icon = new Image("file:sdpLogoIcon.png");
            Image cursor = new Image("file:cutter.png");
            stage.getIcons().add(icon);
            scene.setCursor(new ImageCursor(cursor));
            String styleSheet = getClass().getResource("sdp.css").toExternalForm();
            scene.getStylesheets().add(styleSheet);
        }
        catch (Exception e) {    
        }
        stage.setFullScreen(true); //Sets app to fullscreen
        stage.show(); //Shows the window
    }
    
    //Main
    public static void main(String[] args) {
        launch(args);
    }
    
    //Sets new Root node
    public static void newRoot(Parent node) {
        previousRootNodes.add(currentScene.getRoot());
        currentScene.setRoot(node);
    }
    
    //Sets Root to previous node
    public static void previousRoot() {
        previousRootNodes.remove(currentScene.getRoot());
        currentScene.setRoot(previousRootNodes.get(previousRootNodes.size() - 1));
    }
}    
