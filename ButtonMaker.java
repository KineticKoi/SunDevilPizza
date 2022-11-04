
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Class sets the images for each button in program
public class ButtonMaker extends Button {
    //Basic Button Maker
    ButtonMaker(String type) {
       if (type.equalsIgnoreCase("login"))  { //Checking if button labeled "login" is clicked in program. Ignores text upper/lower cases
            try {
                setGraphic(new ImageView(new Image(new FileInputStream(SunDevilPizza.resourcesPath + "login.png")))); //Sets login button icon
            }
            catch(FileNotFoundException e) {     
            }   
            relocate(1700, 900);
        }
        else if (type.equalsIgnoreCase("order"))  { //Checking if button labeled "order" is clicked in program. Ignores text upper/lower cases
            try {
                setGraphic(new ImageView(new Image(new FileInputStream(SunDevilPizza.resourcesPath + "orderButton.png")))); //Sets order button icon
            }
            catch(FileNotFoundException e) {     
            }
        }
        else if (type.equalsIgnoreCase("back")) { //Checking if button labeled "back" is clicked in program. Ignores text upper/lower cases
            try {
                setGraphic(new ImageView(new Image(new FileInputStream(SunDevilPizza.resourcesPath + "backarrow.png")))); //Sets back arrow button icon
            }
            catch(FileNotFoundException e) {
            }
            relocate(1640, 900);
        }
        else if (type.equalsIgnoreCase("forward")) { //Checking if button labeled "forward" is clicked in program. Ignores text upper/lower cases
            try {
                setGraphic(new ImageView(new Image(new FileInputStream(SunDevilPizza.resourcesPath + "forwardarrow.png")))); //Sets forward arrow button icon 
            }
            catch(FileNotFoundException e) {     
            }
            relocate(1740, 900);
        }
        else if (type.equalsIgnoreCase("home")) { //Checking if button labeled "home" is clicked in program. Ignores text upper/lower cases
            try {
                setGraphic(new ImageView(new Image(new FileInputStream(SunDevilPizza.resourcesPath + "homeIcon.png")))); //Sets home button icon
            }
            catch(FileNotFoundException e) {     
            }
            relocate(1740, 900);
        }
        
    } //End of ButtonMaker
} //End of class
