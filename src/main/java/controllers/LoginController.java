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
import mapping.Konta;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

// Class handling Login Window actions
public class LoginController {
    public static int loggedUserRole;
    public Button loginBT;
    public Button closeBT;
    public TextField loginLabel;
    public PasswordField passwordLabel;
    public Label infoLabel;

    //    Function that handle Login button clicks
    //    If the user is authenticated, opens the window depending on his role
    @FXML
    public void handleLoginButtonAction() throws IOException {
        loggedUserRole = authenticateUser();

        switch (loggedUserRole) {
            case -1:
                infoLabel.setText("Proszę uzupełnić wszystkie pola!");
                break;
            case -2:
                infoLabel.setText("Nieprawidłowe dane logowania!");
                break;
            default:
                loadDiary();
                break;
        }
    }

    //    Function that handle Close button clicks
    //    Closes application
    @FXML
    public void handleCloseButtonAction() {
        Stage stage = (Stage) closeBT.getScene().getWindow();
        stage.close();
    }

    //    Function that checks if login and password are valid
    //    If so returns user role
    private int authenticateUser() {
        Session session = SessionController.getSession();
        String login = loginLabel.getText().toLowerCase();
        String pass = passwordLabel.getText();

        if (login.isEmpty() || pass.isEmpty()) return -1;

        List<Konta> resault = session.createQuery("FROM Konta k WHERE k.login='" + login + "' and k.haslo='" + pass + "'", Konta.class).list();

        if (!resault.isEmpty()) return resault.get(0).getRolaId();

        session.close();

        return -2;
    }

    //    Function that load main diary window with given window title
    private void loadDiary() throws IOException {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/ediary.fxml")));
        Stage primaryStage = (Stage) loginBT.getScene().getWindow();
        primaryStage.setTitle("e-Dziennik");
        primaryStage.setResizable(true);

        primaryStage.setScene(new Scene(pane, 1024, 768));
    }
}
