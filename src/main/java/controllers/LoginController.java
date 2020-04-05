package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

// Class handling Login Window actions
public class LoginController {
    public Button loginBT;
    public Button closeBT;
    public TextField loginLabel;
    public PasswordField passwordLabel;
    public Label infoLabel;

    public static int userRole = -1;

//    Function that handle Login button clicks
//    If the user is authenticated, opens the window depending on his role
    @FXML
    public void handleLoginButtonAction() throws IOException {
        switch (authenticateUser()) {
            case 0:
                userRole = 0;
                loadDiary("Okno dyrektora");

                break;
            case 1:
                userRole = 1;
                loadDiary("Okno nauczyciela");

                break;
            case 2:
                userRole = 2;
                loadDiary("Okno rodzica");

                break;
            case 3:
                userRole = 3;
                loadDiary("Okno ucznia");

                break;
            default:
                infoLabel.setText("Nieprawidłowe dane logowania!");
                break;
        }
    }

//    Function that handle Close button clicks
//    Closes application
    @FXML
    public void handleCloseButtonAction(){
        Stage stage = (Stage) closeBT.getScene().getWindow();
        stage.close();
    }

//    Function that checks if login and password matches
//    If so returns user role
    private int authenticateUser() {
        if(loginLabel.getText().toLowerCase().equals("dyrektor") && passwordLabel.getText().equals("dyrektor")){
            return 0;

        }else if(loginLabel.getText().toLowerCase().equals("nauczyciel") && passwordLabel.getText().equals("nauczyciel")){
            return 1;

        }else if(loginLabel.getText().toLowerCase().equals("rodzic") && passwordLabel.getText().equals("rodzic")){
            return 2;

        }else if(loginLabel.getText().toLowerCase().equals("uczen") && passwordLabel.getText().equals("uczen")){
            return 3;

        }else {
            return -1;
        }
    }

//    Function that load main diary window with given window title
    private void loadDiary(String title) throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/ediary.fxml")));
        Stage primaryStage = (Stage) loginBT.getScene().getWindow();
        primaryStage.setTitle(title);

        primaryStage.setScene(new Scene(pane, 1024, 768));
    }
}
