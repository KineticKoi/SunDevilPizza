
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
            buttonList.get(i).setPrefSize(40, 20);
            buttonList.get(i).relocate(360 + ((i + 1) * 50), 5);
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
            if (selectionType.equals("topping")) {
                if (currentSelection == (Button)event.getSource()) {
                    currentSelection.setStyle(defaultButtonStyle);
                    currentSelection = null;
                }
                else {
                    currentSelection = (Button)event.getSource();
                    for (int i = 0; i < buttonList.size(); i++) {
                        buttonList.get(i).setStyle(defaultButtonStyle);
                    }
                    currentSelection.setStyle("-fx-text-fill: black; -fx-background-color: #90ee90");
                }
            }
        }
    }
}
