import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class TestingUI extends Pane
{
    private List<Button> buttonList;
    
    TestingUI (int width, int height)
    {
        // Sets this pane width
        setWidth(width);
        // Sets this pane height
        setHeight(height);
        // creating an arraylist of buttons
        buttonList = new ArrayList<>();
        // for six buttons
        for (int i = 0; i < 6; i++)
        {
            // create a new button unique button starting at 1
            Button button = new Button(String.valueOf(i + 1));
            // sets size of the new button
            button.setPrefSize(60, 60);
            // adds the new button to the list
            buttonList.add(button);
            // places new button 60 px lower than the last button
            buttonList.get(i).relocate((i * 60), 60);
            // adds the button
            this.getChildren().add(buttonList.get(i));
        }
    }
}