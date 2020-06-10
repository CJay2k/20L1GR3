
 /**
 * Package controllers przechowuje klasy, odpowiadające za sterowanie naszą aplikacją.
 */

package controllers;

import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import mapping.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.PdfGenerator;

import java.io.IOException;
import java.sql.Date;

import static controllers.LoginController.authenticatedUser;

// Class handling Ediary Window actions
/**
 * Klasa EdiaryController służy do zarządzania naszym e-dziennika.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-04-05
 */

public class EdiaryController {
    /**
     * Jest to zmienna przechowująca pojemnik TabPane, w którym znajdują się elementy naszego e-dziennika np. zakładki.
     */

    public TabPane tabPane;

    //================================================================================
    // Uczeń
    //================================================================================

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki Oceny.
     */

    public Tab tabOceny;

    /**
     * Jest to zmienna przechowująca widok tabeli TableView, w której znajdują się obiekty Oceny.
     */

    public TableView<Oceny> ocenyTableEdiary;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o dacie dodania oceny.
     */

    public TableColumn<Oceny, Date> ocenyColumnData;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o przedmiocie z jakiej dostaliśmy daną ocenę.
     */

    public TableColumn<Oceny, String> ocenyColumnPrzedmiot;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o stopniu oceny jakiej dostał dany uczeń.
     */

    public TableColumn<Oceny, String> ocenyColumnOcena;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o tym za co dostaliśmy daną ocenę.
     */

    public TableColumn<Oceny, String> ocenyColumnZaCo;

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki Nieobecnosci.
     */

    public Tab tabNieobecnosci;

    /**
     * Jest to zmienna przechowująca widok tabeli TableView, w której znajdują się obiekty Nieobecnosci.
     */

    public TableView<Nieobecnosci> nieobecnosciTableView;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o tym kiedy byliśmy nieobecni.
     */

    public TableColumn<Nieobecnosci, Date> nieobecnosciColumnData;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o tym na jakim przedmiocie mamy nieobecność.
     */

    public TableColumn<Nieobecnosci, String> nieobecnosciColumnPrzedmiot;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o tym jaki jest aktualny status danej nieobecności.
     */

    public TableColumn<Nieobecnosci, String> nieobecnosciColumnStatus;

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki Uwagi.
     */

    public Tab tabUwagi;

    /**
     * Jest to zmienna przechowująca widok tabeli TableView, w której znajdują się obiekty Uwagi.
     */

    public TableView<Uwagi> uwagiTableView;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o treści danej uwagi.
     */

    public TableColumn<Uwagi, String> uwagiColumnTresc;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o przedmiocie na jakim uczeń dostał uwagę.
     */

    public TableColumn<Uwagi, String> uwagiColumnPrzedmiot;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o tym kiedy uczeń dostał daną uwagę.
     */

    public TableColumn<Uwagi, Date> uwagiColumnData;

    //================================================================================
    // Rodzic
    //================================================================================

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki Usprawiedliwienia.
     */

    public Tab tabUsprawiedliwienia;

    /**
     * Jest to zmienna odnosząca się do pola, zawierającego treść usprawiedliwienia.
     */

    public TextField usprawiedliwieniaTextFieldTresc;

    /**
     * Jest to zmienna przechowująca widok tabeli TableView, w której znajdują się obiekty Nieobecności.
     */

    public TableView<Nieobecnosci> usprawiedliwieniaTableView;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o dacie usprawiedliwienia.
     */

    public TableColumn<Nieobecnosci, Date> usprawiedliwieniaColumnData;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o przedmiocie, z którego jest dane usprawiedliwienie.
     */

    public TableColumn<Nieobecnosci, String> usprawiedliwieniaColumnPrzedmiot;

    /**
     * Jest to zmienna odnosząca się do listy, gdzie rodzic może wybrać podgląd danego dziecka.
     */

    public GridPane tabOcenyGridPaneDziecko;

    /**
     * Jest to zmienna odnosząca się do listy, gdzie rodzic może wybrać dane dziecka, którego chce zobaczyć nieobecności.
     */

    public GridPane tabNieobecnosciGridPaneDziecko;

    /**
     * Jest to zmienna odnosząca się do listy, gdzie rodzic może wybrać dane dziecka, którego chce zobaczyć uwagi.
     */

    public GridPane tabUwagiGridPaneDziecko;

    /**
     * Jest to zmienna odnosząca się do listy, gdzie rodzic może wybrać dane dziecka, którego chce zobaczyć usprawiedliwienia.
     */

    public GridPane tabUsprawiedliwieniaGridPaneDziecko;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie rodzic może wybrać dane dziecko w celu zobaczenia ocen.
     */

    public ChoiceBox<Uczniowie> ocenyChoiceBoxDziecko;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie rodzic może wybrać dane dziecko w celu zobaczenia nieobecności.
     */

    public ChoiceBox<Uczniowie> nieobecnosciChoiceBoxDziecko;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie rodzic może wybrać dane dziecko w celu zobaczenia uwag.
     */

    public ChoiceBox<Uczniowie> uwagiChoiceBoxDziecko;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie rodzic może wybrać dane dziecko w celu zobaczenia usprawiedliwień.
     */

    public ChoiceBox<Uczniowie> usprawiedliwieniaChoiceBoxDziecko;

    //================================================================================
    // Nauczyciel
    //================================================================================

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki DodawanieUwag.
     */

    public Tab tabDodawanieUwag;

    /**
     * Jest to zmienna typu DatePicker, gdzie nauczyciel wprowadza datę dodanie uwagi.
     */

    public DatePicker dodawanieUwagDatePickerData;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie nauczyciel wpisuje treść uwagi.
     */

    public TextField dodawanieUwagTextFieldTresc;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie nauczyciel może wybrać daną klase.
     */

    public ChoiceBox<Klasy> dodawanieUwagChoiceBoxKlasa;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie nauczyciel może wybrać dany przedmiot.
     */

    public ChoiceBox<Przedmioty> dodawanieUwagChoiceBoxPrzedmiot;

    /**
     * Jest to zmienna przechowująca widok tabeli TableView, w której znajdują się obiekty Uczniowie.
     */

    public TableView<Uczniowie> dodawanieUwagTableView;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o nazwisku danego ucznia, któremu nauczyciel chce dodać uwagę.
     */

    public TableColumn<Uczniowie, String> dodawanieUwagColumnNazwisko;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o imieniu danego ucznia, któremu nauczyciel chce dodać uwagę.
     */

    public TableColumn<Uczniowie, String> dodawanieUwagColumnImie;

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki DodajOcenę.
     */

    public Tab tabDodajOcene;

    /**
     * Jest to zmienna typu Button odnosząca się do przycisku, który nauczyciel dodaje ocenę.
     */

    public Button dodawanieOcenButtonDodaj;

    /**
     * Jest to zmienna typu DatePicker, gdzie wybieramy datę dodania oceny.
     */

    public DatePicker dodawanieOcenInputData;

    /**
     * Jest to zmienna odnosząca się do pola, w które wprowadzamy stopień oceny.
     */

    public TextField dodawanieOcenInputStopien;

    /**
     * Jest to zmienna odnosząca się do pola, w które wprowadzamy opis za co jest dana ocena.
     */

    public TextField dodawanieOcenInputZaco;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie nauczyciel może wybrać daną klasę, którą uczy.
     */

    public ChoiceBox<Klasy> dodawanieOcenChoiceBoxKlasa;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie nauczyciel może wybrać dany przedmiot, którego uczy.
     */

    public ChoiceBox<Przedmioty> dodawanieOcenChoiceBoxPrzedmiot;

