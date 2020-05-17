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
import java.util.Objects;

public class LoginController {

    public static Konta authenticatedUser;

    public Button loginBT;
    public Button closeBT;
    public TextField loginLabel;
    public PasswordField passwordLabel;
    public Label infoLabel;

    @FXML
    public void handleLoginButtonAction() throws IOException {

        switch (authenticateUser()) {
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

    @FXML
    public void handleCloseButtonAction() {
        Stage stage = (Stage) closeBT.getScene().getWindow();
        stage.close();
    }

    private int authenticateUser() {

        try (Session session = SessionController.getSession()) {

            String login = loginLabel.getText().toLowerCase();
            String pass = passwordLabel.getText();

            if (login.isEmpty() || pass.isEmpty()) return -1;

            authenticatedUser = session.createQuery("FROM Konta k WHERE k.login='" + login + "' and k.haslo='" + pass + "'", Konta.class).list().get(0);

            return authenticatedUser.getRoleByRolaId().getRolaId();

        }catch (IndexOutOfBoundsException e){
            return -2;
        }

    }

    private void loadDiary() throws IOException {

        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/ediary.fxml")));
        Stage primaryStage = (Stage) loginBT.getScene().getWindow();
        primaryStage.setTitle("e-Dziennik");
        primaryStage.setResizable(true);

        primaryStage.setScene(new Scene(pane, 1024, 768));

    }
}
