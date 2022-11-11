import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


// Class sets the images for each button in program
public class ButtonMaker extends Button
{
    // Basic Button Maker
    ButtonMaker(String type)
    {
        // Checking if new button be created is labeled "login"
       if (type.equalsIgnoreCase("login"))
       {
            try
            {
                // Sets login button icon
                setGraphic(new ImageView(new Image(new FileInputStream(SunDevilPizza.resourcesPath + "login.png"))));
            }
            // Exception catch for when login button icon can't be found
            catch(FileNotFoundException e)
            {

            }   
            // Relocates login.png icon
            relocate(1700, 900);
        }
        // Checking if new button be created is labeled "order"
        else if (type.equalsIgnoreCase("order"))
        {
            try
            {
                setGraphic(new ImageView(new Image(new FileInputStream(SunDevilPizza.resourcesPath + "orderButton.png"))));
            }
            // Exception catch for when order Button icon can't be found
            catch(FileNotFoundException e)
            {

            }
        }
        // Checking if new button be created is labeled "back"
        else if (type.equalsIgnoreCase("back"))
        {
            try
            {
                setGraphic(new ImageView(new Image(new FileInputStream(SunDevilPizza.resourcesPath + "backarrow.png"))));
            }
            // Exception catch for when back arrow icon can't be found
            catch(FileNotFoundException e)
            {

            }
            // Relocates backarrow.png icon
            relocate(1640, 900);
        }
        // Checking if new button be created is labeled "forward"
        else if (type.equalsIgnoreCase("forward"))
        {
            try
            {
                setGraphic(new ImageView(new Image(new FileInputStream(SunDevilPizza.resourcesPath + "forwardarrow.png"))));
            }
            // Exception catch for when forward arrow icon can't be found
            catch(FileNotFoundException e)
            {

            }
            // Relocates forwardarrow.png icon
            relocate(1740, 900);
        }
        // Checking if new button be created is labeled "home"
        else if (type.equalsIgnoreCase("home"))
        {
            try
            {
                setGraphic(new ImageView(new Image(new FileInputStream(SunDevilPizza.resourcesPath + "homeIcon.png"))));
            }
            // Exception catch for when home icon can't be found
            catch(FileNotFoundException e)
            {

            }
            // Relocates homeIcon.png icon
            relocate(1740, 900);
        }
    }
}
