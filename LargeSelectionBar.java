
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

//Class 
public class LargeSelectionBar extends Pane {
    //Declaring Variables...
    private Label optionLabel;
    private List<Button> buttonList;
    private String selectionType;
    private Button currentSelection;
    private final String defaultButtonStyle = "-fx-text-fill: black; -fx-background-color: lightgrey;";
    
    //Constructor
    LargeSelectionBar(String selectionType, String labelText, int numOfButtons, String[] buttonText) {
        setWidth(600); //Sets this pane width
        setHeight(400); //Sets this pane height
        this.selectionType = selectionType; //
        optionLabel = new Label(labelText); //Sets label text from labelText string in method parameters
        optionLabel.setFont(new Font("Arial", 28)); //Sets label text size and font
        optionLabel.relocate(5, 5); //Relocates label 
        buttonList = new ArrayList<>(numOfButtons); //Creates a new list ArrayList of buttons
        getChildren().add(optionLabel); //Adds created label to pane
        for (int i = 0; i < numOfButtons; i++) { //Looping for number of buttons from numOfButtons integer in method parameters 
            buttonList.add(new Button()); //Adding a new button to the button list
            buttonList.get(i).setText(buttonText[i]); //Setting the text of each button from buttonText array in method parameters 
            buttonList.get(i).setPrefSize(220, 80); //Setting preferred size of current button at buttonList index to (220, 80)
            buttonList.get(i).relocate((i * 260), 60); //Relocating current button at buttonList index to be (index * 260), 60)
            buttonList.get(i).setStyle(defaultButtonStyle); //Setting style of new button to have black text and a grey background
            buttonList.get(i).setFont(new Font("Arial", 28)); //Setting button text size and font
            buttonList.get(i).setOnAction(new ControlsHandler()); //Sets control handler for actions for the new button 
            getChildren().add(buttonList.get(i)); //Adding current indexed button to pane 
        }
    }
    
    //Handler for all UI controls...
    private class ControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick(); //Calls sound class to play audio when a button is clicked
            if (currentSelection == (Button)event.getSource()) {
                currentSelection.setStyle(defaultButtonStyle); //Sets button color default to be light grey when not clicked
                if (selectionType.equals("size")) { //Checks if a button for pizza size is clicked
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza().setSize(null);
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();
                }
                else if (selectionType.equals("type")) { //Checks if a button for pizza type is clicked
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza().setType(null);
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();
                }
                currentSelection = null;
            }
            else {
                currentSelection = (Button)event.getSource();
                if (selectionType.equals("size")) { //Checking if a button clicked has text "size"
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza().setSize(((Button)event.getSource()).getText());
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();
                }
                else if (selectionType.equals("type")) { //Checking of a button 
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza().setType(((Button)event.getSource()).getText());
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();
                }
                for (int i = 0; i < buttonList.size(); i++) { //Looping for size of button list
                    buttonList.get(i).setStyle(defaultButtonStyle); //Setting style of each button in button list
                }
                currentSelection.setStyle("-fx-text-fill: black; -fx-background-color: #90ee90"); //Sets color of button text to black and background to light green
            }
        }
    }
    
} //End of LargeSelection class
