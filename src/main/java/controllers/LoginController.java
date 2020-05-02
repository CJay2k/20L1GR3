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
import mapping.Autoryzacja;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

// Class handling Login Window actions
public class LoginController {
    public Button loginBT;
    public Button closeBT;
    public TextField loginLabel;
    public PasswordField passwordLabel;
    public Label infoLabel;

    public static long loggedUserRole = -1;

//    Function that handle Login button clicks
//    If the user is authenticated, opens the window depending on his role
    @FXML
    public void handleLoginButtonAction() throws IOException {
        switch (authenticateUser()) {
            case 0:
                loadDiary(0,"Okno dyrektora");

                break;
            case 1:
                loadDiary(1,"Okno nauczyciela");

                break;
            case 2:
                loadDiary(2,"Okno rodzica");

                break;
            case 3:
                loadDiary(3,"Okno ucznia");

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
        Session session = SessionController.getSession();

        String login = loginLabel.getText().toLowerCase();
        String pass = passwordLabel.getText();
        int rola = -1;

//        Check if login and password field are not empty
        if(!login.isEmpty() && !pass.isEmpty()){
//            Select object from database with matching login and password
            List<Autoryzacja> resault = session.createQuery("FROM Autoryzacja a WHERE a.login='" + login + "' and a.haslo='" + pass + "'").list();

//            Check PK and role of logged and assing it to variable
            if(!resault.isEmpty()) {
                rola = resault.get(0).getRola();
            }
        }
        session.close();

        return rola;
    }

//    Function that load main diary window with given window title
    private void loadDiary(int role, String title) throws IOException {
        loggedUserRole = role;

        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/ediary.fxml")));
        Stage primaryStage = (Stage) loginBT.getScene().getWindow();
        primaryStage.setTitle(title);
        primaryStage.setResizable(true);

        primaryStage.setScene(new Scene(pane, 1024, 768));
    }
}
