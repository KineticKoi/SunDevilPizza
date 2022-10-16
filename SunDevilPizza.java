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
    private static List<Parent> rootNodes = new ArrayList<>();
    private static int currentRootIndex = -1;
    private static Scene scene;
    public static Session session = new Session();
    private final boolean developerMode = false;
    
    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(new WelcomePage(width, height), width, height); //Generates a new GUI scene
        newRoot(scene.getRoot());
        stage.setTitle("SunDevil Pizza"); //Sets the window title
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
        
        if (developerMode) {
            final Stage devConsole = new Stage();
            try {
                Image icon = new Image("file:favicon.png");
                devConsole.getIcons().add(icon);
            } catch (Exception e) {    
            }
            Scene developerUI = new Scene(new TestingUI(400, 400), 400, 400);
            devConsole.setScene(developerUI);
            devConsole.setTitle("Developer Console");
            devConsole.setX(stage.getX() - 400);
            devConsole.setY(stage.getY());
            devConsole.show();
        }
    }
    
    //Main
    public static void main(String[] args) {
        launch(args);
    }
    
    //Sets new Root node
    public static void newRoot(Parent node) {        
        scene.setRoot(node);
        rootNodes.add(scene.getRoot());
        currentRootIndex++;
    }
    
    //Sets Root to previous node
    public static void previousRoot() {
        scene.setRoot(rootNodes.get(currentRootIndex - 1));
        currentRootIndex--;
    }
    
    public static boolean nextRoot() {
        if (rootNodes.size() > currentRootIndex + 1) {
            scene.setRoot(rootNodes.get(currentRootIndex + 1));
            currentRootIndex++;
            return true;
        }
        return false;
    }
    
    public static void clearRoots() {
        for (int i = 1; i < rootNodes.size(); i++) {
            rootNodes.remove(i);
        }
        currentRootIndex = 0;
        scene.setRoot(rootNodes.get(0));
    }
}    
