import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class LoginUI extends Pane {
    //Declaring Variables...
    private String type;
    private Label headerLabel;
    private TextField userNameField;
    private PasswordField passwordField;
    private Button signInButton;
    private Label loginFailedLabel;
    private Button backButton;
    private Hyperlink employeeSignInLink;
    private int posy = 320;
    
    //Constructor
    LoginUI(String type, int width, int height) {
        this.type = type;
        this.setWidth(width);
        this.setHeight(height);
        userNameField = new TextField();
        userNameField.setPrefSize(320, 40);
        userNameField.layoutXProperty().bind(this.widthProperty().subtract(userNameField.getPrefWidth()).divide(2));
        userNameField.layoutYProperty().set(posy + 60);
        userNameField.setPromptText(type.toUpperCase() + " ID");
        userNameField.setStyle("-fx-background-color: lightgrey;");
        passwordField = new PasswordField();
        passwordField.setPrefSize(320, 40);
        passwordField.layoutXProperty().bind(this.widthProperty().subtract(userNameField.getPrefWidth()).divide(2));
        passwordField.layoutYProperty().set(posy + 120);
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: lightgrey;");
        headerLabel = new Label(type.toUpperCase() + " Login:");
        headerLabel.layoutXProperty().bind(this.widthProperty().subtract(userNameField.getPrefWidth()).divide(2));
        headerLabel.layoutYProperty().set(posy);
        headerLabel.setFont(new Font("Arial", 24));
        signInButton = new Button("Sign In");
        signInButton.setStyle("-fx-text-fill: black; -fx-background-color: lightgrey;");
        signInButton.setPrefSize(120, 40);
        signInButton.layoutXProperty().bind(this.widthProperty().subtract(signInButton.getPrefWidth()).divide(2));
        signInButton.layoutYProperty().set(posy + 180);
        signInButton.setOnAction(new LoginControlsHandler());
        employeeSignInLink = new Hyperlink("Employee Login →");
        employeeSignInLink.setPrefWidth(120);
        employeeSignInLink.layoutXProperty().bind(this.widthProperty().subtract(employeeSignInLink.getPrefWidth()).divide(2));
        employeeSignInLink.layoutYProperty().set(posy + 240);
        employeeSignInLink.setOnAction(new LoginControlsHandler());
        if (type.equalsIgnoreCase("employee")) {
            employeeSignInLink.setVisible(false);
        }
        loginFailedLabel = new Label("Login Denied");
        loginFailedLabel.setStyle("-fx-text-fill: red;");
        loginFailedLabel.layoutXProperty().bind(this.widthProperty().subtract(loginFailedLabel.getPrefWidth()).divide(2));
        loginFailedLabel.layoutYProperty().set(posy + 300);
        loginFailedLabel.setVisible(false);
        backButton = new ButtonMaker("back");
        backButton.setOnAction(new LoginControlsHandler());
        this.getChildren().addAll(headerLabel, userNameField, passwordField, signInButton, employeeSignInLink, loginFailedLabel, backButton);
    }
    
    private class LoginControlsHandler implements EventHandler<javafx.event.ActionEvent> {
        @Override
        public void handle(javafx.event.ActionEvent event) {
            Sounds.playButtonClick();
            if (event.getSource() == signInButton) {
                if (CredentialVerification.loginCheck(type, userNameField.getText(), passwordField.getText()).equals("AdminVerified")) {
                    
                    SunDevilPizza.newRoot(new AdminPortal(SunDevilPizza.width, SunDevilPizza.height));
                }
                else {
                    loginFailedLabel.setVisible(true);
                }
            }
            if (event.getSource() == employeeSignInLink) {
                SunDevilPizza.newRoot(new LoginUI("EMPLOYEE", SunDevilPizza.width, SunDevilPizza.height));
            }
            if (event.getSource() == backButton && type.equalsIgnoreCase("ASURITE")) {
                SunDevilPizza.previousRoot();
            }
            if (event.getSource() == backButton && type.equalsIgnoreCase("EMPLOYEE")) {
                SunDevilPizza.previousRoot();
            }
        }
    }
}