    /**
     * Jest to zmienna przechowująca widok tabeli TableView, w której znajdują się obiekty Uczniowie.
     */

    public TableView<Uczniowie> dodawanieOcenTableView;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o nazwisku danego ucznia, któremu nauczyciel chce dodać ocenę.
     */

    public TableColumn<Uczniowie, String> dodawanieOcenColumnNazwisko;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o imieniu danego ucznia, któremu nauczyciel chce dodać ocenę.
     */

    public TableColumn<Uczniowie, String> dodawanieOcenColumnImie;

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki WpisywanieNieobecnosci.
     */

    public Tab tabWpisywanieNieobecnosci;

    /**
     * Jest to zmienna typu DatePicker, gdzie nauczyciel może wybrać datę wprowadzania nieobecności.
     */

    public DatePicker wpisywanieNieobecnosciInputData;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie nauczyciel może wybrać daną klasę, gdzie chce wprowadzić nieobecność.
     */

    public ChoiceBox<Klasy> dodawanieNieobecnosciChoiceBoxKlasa;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie nauczyciel może wybrać dany przedmiot, z którego chce wprowadzić uczniowi nieobecność.
     */

    public ChoiceBox<Przedmioty> dodawanieNieobecnosciChoiceBoxPrzedmiot;

    /**
     * Jest to zmienna przechowująca widok tabeli TableView, w której znajdują się obiekty Uczniowie.
     */

    public TableView<Uczniowie> wpisywanieNieobecnosciTableView;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o nazwisku danego ucznia, któremu nauczyciel chce wpisać nieobecność.
     */

    public TableColumn<Uczniowie, String> wpisywanieNieobecnosciColumnNazwisko;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o imieniu danego ucznia, któremu nauczyciel chce dodać ocenę.
     */

    public TableColumn<Uczniowie, String> wpisywanieNieobecnosciColumnImie;

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki AkceptacjiUsprawiedliwień.
     */

    public Tab tabAkceptacjaUsprawiedliwien;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie nauczyciel może wybrać daną klasę, w której chce zaakceptować usprawiedliwienie.
     */

    public ChoiceBox<Klasy> akceptacjaUsprawiedliwienChoiceBoxKlasa;

    /**
     * Jest to zmienna przechowująca widok tabeli TableView, w której znajdują się obiekty Nieobecnosci.
     */

    public TableView<Nieobecnosci> akceptacjaUsprawiedliwienTableView;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o dacie danej nieobecności.
     */

    public TableColumn<Nieobecnosci, Date> akceptacjaUsprawiedliwienColumnData;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o nazwisku ucznia, któremu nauczyciel chce akceptować usprawiedliwienie.
     */

    public TableColumn<Nieobecnosci, String> akceptacjaUsprawiedliwienColumnNazwisko;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o imieniu danego ucznia, któremu nauczyciel chce akceptować usprawiedliwienie.
     */

    public TableColumn<Nieobecnosci, String> akceptacjaUsprawiedliwienColumnImie;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o przedmiocie, z którego nauczyciel chce akceptować usprawiedliwienie.
     */

    public TableColumn<Nieobecnosci, String> akceptacjaUsprawiedliwienColumnPrzedmiot;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o treści usprawiedliwienia, które nauczyciel rozpatrza.
     */

    public TableColumn<Nieobecnosci, String> akceptacjaUsprawiedliwienColumnTresc;




    //================================================================================
    // Dyrektor
    //================================================================================

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki Lista uczniów.
     */

    public Tab tabListaUczniow;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie dyrektor, może wybrać daną klase.
     */

    public ChoiceBox<Klasy> listaUczniowChoiceBoxKlasa;

    /**
     * Jest to zmienna przechowująca widok tabeli TableView, w której znajdują się obiekty Nieobecnosci.
     */

    public TableView<Uczniowie> listaUczniowTableView;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o liście uczniów w danej klasie.
     */

    public TableColumn<Uczniowie, String> listaUczniowColumnKlasa;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o nazwiskach uczniów w danej klasie.
     */

    public TableColumn<Uczniowie, String> listaUczniowColumnNazwisko;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o imionach uczniów w danej klasie.
     */

    public TableColumn<Uczniowie, String> listaUczniowColumnImie;

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki Dodaj rodziców.
     */

    public Tab tabDodajRodzicow;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza imie ojca.
     */

    public TextField dodajRodzicowInputImieOjca;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza nazwisko ojca.
     */

    public TextField dodajRodzicowInputNazwiskoOjca;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza imie matki.
     */

    public TextField dodajRodzicowInputImieMatki;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza nazwisko matki.
     */

    public TextField dodajRodzicowInputNazwiskoMatki;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza login rodziców.
     */

    public TextField dodajRodzicowInputLogin;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza hasło rodziców.
     */

    public PasswordField dodajRodzicowInputHaslo;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza powtórnie hasło rodziców.
     */

    public PasswordField dodajRodzicowInputPowtorzHaslo;

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki Dodaj uczniów.
     */

    public Tab tabDodajUczniow;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza imie ucznia.
     */

    public TextField dodajUczniowInputImie;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza nazwisko ucznia.
     */

    public TextField dodajUczniowInputNazwisko;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza nazwisko rodzica, do którego konta, chce dodać rodzica.
     */

    public TextField dodajUczniowInputSzukaj;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza login ucznia.
     */

    public TextField dodajUczniowInputLogin;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza hasło ucznia.
     */

    public PasswordField dodajUczniowInputHaslo;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza powtórnie login ucznia.
     */

    public PasswordField dodajUczniowInputPowtorzHaslo;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie dyrektor, może wybrać klase, do której chce przypisać ucznia.
     */

    public ChoiceBox<Klasy> dodajUczniowChoiceBoxKlasa;

    /**
     * Jest to zmienna przechowująca widok tabeli TableView, w której znajdują się obiekty Rodzice.
     */

    public TableView<Rodzice> dodajUczniowTableView;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o imieniu ojca, któremu dziecko przypisuje dyrektor.
     */

    public TableColumn<Rodzice, String> dodajUczniowColumnImieOjca;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o nazwisku ojca, któremu dziecko przypisuje dyrektor.
     */

    public TableColumn<Rodzice, String> dodajUczniowColumnNazwiskoOjca;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o imieniu matki, której dziecko przypisuje dyrektor.
     */

    public TableColumn<Rodzice, String> dodajUczniowColumnImieMatki;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o nazwisku matki, której dziecko przypisuje dyrektor.
     */

    public TableColumn<Rodzice, String> dodajUczniowColumnNazwiskoMatki;

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki Dodaj nauczycieli.
     */

    public Tab tabDodajNauczycieli;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza imie nauczyciela, któremu chce stworzyć konto.
     */

    public TextField dodajNauczycieliInputImie;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza nazwisko nauczyciela, któremu chce stworzyć konto.
     */

    public TextField dodajNauczycieliInputNazwisko;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza login nauczyciela, któremu chce stworzyć konto.
     */

    public TextField dodajNauczycieliInputLogin;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza hasło nauczyciela, któremu chce stworzyć konto.
     */

    public PasswordField dodajNauczycieliInputHaslo;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza powtórnie hasło nauczyciela, któremu chce stworzyć konto.
     */

    public PasswordField dodajNauczycieliInputPowtorzHaslo;

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki Dodaj przedmioty.
     */

    public Tab tabDodajPrzedmioty;

    /**
     * Jest to zmienna odnosząca się do pola, gdzie dyrektor wprowadza nazwe przedmiotu jaki chce dodać.
     */

