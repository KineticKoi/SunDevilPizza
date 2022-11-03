
import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SelectionBar extends Pane {
    //Declaring Variables...
    private Label optionLabel;
    private List<Button> buttonList;
    private String selectionType;
    private Button currentSelection;
    private final String defaultButtonStyle = "-fx-text-fill: black; -fx-background-color: lightgrey;";
    
    //Constructor
    SelectionBar(String selectionType, String labelText, int numOfButtons, String[] buttonText) {
        setWidth(600); //Sets this pane width
        setHeight(100); //Sets this pane height
        this.selectionType = selectionType;
        optionLabel = new Label(labelText);
        optionLabel.relocate(5, 5);
        buttonList = new ArrayList<>(numOfButtons);
        getChildren().add(optionLabel);
        for (int i = 0; i < numOfButtons; i++) {
            buttonList.add(new Button());
            buttonList.get(i).setText(buttonText[i]);
            buttonList.get(i).setPrefSize(80, 20);
            buttonList.get(i).relocate(200 + ((i + 1) * 100), 5);
            buttonList.get(i).setStyle(defaultButtonStyle);
            buttonList.get(i).setOnAction(new ControlsHandler());
            getChildren().add(buttonList.get(i));
        }
    }
    
    //Handler for all UI controls...
    private class ControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick(); //Plays button click sound
            if (currentSelection == (Button)event.getSource()) {
                currentSelection.setStyle(defaultButtonStyle);
                if (selectionType.equals("topping")) {
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza().removeTopping(optionLabel.getText());
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();
                }
                currentSelection = null;
            }
            else {
                currentSelection = (Button)event.getSource();
                if (selectionType.equals("topping")) {
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza().removeTopping(optionLabel.getText());
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().getPizza().addTopping(optionLabel.getText() + " (" + ((Button)event.getSource()).getText() + ")");
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();                       
                }
                if (selectionType.equals("time")) {
                    ((Customer)SunDevilPizza.session.getUser()).getCurrentOrder().setPickupTime(((Button)event.getSource()).getText());
                    ((PizzaBuilderUI)currentSelection.getScene().getRoot()).refreshOrderSummary();
                }
                for (int i = 0; i < buttonList.size(); i++) {
                    buttonList.get(i).setStyle(defaultButtonStyle);
                }
                currentSelection.setStyle("-fx-text-fill: black; -fx-background-color: #90ee90");
            }
        }
    }
}
