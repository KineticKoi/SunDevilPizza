import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//Launches the main application
public class SunDevilPizza extends Application {
    // defines resources directory
    public static final String resourcesPath = "./resources/";
    // defines area to save customer files
    public static final String customerFilesPath = "./customerFiles/";
    // Initializes the width and height for the app window
    static final int width = 1920, height = 1080; 
    // creates an arraylist for rootnodes
    private static List<Parent> rootNodes = new ArrayList<>(); 
    private static int currentRootIndex = -1;
    // initialize a new scene
    private static Scene scene; 
    // create a new default sesssion
    public static Session session = new Session(); 
    
    @Override
    public void start(Stage stage) throws Exception {
        // Generates a new GUI scene
        scene = new Scene(new WelcomeUI(width, height), width, height); 
        // sets a new root that is derived from the root of the new generated scene
        newRoot(scene.getRoot()); 
        // Sets the window title
        stage.setTitle("SunDevil Pizza"); 
        // Sets the window scene
        stage.setScene(scene); 
        //try { //Custom cursor, icons, and CSS
        try {
            // pulls the sun devil pizza logo from the resources folder and sets the icon
            // image as it
            Image icon = new Image(new FileInputStream(SunDevilPizza.resourcesPath + "sdpLogoIcon.png")); 
            // pulls the pizza cutter image from the resource folder and sets the cursor as
            // the pizza cutter
            Image cursor = new Image(new FileInputStream(SunDevilPizza.resourcesPath + "cutter.png"));
            // sets the icon on the stage
            stage.getIcons().add(icon); 
            // initalizes new cursor
            scene.setCursor(new ImageCursor(cursor)); 
            // uses the custom css to create a style for the scene
            scene.getStylesheets().add("sdp.css"); 
        }
        catch (Exception e) {
            // if an exception is caught print in the console log
            System.out.println(e); 
        }
        // Sets app to fullscreen
        stage.setFullScreen(true); 
        // Shows the window
        stage.show(); 
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
        // if the root index is 1, go to node 0 which is home
        if (currentRootIndex == 1) { 
            home();
        }
        else {
            scene.setRoot(rootNodes.get(currentRootIndex - 1));
            // tracks what node of the page the user is on by moving one node back and
            // updating the nodelist
            currentRootIndex--; 
        }   
    }
    
    //Sets Root to next node
    public static boolean nextRoot() {
        // checks if the next node can exist
        if (rootNodes.size() > currentRootIndex + 1) { 
            // sets the next root
            scene.setRoot(rootNodes.get(currentRootIndex + 1)); 
            // updates the root index
            currentRootIndex++; 
            // returns true if the root was set to the next node
            return true; 
        }
        // returns false if the root is the last node
        return false; 
    }
    
    //Returns app to welcome screen
    public static void home() {
        // removes all of the nodes in the list iteratively
        for (int i = rootNodes.size() - 1; i > 0; i--) { 
            rootNodes.remove(i);
        }
        // resets the root node index to home
        currentRootIndex = 0; 
        // sets the new empty root to home
        scene.setRoot(rootNodes.get(0)); 
    }
}    