    public TextField dodajPrzedmiotyInputNazwaPrzedmioty;

    /**
     * Jest to zmienna przechowująca widok tabeli TableView, w której znajdują się obiekty Nauczyciele.
     */

    public TableView<Nauczyciele> dodajPrzedmiotyTableView;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o nazwisku nauczyciela, któremu dyrekor przypisuje dany przedmiot.
     */

    public TableColumn<Nauczyciele, String> dodajPrzedmiotyColumnNazwisko;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o imieniu nauczyciela, któremu dyrekor przypisuje dany przedmiot.
     */

    public TableColumn<Nauczyciele, String> dodajPrzedmiotyColumnImie;

    /**
     * Jest to zmienna typu Tab wewnątrz TabPane, odnosząca się do zakładki Przydziel przedmiot do klasy.
     */

    public Tab tabPrzydzielPrzedmiotDoKlasy;

    /**
     * Jest to zmienna odnosząca się do pola wyboru, gdzie dyrektor, może wybrać przedmiot, który chce przydzielić do danej klasy.
     */

    public ChoiceBox<Klasy> przydzielPrzedmiotDoKlasyChoiceBoxKlasa;

    /**
     * Jest to zmienna przechowująca widok tabeli TableView, w której znajdują się obiekty Przedmioty.
     */

    public TableView<Przedmioty> przydzielPrzedmiotDoKlasyTableView;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o nazwie przedmiotu, który dyrekor przypisuje do danej klasy.
     */

    public TableColumn<Przedmioty, String> przydzielPrzedmiotDoKlasyColumnNazwaPrzedmiotu;

    /**
     * Jest to zmienna odnosząca się do kolumny zawierającej informacje o prowadzącym dany przedmiot, któremu dyrektor przypisuje klasę.
     */

    public TableColumn<Przedmioty, String> przydzielPrzedmiotDoKlasyColumnProwadzacy;

    //================================================================================
    // Private
    //================================================================================

    /**
     * Jest to zmienna typu Tab, która przechowuje informacje o aktualnie wybranej zakładce.
     */

    private Tab currentTab;

    /**
     * Jest to zmienna odnosząca się do aktualnie zalogowanego obiektu Nauczyciele.
     */

    private Nauczyciele loggedNauczyciel;

    /**
     * Jest to zmienna odnosząca się do aktualnie zalogowanego obiektu Rodzice.
     */

    private Rodzice loggedRodzic;

    /**
     * Jest to zmienna odnosząca się do aktualnie zalogowanego obiektu Uczniowie.
     */

    private Uczniowie loggedUczen;

    /**
     * Metoda initialize uruchamia się jednocześnie z pozytywnym zalogowaniem się do e-dziennika.
     */

