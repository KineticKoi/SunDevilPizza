
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class TestingUI extends Pane {
    private List<Button> buttonList;
    
    TestingUI (int width, int height) {
        setWidth(width); //Sets this pane width
        setHeight(height); //Sets this pane height
        buttonList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Button button = new Button(String.valueOf(i + 1));
            button.setPrefSize(60, 60);
            buttonList.add(button);
            buttonList.get(i).relocate((i * 60), 60);
            this.getChildren().add(buttonList.get(i));
        }
    }
}
