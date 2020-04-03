package ediary.controllers;

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

public class LoginController {
    public Button loginBT;
    public Button closeBT;
    public TextField loginLabel;
    public PasswordField passwordLabel;
    public Label infoLabel;


    @FXML
    public void handleLoginButtonAction() throws IOException {
        switch (authenticateUser()) {
            case 0:
                loadDiary("Okno dyrektora");

                break;
            case 1:
                loadDiary("Okno nauczyciela");

                break;
            case 2:
                loadDiary("Okno rodzica");

                break;
            case 3:
                loadDiary("Okno ucznia");

                break;
            default:
                infoLabel.setText("Nieprawidłowe dane logowania!");
                break;
        }
    }

    @FXML
    public void handleCloseButtonAction(){
        Stage stage = (Stage) closeBT.getScene().getWindow();
        stage.close();
    }

    private int authenticateUser() {
        if(loginLabel.getText().equals("dyrektor") && passwordLabel.getText().equals("dyrektor")){
            return 0;

        }else if(loginLabel.getText().equals("nauczyciel") && passwordLabel.getText().equals("nauczyciel")){
            return 1;

        }else if(loginLabel.getText().equals("rodzic") && passwordLabel.getText().equals("rodzic")){
            return 2;

        }else if(loginLabel.getText().equals("uczen") && passwordLabel.getText().equals("uczen")){
            return 3;

        }else {
            return -1;
        }
    }

    private void loadDiary(String title) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("../fxmls/temp.fxml"));
        Stage primaryStage = (Stage) loginBT.getScene().getWindow();
        primaryStage.setTitle(title);

        primaryStage.setScene(new Scene(pane, 1024, 768));
    }
}