    public void initialize() {

        hideElements();
        loadLoggedUser();

        currentTab = tabPane.getSelectionModel().getSelectedItem();

        loadData(currentTab);

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            currentTab = newTab;
            loadData(currentTab);
        });

    }

    /**
     * Metoda loadLoggedUser odpowiada za sprawdzenie roli,
     * oraz przypisania obiektu użytkownika związanego z zalogowanym kontem do zmiennej.
     */

    private void loadLoggedUser() {

        try (Session session = SessionController.getSession()) {

            switch (authenticatedUser.getRoleByRolaId().getNazwaRoli()) {

                case "nauczyciel":  // Role Nauczyciel
                    Query<Nauczyciele> query1 = session.createQuery("FROM Nauczyciele n WHERE n.kontaByKontoId.kontoId=" + authenticatedUser.getKontoId(), Nauczyciele.class);
                    loggedNauczyciel = FXCollections.observableArrayList(query1.list()).get(0);

                    break;
                case "rodzic":  // Role Rodzic
                    Query<Rodzice> query2 = session.createQuery("FROM Rodzice r WHERE r.kontaByKontoId.kontoId=" + authenticatedUser.getKontoId(), Rodzice.class);
                    loggedRodzic = FXCollections.observableArrayList(query2.list()).get(0);

                    break;
                case "uczen":  // Role Uczen
                    Query<Uczniowie> query3 = session.createQuery("FROM Uczniowie u WHERE u.kontaByKontoId.kontoId=" + authenticatedUser.getKontoId(), Uczniowie.class);
                    loggedUczen = FXCollections.observableArrayList(query3.list()).get(0);

                    break;
            }
        }

    }

    /**
     * Metoda loadData odpowiada za załadowanie danych z bazy danych do tabel.
     * @param tab Jest to parametr, który nam mówi, gdzie dane mają być załadowane.
     */

    //    Function that load data from database to tableviews
    private void loadData(Tab tab) {

        try (Session session = SessionController.getSession()) {

            //================================================================================
            // Uczeń
            //================================================================================

            if (tab.equals(tabOceny)) {

                Query<Oceny> query;

                if (authenticatedUser.getRoleByRolaId().getNazwaRoli().equals("rodzic")) {

                    loadDzieciToChoiceBox(ocenyChoiceBoxDziecko);

                    query = session.createQuery("FROM Oceny o JOIN FETCH o.przedmiotyByPrzedmiotId WHERE o.uczniowieByUczenId.uczenId=" + ocenyChoiceBoxDziecko.getValue().getUczenId(), Oceny.class);
                } else {
                    query = session.createQuery("FROM Oceny o JOIN FETCH o.przedmiotyByPrzedmiotId WHERE o.uczniowieByUczenId.uczenId=" + loggedUczen.getUczenId(), Oceny.class);
                }

                ocenyColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
                ocenyColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("przedmiotyByPrzedmiotId"));
                ocenyColumnOcena.setCellValueFactory(new PropertyValueFactory<>("wartosc"));
                ocenyColumnZaCo.setCellValueFactory(new PropertyValueFactory<>("opis"));

                ocenyTableEdiary.setItems(FXCollections.observableArrayList(query.list()));

            } else if (tab.equals(tabNieobecnosci)) {

                Query<Nieobecnosci> query;

                if (authenticatedUser.getRoleByRolaId().getNazwaRoli().equals("rodzic")) {

                    loadDzieciToChoiceBox(nieobecnosciChoiceBoxDziecko);

                    query = session.createQuery("FROM Nieobecnosci n JOIN FETCH n.przedmiotyByPrzedmiotId WHERE n.uczniowieByUczenId.uczenId=" + nieobecnosciChoiceBoxDziecko.getValue().getUczenId(), Nieobecnosci.class);
                } else {
                    query = session.createQuery("FROM Nieobecnosci n JOIN FETCH n.przedmiotyByPrzedmiotId WHERE n.uczniowieByUczenId.uczenId=" + loggedUczen.getUczenId(), Nieobecnosci.class);

                }

                nieobecnosciColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
                nieobecnosciColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("przedmiotyByPrzedmiotId"));
                nieobecnosciColumnStatus.setCellValueFactory(new PropertyValueFactory<>("wartosc"));

                nieobecnosciTableView.setItems(FXCollections.observableArrayList(query.list()));

            } else if (tab.equals(tabUwagi)) {

                Query<Uwagi> query;

                if (authenticatedUser.getRoleByRolaId().getNazwaRoli().equals("rodzic")) {

                    loadDzieciToChoiceBox(uwagiChoiceBoxDziecko);

                    query = session.createQuery("FROM Uwagi u JOIN FETCH u.przedmiotyByPrzedmiotId WHERE u.uczniowieByUczenId.uczenId=" + uwagiChoiceBoxDziecko.getValue().getUczenId(), Uwagi.class);
                } else {
                    query = session.createQuery("FROM Uwagi u JOIN FETCH u.przedmiotyByPrzedmiotId WHERE u.uczniowieByUczenId.uczenId=" + loggedUczen.getUczenId(), Uwagi.class);
                }

                uwagiColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
                uwagiColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("przedmiotyByPrzedmiotId"));
                uwagiColumnTresc.setCellValueFactory(new PropertyValueFactory<>("wartosc"));

                uwagiTableView.setItems(FXCollections.observableArrayList(query.list()));

            }

            //================================================================================
            // Rodzic
            //================================================================================

            else if (tab.equals(tabUsprawiedliwienia)) {

                Query<Nieobecnosci> query;

                loadDzieciToChoiceBox(usprawiedliwieniaChoiceBoxDziecko);

                query = session.createQuery("FROM Nieobecnosci n JOIN FETCH n.przedmiotyByPrzedmiotId WHERE n.trescUsprawiedliwienia='' AND n.uczniowieByUczenId.uczenId=" + usprawiedliwieniaChoiceBoxDziecko.getValue().getUczenId(), Nieobecnosci.class);

                usprawiedliwieniaColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
                usprawiedliwieniaColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("przedmiotyByPrzedmiotId"));

                usprawiedliwieniaTableView.setItems(FXCollections.observableArrayList(query.list()));

            }

            //================================================================================
            // Nauczyciel
            //================================================================================

            else if (tab.equals(tabDodawanieUwag)) {

                loadKlasaToChoiceBox(dodawanieUwagChoiceBoxKlasa);
                loadPrzedmiotToChoiceBox(dodawanieUwagChoiceBoxPrzedmiot);

                loadUczniowieToTableView(dodawanieUwagChoiceBoxKlasa, dodawanieUwagColumnNazwisko, dodawanieUwagColumnImie, dodawanieUwagTableView);

            } else if (tab.equals(tabDodajOcene)) {

                loadKlasaToChoiceBox(dodawanieOcenChoiceBoxKlasa);
                loadPrzedmiotToChoiceBox(dodawanieOcenChoiceBoxPrzedmiot);

                loadUczniowieToTableView(dodawanieOcenChoiceBoxKlasa, dodawanieOcenColumnNazwisko, dodawanieOcenColumnImie, dodawanieOcenTableView);

            } else if (tab.equals(tabWpisywanieNieobecnosci)) {

                loadKlasaToChoiceBox(dodawanieNieobecnosciChoiceBoxKlasa);
                loadPrzedmiotToChoiceBox(dodawanieNieobecnosciChoiceBoxPrzedmiot);

                loadUczniowieToTableView(dodawanieNieobecnosciChoiceBoxKlasa, wpisywanieNieobecnosciColumnNazwisko, wpisywanieNieobecnosciColumnImie, wpisywanieNieobecnosciTableView);

            } else if (tab.equals(tabAkceptacjaUsprawiedliwien)) {

                loadKlasaToChoiceBox(akceptacjaUsprawiedliwienChoiceBoxKlasa);

                Query<Nieobecnosci> query = session.createQuery("FROM Nieobecnosci n JOIN FETCH n.uczniowieByUczenId JOIN FETCH n.przedmiotyByPrzedmiotId WHERE n.trescUsprawiedliwienia NOT LIKE '' AND n.wartosc LIKE 'nieobecny' AND n.uczniowieByUczenId.klasyByKlasaId.nazwaKlasy='" + akceptacjaUsprawiedliwienChoiceBoxKlasa.getValue() + "'", Nieobecnosci.class);

                akceptacjaUsprawiedliwienColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
                akceptacjaUsprawiedliwienColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
                akceptacjaUsprawiedliwienColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("przedmiotyByPrzedmiotId"));
                akceptacjaUsprawiedliwienColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
                akceptacjaUsprawiedliwienColumnTresc.setCellValueFactory(new PropertyValueFactory<>("trescUsprawiedliwienia"));

                akceptacjaUsprawiedliwienTableView.setItems(FXCollections.observableArrayList(query.list()));

            }

            //================================================================================
            // Dyrektor
            //================================================================================

            else if (tab.equals(tabListaUczniow)) {

                if (listaUczniowChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                    Query<Klasy> query = session.createQuery("FROM Klasy k", Klasy.class);

                    listaUczniowChoiceBoxKlasa.setItems(FXCollections.observableArrayList(query.list()));
                    listaUczniowChoiceBoxKlasa.getSelectionModel().select(0);
                }

                Query<Uczniowie> query = session.createQuery("FROM Uczniowie u JOIN FETCH u.klasyByKlasaId WHERE u.klasyByKlasaId.nazwaKlasy='" + listaUczniowChoiceBoxKlasa.getValue() + "'", Uczniowie.class);

                listaUczniowColumnKlasa.setCellValueFactory(new PropertyValueFactory<>("klasyByKlasaId"));
                listaUczniowColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
                listaUczniowColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

                listaUczniowTableView.setItems(FXCollections.observableArrayList(query.list()));

            } else if (tab.equals(tabDodajUczniow)) {

                Query<Klasy> query = session.createQuery("SELECT k FROM Klasy k", Klasy.class);

                dodajUczniowChoiceBoxKlasa.setItems(FXCollections.observableArrayList(query.list()));
                dodajUczniowChoiceBoxKlasa.getSelectionModel().select(0);

                Query<Rodzice> query2 = session.createQuery("SELECT r FROM Rodzice r", Rodzice.class);

                dodajUczniowColumnImieOjca.setCellValueFactory(new PropertyValueFactory<>("imieOjca"));
                dodajUczniowColumnNazwiskoOjca.setCellValueFactory(new PropertyValueFactory<>("nazwiskoOjca"));
                dodajUczniowColumnImieMatki.setCellValueFactory(new PropertyValueFactory<>("imieMatki"));
                dodajUczniowColumnNazwiskoMatki.setCellValueFactory(new PropertyValueFactory<>("nazwiskoMatki"));

                dodajUczniowTableView.setItems(FXCollections.observableArrayList(query2.list()));

            } else if (tab.equals(tabDodajPrzedmioty)) {

                Query<Nauczyciele> query = session.createQuery("SELECT n FROM Nauczyciele n", Nauczyciele.class);

                dodajPrzedmiotyColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
                dodajPrzedmiotyColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

                dodajPrzedmiotyTableView.setItems(FXCollections.observableArrayList(query.list()));

            } else if (tab.equals(tabPrzydzielPrzedmiotDoKlasy)) {

                Query<Przedmioty> query = session.createQuery("FROM Przedmioty p JOIN FETCH p.nauczycieleByNauczycielId", Przedmioty.class);

                przydzielPrzedmiotDoKlasyColumnNazwaPrzedmiotu.setCellValueFactory(new PropertyValueFactory<>("nazwaPrzedmiotu"));
                przydzielPrzedmiotDoKlasyColumnProwadzacy.setCellValueFactory(new PropertyValueFactory<>("nauczycieleByNauczycielId"));

                przydzielPrzedmiotDoKlasyTableView.setItems(FXCollections.observableArrayList(query.list()));

                if (przydzielPrzedmiotDoKlasyChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                    Query<Klasy> query2 = session.createQuery("FROM Klasy k", Klasy.class);

                    przydzielPrzedmiotDoKlasyChoiceBoxKlasa.setItems(FXCollections.observableArrayList(query2.list()));
                    przydzielPrzedmiotDoKlasyChoiceBoxKlasa.getSelectionModel().select(0);
                }
            }
        }
    }

    /**
     * Metoda loadUczniowieToTableView odpowiada za załadowanie danych o danym uczniu do tabeli
     * @param choiceBoxKlasa Jest to parametr przechowujący listę obiektów Klasy.
     * @param columnNazwisko Jest to parametr wskazujący nazwiska uczniów w danej klasie.
     * @param columnImie Jest to parametr wskazujący imiona uczniów w danej klasie.
     * @param tableView Parametr przechowuje tabele, do której wrzucamy dane.
     */

    private void loadUczniowieToTableView(ChoiceBox<Klasy> choiceBoxKlasa, TableColumn<Uczniowie, String> columnNazwisko, TableColumn<Uczniowie, String> columnImie, TableView<Uczniowie> tableView) {

        try (Session session = SessionController.getSession()) {

            Query<Uczniowie> query = session.createQuery("FROM Uczniowie u WHERE u.klasyByKlasaId.nazwaKlasy='" + choiceBoxKlasa.getValue() + "'", Uczniowie.class);

            columnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            columnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

            tableView.setItems(FXCollections.observableArrayList(query.list()));
        }
    }

    /**
     * Metoda loadPrzedmiotToChoiceBox odpowiada za załadowanie informacji o przedmiotach, które uczy zalogowany nauczyciel.
     * @param cb Przechowuje listę przedmiotów, którę prowadzi zalogowane nauczyciel.
     */

    private void loadPrzedmiotToChoiceBox(ChoiceBox<Przedmioty> cb) {

        try (Session session = SessionController.getSession()) {

            Query<Przedmioty> query = session.createQuery("SELECT kp.przedmiotyByPrzedmiotId FROM KlasyPrzedmioty kp WHERE kp.przedmiotyByPrzedmiotId.nauczycieleByNauczycielId.id=" + loggedNauczyciel.getNauczycielId() + " AND kp.klasyByKlasaId.nazwaKlasy='" + dodawanieUwagChoiceBoxKlasa.getValue() + "'", Przedmioty.class);

            cb.setItems(FXCollections.observableArrayList(query.list()));

            if (cb.getSelectionModel().isEmpty()) {
                cb.getSelectionModel().select(0);
            }
        }
    }

    /**
     * Metoda loadKlasaToChoiceBox odpowiada za załadowanie informacji o klasach, które uczy zalogowany nauczyciel.
     * @param cb Przechowuje listę klas, którę prowadzi zalogowane nauczyciel.
     */

    private void loadKlasaToChoiceBox(ChoiceBox<Klasy> cb) {
        try (Session session = SessionController.getSession()) {
            if (cb.getSelectionModel().isEmpty()) {

                Query<Klasy> query = session.createQuery("SELECT kp.klasyByKlasaId FROM KlasyPrzedmioty kp WHERE kp.przedmiotyByPrzedmiotId.nauczycieleByNauczycielId.id=" + loggedNauczyciel.getNauczycielId(), Klasy.class);

                cb.setItems(FXCollections.observableArrayList(query.list()));
                cb.getSelectionModel().select(0);
            }
        }
    }

    //================================================================================
    // Rodzic
    //================================================================================

    /**
     * Metoda loadDzieciToChoiceBox odpowiada za załadowanie informacji o dziaciach zalogowanego rodzica.
     * @param cb Przechowuje listę uczniów, których konta są powiązane z zalogowanym rodzicem.
     */

    private void loadDzieciToChoiceBox(ChoiceBox<Uczniowie> cb) {

        try (Session session = SessionController.getSession()) {
            if (cb.getSelectionModel().isEmpty()) {
                Query<Uczniowie> query1 = session.createQuery("FROM Uczniowie u WHERE u.rodziceByRodzicId.id=" + loggedRodzic.getRodzicId(), Uczniowie.class);

                cb.setItems(FXCollections.observableArrayList(query1.list()));
                cb.getSelectionModel().select(0);
            }
        }

    }


    /**
     * Metoda saveData odpowiada za zapisanie danych z tabel do bazy danych.
     * @param tab Jest to parametr, który nam mówi, gdzie dane mają być zapisane.
     */

    private void saveData(Tab tab) {

        try (Session session = SessionController.getSession()) {

            //================================================================================
            // Rodzic
            //================================================================================

            if (tab.equals(tabUsprawiedliwienia)) {

                if (usprawiedliwieniaTableView.getSelectionModel().getSelectedItem() != null) {
                    Nieobecnosci nieobecnosci = usprawiedliwieniaTableView.getSelectionModel().getSelectedItem();

                    nieobecnosci.setTrescUsprawiedliwienia(usprawiedliwieniaTextFieldTresc.getText());

                    session.beginTransaction();
                    session.update(nieobecnosci);
                    session.getTransaction().commit();

                    if (usprawiedliwieniaTextFieldTresc.getText().isEmpty()) {
                        showAlert(Alert.AlertType.WARNING, "Podaj treść usprawiedliwienia!");
                    } else {
                        showAlert(Alert.AlertType.CONFIRMATION, "Pomyślnie dodano usprawiedliwienie!");
                        clear(currentTab);
                    }

                } else {
                    showAlert(Alert.AlertType.WARNING, "Wybierz nieobecność do usprawiedliwienia");
                }
            }

            //================================================================================
            // Nauczyciel
            //================================================================================

            else if (tab.equals(tabDodawanieUwag)) {

                if (dodawanieUwagTableView.getSelectionModel().getSelectedItem() != null) {

                    if (!dodawanieUwagTextFieldTresc.getText().isEmpty() && dodawanieUwagDatePickerData.getValue() != null) {
                        Uwagi nowaUwaga = new Uwagi();

                        nowaUwaga.setUczniowieByUczenId(dodawanieUwagTableView.getSelectionModel().getSelectedItem());
                        nowaUwaga.setPrzedmiotyByPrzedmiotId(dodawanieUwagChoiceBoxPrzedmiot.getValue());
                        nowaUwaga.setWartosc(dodawanieUwagTextFieldTresc.getText());
                        nowaUwaga.setData(Date.valueOf(dodawanieUwagDatePickerData.getValue()));

                        session.beginTransaction();
                        session.save(nowaUwaga);
                        session.getTransaction().commit();

                        showAlert(Alert.AlertType.CONFIRMATION, "Pomyślnie dodano uwagę!");
                        clear(currentTab);

                    } else {
                        showAlert(Alert.AlertType.CONFIRMATION, "Uzupełnij wszystkie pola!");
                    }

                } else {
                    showAlert(Alert.AlertType.WARNING, "Nie wybrano ucznia!");
                }

            } else if (tab.equals(tabDodajOcene)) {

                if (dodawanieOcenTableView.getSelectionModel().getSelectedItem() != null) {

                    if (!dodawanieOcenInputStopien.getText().isEmpty() && !dodawanieOcenInputZaco.getText().isEmpty() && dodawanieOcenInputData.getValue() != null) {
                        Oceny nowaOcena = new Oceny();

                        nowaOcena.setUczniowieByUczenId(dodawanieOcenTableView.getSelectionModel().getSelectedItem());
                        nowaOcena.setPrzedmiotyByPrzedmiotId(dodawanieOcenChoiceBoxPrzedmiot.getValue());
                        nowaOcena.setWartosc(dodawanieOcenInputStopien.getText());
                        nowaOcena.setOpis(dodawanieOcenInputZaco.getText());
                        nowaOcena.setData(Date.valueOf(dodawanieOcenInputData.getValue()));

                        session.beginTransaction();
                        session.save(nowaOcena);
                        session.getTransaction().commit();

                        showAlert(Alert.AlertType.CONFIRMATION, "Pomyślnie dodano ocenę!");
                        clear(currentTab);

                    } else {
                        showAlert(Alert.AlertType.WARNING, "Uzupełnij wszystkie pola!");
                    }

                } else {
                    showAlert(Alert.AlertType.WARNING, "Nie wybrano ucznia!");
                }

            } else if (tab.equals(tabWpisywanieNieobecnosci)) {

                if (wpisywanieNieobecnosciTableView.getSelectionModel().getSelectedItem() != null) {

                    if (wpisywanieNieobecnosciInputData.getValue() != null) {
                        Nieobecnosci nowaNieobecnosc = new Nieobecnosci();

                        nowaNieobecnosc.setUczniowieByUczenId(wpisywanieNieobecnosciTableView.getSelectionModel().getSelectedItem());
                        nowaNieobecnosc.setPrzedmiotyByPrzedmiotId(dodawanieNieobecnosciChoiceBoxPrzedmiot.getValue());
                        nowaNieobecnosc.setData(Date.valueOf(wpisywanieNieobecnosciInputData.getValue()));
                        nowaNieobecnosc.setWartosc("nieobecny");
                        nowaNieobecnosc.setTrescUsprawiedliwienia("");

                        session.beginTransaction();
                        session.save(nowaNieobecnosc);
                        session.getTransaction().commit();

                        showAlert(Alert.AlertType.CONFIRMATION, "Pomyślnie dodano nieobecność!");

                    } else {
                        showAlert(Alert.AlertType.WARNING, "Nie podano daty!");
                    }

                } else {
                    showAlert(Alert.AlertType.WARNING, "Nie wybrano ucznia!");
                }

            } else if (tab.equals(tabAkceptacjaUsprawiedliwien)) {

                if (akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem() != null) {

                    Nieobecnosci nieobecnosc = akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem();

                    nieobecnosc.setWartosc("usprawiedliwiona");

                    session.beginTransaction();
                    session.update(nieobecnosc);
                    session.getTransaction().commit();

                    showAlert(Alert.AlertType.CONFIRMATION, "Pomyślnie usprawiedliwiono!");

                } else {
                    showAlert(Alert.AlertType.WARNING, "Nie wybrano usprawiedliwienia!");
                }

            }

            //================================================================================
            // Dyrektor
            //================================================================================

            else if (tab.equals(tabDodajRodzicow)) {

                if (!dodajRodzicowInputLogin.getText().isEmpty()
                        && !dodajRodzicowInputHaslo.getText().isEmpty() && !dodajRodzicowInputPowtorzHaslo.getText().isEmpty()
                        && !dodajRodzicowInputImieOjca.getText().isEmpty()
                        && !dodajRodzicowInputNazwiskoOjca.getText().isEmpty()
                        && !dodajRodzicowInputImieMatki.getText().isEmpty()
                        && !dodajRodzicowInputNazwiskoMatki.getText().isEmpty()
                ) {
                    if (dodajRodzicowInputPowtorzHaslo.getText().equals(dodajRodzicowInputHaslo.getText())) {

                        Query<Konta> query = session.createQuery("FROM Konta k WHERE k.login='" + dodajRodzicowInputLogin.getText() + "'", Konta.class);

                        if (FXCollections.observableArrayList(query.list()).isEmpty()) {
                            Query<Role> query1 = session.createQuery("FROM Role r WHERE r.nazwaRoli LIKE 'rodzic'", Role.class);

                            createNewAccount(query1, dodajRodzicowInputLogin, dodajRodzicowInputHaslo);

                            Query<Konta> query2 = session.createQuery("FROM Konta k WHERE k.login='" + dodajRodzicowInputLogin.getText() + "'", Konta.class);
                            Konta konto = FXCollections.observableArrayList(query2.list()).get(0);

                            Rodzice nowyRodzic = new Rodzice();

                            nowyRodzic.setKontaByKontoId(konto);
                            nowyRodzic.setImieOjca(dodajRodzicowInputImieOjca.getText());
                            nowyRodzic.setNazwiskoOjca(dodajRodzicowInputNazwiskoOjca.getText());
                            nowyRodzic.setImieMatki(dodajRodzicowInputImieMatki.getText());
                            nowyRodzic.setNazwiskoMatki(dodajRodzicowInputNazwiskoMatki.getText());

                            session.beginTransaction();
                            session.save(nowyRodzic);
                            session.getTransaction().commit();

                            showAlert(Alert.AlertType.CONFIRMATION, "Pomyślnie dodano konto rodzica!");
                        } else {
                            showAlert(Alert.AlertType.ERROR, "Konto o takim loginie już istnieje!");
                        }
                    } else {
                        showAlert(Alert.AlertType.WARNING, "Hasła nie są identyczne");
                    }
                } else {
                    showAlert(Alert.AlertType.WARNING, "Uzupełnij wszystkie pola!");
                }

            } else if (tab.equals(tabDodajUczniow)) {

                Query<Role> query1 = session.createQuery("SELECT r FROM Role r WHERE r.nazwaRoli LIKE 'uczen'", Role.class);

                Konta noweKonto = new Konta();
                if (!dodajUczniowInputLogin.getText().isEmpty() && !dodajUczniowInputHaslo.getText().isEmpty() && !dodajUczniowInputPowtorzHaslo.getText().isEmpty() && !dodajUczniowInputImie.getText().isEmpty() && !dodajUczniowInputNazwisko.getText().isEmpty() && dodajUczniowTableView.getSelectionModel().getSelectedItem() != null) {

                    if (dodajUczniowInputPowtorzHaslo.getText().equals(dodajUczniowInputHaslo.getText())) {

                        Query<Konta> query = session.createQuery("FROM Konta k WHERE k.login='" + dodajUczniowInputLogin.getText() + "'", Konta.class);

                        if (FXCollections.observableArrayList(query.list()).isEmpty()) {
                            noweKonto.setLogin(dodajUczniowInputLogin.getText());
                            noweKonto.setHaslo(dodajUczniowInputHaslo.getText());
                            noweKonto.setRoleByRolaId(FXCollections.observableArrayList(query1.list()).get(0));

                            session.beginTransaction();
                            session.save(noweKonto);
                            session.getTransaction().commit();

                            Query<Konta> query2 = session.createQuery("SELECT k FROM Konta k WHERE k.login='" + dodajUczniowInputLogin.getText() + "'", Konta.class);

                            Uczniowie nowyUczen = new Uczniowie();

                            nowyUczen.setKontaByKontoId(FXCollections.observableArrayList(query2.list()).get(0));
                            nowyUczen.setImie(dodajUczniowInputImie.getText());
                            nowyUczen.setNazwisko(dodajUczniowInputNazwisko.getText());
                            nowyUczen.setKlasyByKlasaId(dodajUczniowChoiceBoxKlasa.getValue());
                            nowyUczen.setRodziceByRodzicId(dodajUczniowTableView.getSelectionModel().getSelectedItem());
                            session.beginTransaction();
                            session.save(nowyUczen);
                            session.getTransaction().commit();

                            showAlert(Alert.AlertType.CONFIRMATION, "Pomyślnie dodano konto ucznia!");
                        } else {
                            showAlert(Alert.AlertType.ERROR, "Konto o takim loginie już istnieje!");
                        }
                    } else {
                        showAlert(Alert.AlertType.WARNING, "Hasła nie są identyczne");
                    }
                } else {
                    showAlert(Alert.AlertType.WARNING, "Uzupełnij wszystkie pola!");
                }
            } else if (tab.equals(tabDodajNauczycieli)) {

                if (!dodajNauczycieliInputLogin.getText().isEmpty() && !dodajNauczycieliInputHaslo.getText().isEmpty() && !dodajNauczycieliInputPowtorzHaslo.getText().isEmpty() && !dodajNauczycieliInputImie.getText().isEmpty() && !dodajNauczycieliInputNazwisko.getText().isEmpty()) {

                    if (dodajNauczycieliInputPowtorzHaslo.getText().equals(dodajNauczycieliInputHaslo.getText())) {

                        Query<Konta> query = session.createQuery("FROM Konta k WHERE k.login='" + dodajNauczycieliInputLogin.getText() + "'", Konta.class);

                        if (FXCollections.observableArrayList(query.list()).isEmpty()) {
                            Query<Role> query1 = session.createQuery("SELECT r FROM Role r WHERE r.nazwaRoli LIKE 'nauczyciel'", Role.class);

                            createNewAccount(query1, dodajNauczycieliInputLogin, dodajNauczycieliInputHaslo);

                            Query<Konta> query2 = session.createQuery("SELECT k FROM Konta k WHERE k.login='" + dodajNauczycieliInputLogin.getText() + "'", Konta.class);
                            Konta konto = FXCollections.observableArrayList(query2.list()).get(0);

                            Nauczyciele nowyNauczyciel = new Nauczyciele();

                            nowyNauczyciel.setKontaByKontoId(konto);
                            nowyNauczyciel.setImie(dodajNauczycieliInputImie.getText());
                            nowyNauczyciel.setNazwisko(dodajNauczycieliInputNazwisko.getText());

                            session.beginTransaction();
                            session.save(nowyNauczyciel);
                            session.getTransaction().commit();

                            showAlert(Alert.AlertType.CONFIRMATION, "Pomyślnie dodano konto nauczyciela");
                        } else {
                            showAlert(Alert.AlertType.ERROR, "Konto o takim loginie już istnieje!");
                        }
                    } else {
                        showAlert(Alert.AlertType.WARNING, "Hasła nie są identyczne");
                    }
                } else {
                    showAlert(Alert.AlertType.WARNING, "Uzupełnij wszystkie pola!");
                }

            } else if (tab.equals(tabDodajPrzedmioty)) {

                if (dodajPrzedmiotyTableView.getSelectionModel().getSelectedItem() != null && !dodajPrzedmiotyInputNazwaPrzedmioty.getText().isEmpty()) {

                    Query<Przedmioty> query = session.createQuery("FROM Przedmioty p WHERE p.nazwaPrzedmiotu='" + dodajPrzedmiotyInputNazwaPrzedmioty.getText() + "' AND p.nauczycieleByNauczycielId.id=" + dodajPrzedmiotyTableView.getSelectionModel().getSelectedItem().getNauczycielId(), Przedmioty.class);

                    if (FXCollections.observableArrayList(query.list()).isEmpty()) {

                        Przedmioty nowyPrzedmiot = new Przedmioty();
                        nowyPrzedmiot.setNazwaPrzedmiotu(dodajPrzedmiotyInputNazwaPrzedmioty.getText());
                        nowyPrzedmiot.setNauczycieleByNauczycielId(dodajPrzedmiotyTableView.getSelectionModel().getSelectedItem());

                        session.beginTransaction();
                        session.save(nowyPrzedmiot);
                        session.getTransaction().commit();

                        showAlert(Alert.AlertType.CONFIRMATION, "Pomyślnie dodano przedmiot");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Ten nauczyciel już uczy tego przedmiotu");
                    }
                } else {
                    showAlert(Alert.AlertType.WARNING, "Uzupełnij wszystkie pola!");
                }

            } else if (tab.equals(tabPrzydzielPrzedmiotDoKlasy)) {

                if (przydzielPrzedmiotDoKlasyTableView.getSelectionModel().getSelectedItem() != null) {

                    Query<KlasyPrzedmioty> query = session.createQuery("FROM KlasyPrzedmioty kp WHERE kp.klasyByKlasaId.id=" + przydzielPrzedmiotDoKlasyChoiceBoxKlasa.getValue().getKlasaId() + " AND kp.przedmiotyByPrzedmiotId.id=" + przydzielPrzedmiotDoKlasyTableView.getSelectionModel().getSelectedItem().getPrzedmiotId(), KlasyPrzedmioty.class);

                    if (FXCollections.observableArrayList(query.list()).isEmpty()) {
                        KlasyPrzedmioty klasyPrzedmioty = new KlasyPrzedmioty();
                        klasyPrzedmioty.setKlasyByKlasaId(przydzielPrzedmiotDoKlasyChoiceBoxKlasa.getValue());
                        klasyPrzedmioty.setPrzedmiotyByPrzedmiotId(przydzielPrzedmiotDoKlasyTableView.getSelectionModel().getSelectedItem());

                        session.beginTransaction();
                        session.save(klasyPrzedmioty);
                        session.getTransaction().commit();

                        showAlert(Alert.AlertType.CONFIRMATION, "Pomyślnie przypisano przedmiot do klasy.");
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Ten przedmiot jest już przypisany do tej klasy!");
                    }
                } else {
                    showAlert(Alert.AlertType.WARNING, "Wybierz przedmiot");
                }
            }
        }

    }

    /**
     * Metoda createNewAccount odpowiada za dodanie nowego konta do aplikacji.
     * @param queryRola Jest to parametr, który przechowuje role tworzonego użytkownika.
     * @param inputLogin Jest to parametr, który przechowuje login tworzonego użytkownika.
     * @param inputHaslo Jest to parametr, który przechowuje hasło tworzonego użytkownika.
     */

    private void createNewAccount(Query<Role> queryRola, TextField inputLogin, PasswordField inputHaslo) {

        try (Session session = SessionController.getSession()) {
            Konta noweKonto = new Konta();
            noweKonto.setLogin(inputLogin.getText());
            noweKonto.setHaslo(inputHaslo.getText());
            noweKonto.setRoleByRolaId(FXCollections.observableArrayList(queryRola.list()).get(0));

            session.beginTransaction();
            session.save(noweKonto);
            session.getTransaction().commit();
        }
    }

    /**
     * Metoda rejectExcuse odpowiada za odrzucenie usprawiedliwienia przez nauczyciela.
     */
    public void rejectExcuse() {

        try (Session session = SessionController.getSession()) {

            if (akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem() != null) {

                Nieobecnosci nieobecnosc = akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem();

                nieobecnosc.setWartosc("nieusprawiedliwiona");

                session.beginTransaction();
                session.update(nieobecnosc);
                session.getTransaction().commit();

                showAlert(Alert.AlertType.CONFIRMATION, "Pomyślnie nieusprawiedliwiono!");
            } else {
                showAlert(Alert.AlertType.WARNING, "Nie wybrano usprawiedliwienia!");
            }

        }

        loadData(currentTab);
    }

    /**
     * Metoda showAlert odpowiada wyświetlenie komunikatu w nowym oknie.
     * @param alertType Przechowuje typ alertu.
     * @param content Przechowuje treść alertu.
     */

    private void showAlert(Alert.AlertType alertType, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Komunikat");
        alert.setHeaderText(null);
        alert.setContentText(content);

        ButtonType buttonTypeYes = new ButtonType("OK");

        alert.getButtonTypes().setAll(buttonTypeYes);

        alert.showAndWait();
    }

    /**
     * Metoda hideElements odpowiada za usunięcie elementów interfejsu, do których użytkownik nie ma uprawnień.
     */

    private void hideElements() {

        switch (authenticatedUser.getRoleByRolaId().getNazwaRoli()) {

            case "dyrektor":  // Role Dyrektor
                hideElementsNauczyciel();
                hideElementsRodzic();
                hideElementsUczen();

                break;
            case "nauczyciel":  // Role Nauczyciel
                hideElementsDyrektor();
                hideElementsRodzic();
                hideElementsUczen();

                break;
            case "rodzic":  // Role Rodzic
                hideElementsDyrektor();
                hideElementsNauczyciel();

                break;
            case "uczen":  // Role Uczen
                hideElementsDyrektor();
                hideElementsNauczyciel();
                hideElementsRodzic();

                break;

        }

    }

    /**
     * Metoda hideElementsDyrektor odpowiada za ukrycie elementów przeznaczonych tylko dla dyrektora.
     */

    private void hideElementsDyrektor() {

        tabPane.getTabs().remove(tabListaUczniow);
        tabPane.getTabs().remove(tabDodajRodzicow);
        tabPane.getTabs().remove(tabDodajUczniow);
        tabPane.getTabs().remove(tabDodajNauczycieli);
        tabPane.getTabs().remove(tabDodajPrzedmioty);
        tabPane.getTabs().remove(tabPrzydzielPrzedmiotDoKlasy);

    }

    /**
     * Metoda hideElementsNauczyciel odpowiada za ukrycie elementów przeznaczonych tylko dla nauczyciela.
     */

    private void hideElementsNauczyciel() {

        tabPane.getTabs().remove(tabDodawanieUwag);
        tabPane.getTabs().remove(tabDodajOcene);
        tabPane.getTabs().remove(tabWpisywanieNieobecnosci);
        tabPane.getTabs().remove(tabAkceptacjaUsprawiedliwien);

    }

    /**
     * Metoda hideElementsDyrektor odpowiada za ukrycie elementów przeznaczonych tylko dla rodzica.
     */

    private void hideElementsRodzic() {

        tabPane.getTabs().remove(tabUsprawiedliwienia);
        tabOcenyGridPaneDziecko.setManaged(false);
        tabNieobecnosciGridPaneDziecko.setManaged(false);
        tabUwagiGridPaneDziecko.setManaged(false);
        tabUsprawiedliwieniaGridPaneDziecko.setManaged(false);

    }

    /**
     * Metoda hideElementsDyrektor odpowiada za ukrycie elementów przeznaczonych tylko dla ucznia.
     */

    private void hideElementsUczen() {

        tabPane.getTabs().remove(tabOceny);
        tabPane.getTabs().remove(tabNieobecnosci);
        tabPane.getTabs().remove(tabUwagi);
    }

    /**
     * Metoda generateGrades służy do generowania raportów PDF.
     */

    public void generateGrades() {

        try (Session session = SessionController.getSession()) {

            Query<Oceny> query;

            if (authenticatedUser.getRoleByRolaId().getNazwaRoli().equals("rodzic")) {
                query = session.createQuery("FROM Oceny o JOIN FETCH o.przedmiotyByPrzedmiotId WHERE o.uczniowieByUczenId.uczenId=" + ocenyChoiceBoxDziecko.getValue().getUczenId(), Oceny.class);
            } else {
                query = session.createQuery("FROM Oceny o JOIN FETCH o.przedmiotyByPrzedmiotId WHERE o.uczniowieByUczenId.uczenId=" + loggedUczen.getUczenId(), Oceny.class);
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Generowanie PDF");
            alert.setHeaderText(null);
            alert.setContentText("Jesteś pewien, że chcesz wygenerować PDF?");

            ButtonType buttonTypeNo = new ButtonType("Nie");
            ButtonType buttonTypeYes = new ButtonType("Tak");

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            alert.showAndWait().ifPresent(rs -> {
                if (rs == buttonTypeYes) {
                    try {
                        new PdfGenerator().PDFGenerateGrades(FXCollections.observableArrayList(query.list()));
                        showAlert(Alert.AlertType.CONFIRMATION, "PDF został zapisany w folderze z aplikacją.");
                    } catch (IOException | DocumentException e) {
                        showAlert(Alert.AlertType.ERROR, "Nie udało się zapisać raportu PDF.");
                    }
                }
            });

        }

    }

    /**
     * Metoda refreshActionHandle odpowiada za obsługę przycisku Odswież.
     */
    public void refreshActionHandle() {
        loadData(currentTab);
    }

    /**
     * Metoda saveData odpowiada za obsługę przycisku Przypisz/Dodaj/Zapisz.
     */

    public void saveData() {
        saveData(currentTab);
        loadData(currentTab);
    }

    /**
     * Metoda clearButtonActionHandle odpowiada za obsługę przycisku Wyczyść.
     */

    public void clearButtonActionHandle() {
        clear(currentTab);
    }

    /**
     * Metoda clear odpowiada za wyczyszczenie pól do wpisywania danych(TextField).
     * @param tab Przechowuje informacje o zakładce.
     */

    private void clear(Tab tab) {

        //================================================================================
        // Rodzic
        //================================================================================

        if (tab.equals(tabUsprawiedliwienia)) {
            usprawiedliwieniaTextFieldTresc.clear();
        }

        //================================================================================
        // Nauczyciel
        //================================================================================

        else if (tab.equals(tabDodawanieUwag)) {
            dodawanieUwagDatePickerData.setValue(null);
            dodawanieUwagTextFieldTresc.clear();

        } else if (tab.equals(tabDodajOcene)) {
            dodawanieOcenInputData.setValue(null);
            dodawanieOcenInputStopien.clear();
            dodawanieOcenInputZaco.clear();

        } else if (tab.equals(tabWpisywanieNieobecnosci)) {
            wpisywanieNieobecnosciInputData.setValue(null);
        }

        //================================================================================
        // Dyrektor
        //================================================================================

        else if (tab.equals(tabDodajRodzicow)) {
            dodajRodzicowInputLogin.clear();
            dodajRodzicowInputHaslo.clear();
            dodajRodzicowInputPowtorzHaslo.clear();
            dodajRodzicowInputImieOjca.clear();
            dodajRodzicowInputNazwiskoOjca.clear();
            dodajRodzicowInputImieMatki.clear();
            dodajRodzicowInputNazwiskoMatki.clear();

        } else if (tab.equals(tabDodajUczniow)) {
            dodajUczniowInputLogin.clear();
            dodajUczniowInputHaslo.clear();
            dodajUczniowInputPowtorzHaslo.clear();
            dodajUczniowInputImie.clear();
            dodajUczniowInputNazwisko.clear();
            dodajUczniowInputSzukaj.clear();
            loadData(currentTab);

        } else if (tab.equals(tabDodajNauczycieli)) {
            dodajNauczycieliInputLogin.clear();
            dodajNauczycieliInputHaslo.clear();
            dodajNauczycieliInputPowtorzHaslo.clear();
            dodajNauczycieliInputImie.clear();
            dodajNauczycieliInputNazwisko.clear();

        } else if (tab.equals(tabDodajPrzedmioty)) {
            dodajPrzedmiotyInputNazwaPrzedmioty.clear();

        }
    }

    /**
     * Metoda searchButtonActionHandle odpowiada za wyszukanie odpowiednich danych wyjściowych.
     */

    public void searchButtonActionHandle() {

        try (Session session = SessionController.getSession()) {
            Query<Rodzice> query = session.createQuery("FROM Rodzice r WHERE r.imieOjca LIKE '%" + dodajUczniowInputSzukaj.getText() + "%' OR r.nazwiskoOjca LIKE '%" + dodajUczniowInputSzukaj.getText() + "%' OR r.imieMatki LIKE '%" + dodajUczniowInputSzukaj.getText() + "%' OR r.nazwiskoMatki LIKE '%" + dodajUczniowInputSzukaj.getText() + "%'", Rodzice.class);

            dodajUczniowTableView.setItems(FXCollections.observableArrayList(query.list()));
        }
    }
}

