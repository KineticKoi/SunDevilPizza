import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SelectionBar extends Pane
{
    // Declaring Variables
    private Label optionLabel;
    private List<Button> buttonList;
    private String selectionType;
    private Button currentSelection;
    private final String defaultButtonStyle = "-fx-text-fill: black; -fx-background-color: lightgrey;";
    
    // Constructor
    SelectionBar(int barWidth, String selectionType, String labelText, int numOfButtons,
        String[] buttonText, int offSet, int buttonWidth)
    {
        // Sets this pane width
        setWidth(600);
        // Sets this pane height
        setHeight(100);
        // populating variables...
        this.selectionType = selectionType;
        // creating a new option label
        optionLabel = new Label(labelText);
        // relocating nodes
        optionLabel.relocate(5, 5);
        // creating a new arraylist of buttonss
        buttonList = new ArrayList<>(numOfButtons);
        getChildren().add(optionLabel);
        // creating buttons to populate the arraylist
        for (int i = 0; i < numOfButtons; i++)
        {
            // creating a new button
            buttonList.add(new Button());
            // setting the text of the button depending on which button in the arraylist is being created
            buttonList.get(i).setText(buttonText[i]);
            // setting the button height to 20
            buttonList.get(i).setPrefSize(buttonWidth, 20);
            // setting offests so theres no collisons
            buttonList.get(i).relocate(offSet + ((i + 1) * (buttonWidth + 20)), 5);
            // setting style in accordance to the css file
            buttonList.get(i).setStyle(defaultButtonStyle);
            // creating control handlers
            buttonList.get(i).setOnAction(new ControlsHandler());
            // adding the button
            getChildren().add(buttonList.get(i));
        }
    }
    
    // Handler for all UI controls
    private class ControlsHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            // Plays button click sound
            Sounds.playButtonClick();
            // creates color response to being clicked
            if (currentSelection == (Button)event.getSource())
            {
                currentSelection.setStyle(defaultButtonStyle);
                // if it is a topping being removed from selection
                if (selectionType.equals("topping"))
                {
                    // removing the topping from the order
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().
                        getPizza().removeTopping(optionLabel.getText());
                    // reloading order summary
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();
                }
                currentSelection = null;
            }
            else
            {
                // reading the button inputs
                currentSelection = (Button)event.getSource();
                for (int i = 0; i < buttonList.size(); i++)
                {
                    // setting button styles to default one by one
                    buttonList.get(i).setStyle(defaultButtonStyle);
                }
                currentSelection.setStyle("-fx-text-fill: black; -fx-background-color: #90ee90");
                // selecting a topping
                if (selectionType.equalsIgnoreCase("topping"))
                {
                    // removing a topping if too many are selected
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().
                        getPizza().removeTopping(optionLabel.getText());
                    // adding the new topping
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza().
                        addTopping(optionLabel.getText() + " (" + ((Button)event.getSource()).getText() + ")");
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();
                }
                // slecting when to pick up the order if time is clicked
                if (selectionType.equalsIgnoreCase("time"))
                {
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().
                        setPickupTime(((Button)event.getSource()).getText());
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();
                }
                // displaying status of the order if status is clicked
                if (selectionType.equalsIgnoreCase("status"))
                {
                    ((EmployeePortalUI)currentSelection.getScene().getRoot()).
                        updateQueue(optionLabel.getText(), ((Button)event.getSource()).getText(),
                        (SelectionBar)((Button)event.getSource()).getParent());
                }
            }
        }
    }
}