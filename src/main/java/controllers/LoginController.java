/**
 * Package controllers przechowuje klasy, odpowiadające za sterowanie naszą aplikacją.
 */
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

/**
 * Klasa LoginController odpowiada za uwierzytelnianie oraz logowanie użytkowników.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-04-03
 */

public class LoginController {
    /**
     * Zmienna do której jest przypisany zalogowany użytkownik.
     */
    public static Konta authenticatedUser;
    /**
     * Zmienna do której jest przypisany przycisk logowania.
     */
    public Button loginBT;
    /**
     * Zmienna do której jest przypisany przycisk wyjścia.
     */
    public Button closeBT;
    /**
     * Zmienna do której jest przypisane pole do wprowadzenia loginu.
     */
    public TextField loginLabel;
    /**
     * Zmienna do której jest przypisane pole do wprowadzenia hasła.
     */
    public PasswordField passwordLabel;
    /**
     * Zmienna do której przypisane jest pole służące do wyświetlania informacji na temat logowania.
     */
    public Label infoLabel;

    /**
     * Metoda handleLoginButtonAction sprawdza czy użytkownik podał prawdziwe dane oraz wypełnił wszystkie pola.
     * @throws IOException Występuje w przypadku kiedy program ma problem ze znalezieniem lub otworzeniem fxmla.
    */
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
    /**
     * Metoda handleCloseButtonAction służy do zamknięcia aktualnego okna poprzez wciśniecie przycisku closeBT.
     */
    @FXML
    public void handleCloseButtonAction() {
        Stage stage = (Stage) closeBT.getScene().getWindow();
        stage.close();
    }
    /**
     * Metoda authenticateUser sprawdza czy podane dane są prawidłowe, kompletne oraz zwraca użytkownikowi jego role.
     * @return -1 Zwraca błąd, odnoszący się do pustego badź pustych pól.
     * @return -2 Zwróci nam błąd, jeżeli index będzie wykraczał poza tablice.
     */
    private int authenticateUser() {

        String login = loginLabel.getText().toLowerCase();
        String pass = passwordLabel.getText();

        if (login.isEmpty() || pass.isEmpty()) return -1;

        try (Session session = SessionController.getSession()) {

            authenticatedUser = session.createQuery("FROM Konta k JOIN FETCH k.roleByRolaId WHERE k.login='" + login + "' and k.haslo='" + pass + "'", Konta.class).list().get(0);

            return authenticatedUser.getRoleByRolaId().getRolaId();

        } catch (IndexOutOfBoundsException e) {
            return -2;
        }

    }

    /**
     * Funkcja start, która odpowiada za zaladowanie okna logowania *
     * @throws IOException Jest to błąd, który aplikacja wychwytuje kiedy ma problem ze znalezieniem i wyświetlniem podanego pliku FXML.
     */

    private void loadDiary() throws IOException {

        Parent pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/ediary.fxml")));
        Stage primaryStage = (Stage) loginBT.getScene().getWindow();
        primaryStage.setTitle("e-Dziennik");
        primaryStage.setResizable(true);

        primaryStage.setScene(new Scene(pane, 1024, 768));

    }
}
