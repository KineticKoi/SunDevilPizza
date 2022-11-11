import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

// Class builds Large selection bars for use throughout program
public class LargeSelectionBar extends Pane
{
    // Declaring Variables
    private Label optionLabel;
    private List<Button> buttonList;
    private String selectionType;
    private Button currentSelection;
    private final String defaultButtonStyle = "-fx-text-fill: black; -fx-background-color: lightgrey;";
    
    // Constructor
    LargeSelectionBar(String selectionType, String labelText, int numOfButtons, String[] buttonText)
    {
        // Sets this pane width
        setWidth(600);
        // Sets this pane height
        setHeight(400);
        this.selectionType = selectionType;
        // Sets label text from labelText string in method parameters
        optionLabel = new Label(labelText);
        // Sets label text size and font
        optionLabel.setFont(new Font("Arial", 28));
        // Relocates label
        optionLabel.relocate(5, 5);
        // Creates a new list ArrayList of buttons
        buttonList = new ArrayList<>(numOfButtons);
        // Adds created label to pane
        getChildren().add(optionLabel);
        // Looping for number of buttons that selection bar will have
        for (int i = 0; i < numOfButtons; i++)
        {
            // Adds new button to the list
            buttonList.add(new Button());
            // Sets text of current indexed button
            buttonList.get(i).setText(buttonText[i]);
            // Set's current indexed button's preffered size to (220, 80)
            buttonList.get(i).setPrefSize(220, 80);
            // Relocate button
            buttonList.get(i).relocate((i * 260), 60);
            // Sets style of new button to have black text and lightgrey background
            buttonList.get(i).setStyle(defaultButtonStyle);
            // Set's button text font and size
            buttonList.get(i).setFont(new Font("Arial", 28));
            // Sets up control handler for button
            buttonList.get(i).setOnAction(new ControlsHandler());
            // Adding current button to list
            getChildren().add(buttonList.get(i));
        }
    }
    
    // Handler for all UI controls...
    private class ControlsHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            // Calls sound class to play audio when a button is clicked
            Sounds.playButtonClick();
            // Checks if button has already been toggled
            if (currentSelection == (Button)event.getSource())
            {
                // Sets button color to the default light grey
                currentSelection.setStyle(defaultButtonStyle);
                // Checking if button labeled "size" was clicked
                if (selectionType.equals("size"))
                {
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza().setSize(null);
                    // Updates pizza being currently built to show changes
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();
                }
                // Checking if button labeled "type" was clicked
                else if (selectionType.equals("type"))
                {
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza().setType(null);
                    // Updates pizza being currently built to show changes
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();
                }
                // Setting button clikced to null to work as a toggle
                currentSelection = null;
            }
            else
            {
                // Checks if button has not been toggled
                currentSelection = (Button)event.getSource();
                // Checking if button labeled "size" was clicked
                if (selectionType.equals("size"))
                {
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza().setSize(((Button)event.getSource()).getText());
                    // Updates pizza being currently built to show changes
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();
                }
                // Checking if button labeled "type" was clicked
                else if (selectionType.equals("type"))
                {
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza().setType(((Button)event.getSource()).getText());
                    // Updates pizza being currently built to show changes
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();
                }
                // Looping for size of button list
                for (int i = 0; i < buttonList.size(); i++)
                {
                    // Setting style of each button in button list to have black text and a lightgrey background
                    buttonList.get(i).setStyle(defaultButtonStyle);
                }
                // Toggling button to change background to light green
                currentSelection.setStyle("-fx-text-fill: black; -fx-background-color: #90ee90");
            }
        }
    }   
}