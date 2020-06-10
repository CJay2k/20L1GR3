import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Klasa MainFX odpowiadająca za uruchomienie GUI naszej aplikacji.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-04-04
 *
 */


public class MainFX extends Application {

    /**
     *  Główna metoda klasy MainFX odpowiadająca za włączenie GUI *
     * @param args Zawiera dostarczone argumenty wiersza polecenia jako tablicę String obiektów.
     */

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Funkcja start, która odpowiada za zaladowanie okna logowania *
     * @param primaryStage Jest to argument funkcji, który otwiera okno aplikacji, w naszym przypadku login.fxml.
     * @throws Exception Jest to błąd, który aplikacja wychwytuje kiedy ma problem ze znalezieniem podanego pliku FXML.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("fxmls/login.fxml"));
        primaryStage.setTitle("Logowanie e-Dziennik");
        primaryStage.setScene(new Scene(root, 400, 300));
//        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
