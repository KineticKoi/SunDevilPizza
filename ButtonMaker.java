import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonMaker extends Button {
    //Basic Button Maker
    ButtonMaker(String type) {
       if (type.equalsIgnoreCase("login"))  {
            try {
                setGraphic(new ImageView(new Image(new FileInputStream("login.png"))));
            }
            catch(FileNotFoundException e) {     
            }   
            relocate(1700, 900);
        }
        else if (type.equalsIgnoreCase("order"))  {
            try {
                setGraphic(new ImageView(new Image(new FileInputStream("orderButton.png"))));
            }
            catch(FileNotFoundException e) {     
            }
        }
        else if (type.equalsIgnoreCase("back")) {
            try {
                setGraphic(new ImageView(new Image(new FileInputStream("backarrow.png"))));
            }
            catch(FileNotFoundException e) {     
            }
            relocate(1640, 900);
        }
       else if (type.equalsIgnoreCase("forward")) {
            try {
                setGraphic(new ImageView(new Image(new FileInputStream("forwardarrow.png"))));
            }
            catch(FileNotFoundException e) {     
            }
            relocate(1740, 900);
        }
    }
}
