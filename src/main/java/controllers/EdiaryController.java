package controllers;

import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mapping.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.PdfGenerator;

import java.io.IOException;
import java.sql.Date;

import static controllers.LoginController.authenticatedUser;

// Class handling Ediary Window actions

public class EdiaryController {

    public TabPane tabPane;

    //================================================================================
    // Uczeń
    //================================================================================

    public Tab tabOceny;
    public TableView<Oceny> ocenyTableEdiary;
    public TableColumn<Oceny, Date> ocenyColumnData;
    public TableColumn<Oceny, String> ocenyColumnPrzedmiot;
    public TableColumn<Oceny, String> ocenyColumnOcena;
    public TableColumn<Oceny, String> ocenyColumnZaCo;

    public Tab tabNieobecnosci;
    public TableView<Nieobecnosci> nieobecnosciTableView;
    public TableColumn<Nieobecnosci, Date> nieobecnosciColumnData;
    public TableColumn<Nieobecnosci, String> nieobecnosciColumnPrzedmiot;
    public TableColumn<Nieobecnosci, String> nieobecnosciColumnStatus;

    public Tab tabUwagi;
    public TableView<Uwagi> uwagiTableView;
    public TableColumn<Uwagi, String> uwagiColumnTresc;
    public TableColumn<Uwagi, String> uwagiColumnPrzedmiot;
    public TableColumn<Uwagi, Date> uwagiColumnData;

    //================================================================================
    // Rodzic
    //================================================================================

    public Tab tabUsprawiedliwienia;
    public TextField usprawiedliwieniaTextFieldTresc;
    public TableView<Nieobecnosci> usprawiedliwieniaTableView;
    public TableColumn<Nieobecnosci, Date> usprawiedliwieniaColumnData;
    public TableColumn<Nieobecnosci, String> usprawiedliwieniaColumnPrzedmiot;

    //================================================================================
    // Nauczyciel
    //================================================================================

    public Tab tabListaUczniow;
    public ChoiceBox<Klasy> listaUczniowChoiceBoxKlasa;
    public TableView<Uczniowie> listaUczniowTableView;
    public TableColumn<Uczniowie, String> listaUczniowColumnKlasa;
    public TableColumn<Uczniowie, String> listaUczniowColumnNazwisko;
    public TableColumn<Uczniowie, String> listaUczniowColumnImie;

    public Tab tabDodawanieUwag;
    public DatePicker dodawanieUwagDatePickerData;
    public TextField dodawanieUwagTextFieldTresc;
    public ChoiceBox<Klasy> dodawanieUwagChoiceBoxKlasa;
    public ChoiceBox<Przedmioty> dodawanieUwagChoiceBoxPrzedmiot;
    public TableView<Uczniowie> dodawanieUwagTableView;
    public TableColumn<Uczniowie, String> dodawanieUwagColumnNazwisko;
    public TableColumn<Uczniowie, String> dodawanieUwagColumnImie;

    public Tab tabDodajOcene;
    public Button dodawanieOcenButtonDodaj;
    public DatePicker dodawanieOcenInputData;
    public TextField dodawanieOcenInputStopien;
    public TextField dodawanieOcenInputZaco;
    public ChoiceBox<Klasy> dodawanieOcenChoiceBoxKlasa;
    public ChoiceBox<Przedmioty> dodawanieOcenChoiceBoxPrzedmiot;
    public TableView<Uczniowie> dodawanieOcenTableView;
    public TableColumn<Uczniowie, String> dodawanieOcenColumnNazwisko;
    public TableColumn<Uczniowie, String> dodawanieOcenColumnImie;

    public Tab tabWpisywanieNieobecnosci;
    public DatePicker wpisywanieNieobecnosciInputData;
    public ChoiceBox<Klasy> dodawanieNieobecnosciChoiceBoxKlasa;
    public ChoiceBox<Przedmioty> dodawanieNieobecnosciChoiceBoxPrzedmiot;
    public TableView<Uczniowie> wpisywanieNieobecnosciTableView;
    public TableColumn<Uczniowie, String> wpisywanieNieobecnosciColumnNazwisko;
    public TableColumn<Uczniowie, String> wpisywanieNieobecnosciColumnImie;

    public Tab tabAkceptacjaUsprawiedliwien;
    public ChoiceBox<Klasy> akceptacjaUsprawiedliwienChoiceBoxKlasa;
    public TableView<Nieobecnosci> akceptacjaUsprawiedliwienTableView;
    public TableColumn<Nieobecnosci, Date> akceptacjaUsprawiedliwienColumnData;
    public TableColumn<Nieobecnosci, String> akceptacjaUsprawiedliwienColumnNazwisko;
    public TableColumn<Nieobecnosci, String> akceptacjaUsprawiedliwienColumnImie;
    public TableColumn<Nieobecnosci, String> akceptacjaUsprawiedliwienColumnTresc;

    //================================================================================
    // Dyrektor
    //================================================================================

    public Tab tabDodajRodzicow;
    public TextField dodajRodzicowInputImieOjca;
    public TextField dodajRodzicowInputNazwiskoOjca;
    public TextField dodajRodzicowInputImieMatki;
    public TextField dodajRodzicowInputNazwiskoMatki;
    public TextField dodajRodzicowInputLogin;
    public PasswordField dodajRodzicowInputHaslo;
    public PasswordField dodajRodzicowInputPowtorzHaslo;

    public Tab tabDodajUczniow;
    public TextField dodajUczniowInputImie;
    public TextField dodajUczniowInputNazwisko;
    public TextField dodajUczniowInputSzukaj;
    public TextField dodajUczniowInputLogin;
    public PasswordField dodajUczniowInputHaslo;
    public PasswordField dodajUczniowInputPowtorzHaslo;
    public ChoiceBox<Klasy> dodajUczniowChoiceBoxKlasa;
    public TableView<Rodzice> dodajUczniowTableView;
    public TableColumn<Rodzice, String> dodajUczniowColumnImieOjca;
    public TableColumn<Rodzice, String> dodajUczniowColumnNazwiskoOjca;
    public TableColumn<Rodzice, String> dodajUczniowColumnImieMatki;
    public TableColumn<Rodzice, String> dodajUczniowColumnNazwiskoMatki;

    public Tab tabDodajNauczycieli;
    public TextField dodajNauczycieliInputImie;
    public TextField dodajNauczycieliInputNazwisko;
    public TextField dodajNauczycieliInputLogin;
    public PasswordField dodajNauczycieliInputHaslo;
    public PasswordField dodajNauczycieliInputPowtorzHaslo;

    public Tab tabDodajPrzedmioty;
    public TextField dodajPrzedmiotyInputNazwaPrzedmioty;
    public TableView<Nauczyciele> dodajPrzedmiotyTableView;
    public TableColumn<Nauczyciele, String> dodajPrzedmiotyColumnNazwisko;
    public TableColumn<Nauczyciele, String> dodajPrzedmiotyColumnImie;

    public Tab tabPrzydzielPrzedmiotDoKlasy;
    public ChoiceBox<Klasy> przydzielPrzedmiotDoKlasyChoiceBoxKlasa;
    public TableView<Przedmioty> przydzielPrzedmiotDoKlasyTableView;
    public TableColumn<Przedmioty, String> przydzielPrzedmiotDoKlasyColumnNazwaPrzedmiotu;
    public TableColumn<Przedmioty, String> przydzielPrzedmiotDoKlasyColumnProwadzacy;

    //================================================================================
    // Private
    //================================================================================

    private Session session;
    private Tab currentTab;
    private Nauczyciele loggedNauczyciel;
    private Rodzice loggedRodzic;
    private Uczniowie loggedUczen;

    //    Function run when user logs on
    public void initialize() {
        hideElements();

        session = SessionController.getSession();

        loadLoggedUser();

        currentTab = tabPane.getSelectionModel().getSelectedItem();

        refresh();

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            currentTab = newTab;
            refresh();
        });

    }

    private void loadLoggedUser() {
        switch (authenticatedUser.getRoleByRolaId().getNazwaRoli()) {

            case "nauczyciel":  // Role Nauczyciel
                Query<Nauczyciele> query1 = session.createQuery("SELECT n FROM Nauczyciele n WHERE n.kontaByKontoId.kontoId=" + authenticatedUser.getKontoId(), Nauczyciele.class);
                loggedNauczyciel = FXCollections.observableArrayList(query1.list()).get(0);

                break;
            case "rodzic":  // Role Rodzic
                Query<Rodzice> query2 = session.createQuery("SELECT r FROM Rodzice r WHERE r.kontaByKontoId.kontoId=" + authenticatedUser.getKontoId(), Rodzice.class);
                loggedRodzic = FXCollections.observableArrayList(query2.list()).get(0);

                break;
            case "uczen":  // Role Uczen
                Query<Uczniowie> query3 = session.createQuery("SELECT u FROM Uczniowie u WHERE u.kontaByKontoId.kontoId=" + authenticatedUser.getKontoId(), Uczniowie.class);
                loggedUczen = FXCollections.observableArrayList(query3.list()).get(0);

                break;

        }
    }

    //    Function that load data from database to tableviews
    private void loadData(Tab tab) {

        //================================================================================
        // Uczeń
        //================================================================================

        if (tab.equals(tabOceny)) {

            Query<Oceny> query1 = session.createQuery("SELECT o FROM Oceny o WHERE o.uczniowieByUczenId.uczenId=" + loggedUczen.getUczenId(), Oceny.class);
            ObservableList<Oceny> listaOcen = FXCollections.observableArrayList(query1.list());

            ocenyColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
            ocenyColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("przedmiotyByPrzedmiotId"));
            ocenyColumnOcena.setCellValueFactory(new PropertyValueFactory<>("wartosc"));
            ocenyColumnZaCo.setCellValueFactory(new PropertyValueFactory<>("opis"));

            ocenyTableEdiary.setItems(listaOcen);

        } else if (tab.equals(tabNieobecnosci)) {

            Query<Nieobecnosci> query1 = session.createQuery("SELECT n FROM Nieobecnosci n WHERE n.uczniowieByUczenId.uczenId=" + loggedUczen.getUczenId(), Nieobecnosci.class);
            ObservableList<Nieobecnosci> listaNieobesnoci = FXCollections.observableArrayList(query1.list());

            nieobecnosciColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
            nieobecnosciColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("przedmiotyByPrzedmiotId"));
            nieobecnosciColumnStatus.setCellValueFactory(new PropertyValueFactory<>("wartosc"));

            nieobecnosciTableView.setItems(listaNieobesnoci);

        } else if (tab.equals(tabUwagi)) {

            Query<Uwagi> query1 = session.createQuery("SELECT u FROM Uwagi u WHERE u.uczniowieByUczenId.uczenId=" + loggedUczen.getUczenId(), Uwagi.class);
            ObservableList<Uwagi> listaUwag = FXCollections.observableArrayList(query1.list());

            uwagiColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
            uwagiColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("przedmiotyByPrzedmiotId"));
            uwagiColumnTresc.setCellValueFactory(new PropertyValueFactory<>("wartosc"));

            uwagiTableView.setItems(listaUwag);

        }

        //================================================================================
        // Rodzic
        //================================================================================

        else if (tab.equals(tabUsprawiedliwienia)) {

            Query<Nieobecnosci> query1 = session.createQuery("SELECT n FROM Nieobecnosci n", Nieobecnosci.class);
            ObservableList<Nieobecnosci> listaUsprawiedliwien = FXCollections.observableArrayList(query1.list());

            usprawiedliwieniaColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
            usprawiedliwieniaColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("przedmiotyByPrzedmiotId"));

            usprawiedliwieniaTableView.setItems(listaUsprawiedliwien);

        }

        //================================================================================
        // Nauczyciel
        //================================================================================

        else if (tab.equals(tabListaUczniow)) {

            if (listaUczniowChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                Query<Klasy> query1 = session.createQuery("SELECT k FROM Klasy k", Klasy.class);
                ObservableList<Klasy> listaKlas = FXCollections.observableArrayList(query1.list());

                listaUczniowChoiceBoxKlasa.setItems(listaKlas);
                listaUczniowChoiceBoxKlasa.getSelectionModel().select(0);
            }

            Query<Uczniowie> query2 = session.createQuery("SELECT u FROM Uczniowie u, Klasy k WHERE u.klasyByKlasaId = k and k.nazwaKlasy='" + listaUczniowChoiceBoxKlasa.getValue() + "'", Uczniowie.class);
            ObservableList<Uczniowie> listaUczniow = FXCollections.observableArrayList(query2.list());

            listaUczniowColumnKlasa.setCellValueFactory(new PropertyValueFactory<>("klasyByKlasaId"));
            listaUczniowColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            listaUczniowColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

            listaUczniowTableView.setItems(listaUczniow);

        } else if (tab.equals(tabDodawanieUwag)) {

            if (dodawanieUwagChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                Query<Klasy> query1 = session.createQuery("SELECT k FROM Klasy k", Klasy.class);
                ObservableList<Klasy> listaKlas = FXCollections.observableArrayList(query1.list());

                dodawanieUwagChoiceBoxKlasa.setItems(listaKlas);
                dodawanieUwagChoiceBoxKlasa.getSelectionModel().select(0);
            }

            Query<Przedmioty> query1 = session.createQuery("SELECT k.przedmiotyByPrzedmiotId FROM KlasyPrzedmioty k WHERE k.klasyByKlasaId.nazwaKlasy='" + dodawanieUwagChoiceBoxKlasa.getValue() + "'", Przedmioty.class);
            ObservableList<Przedmioty> listaPrzedmiotow = FXCollections.observableArrayList(query1.list());

            dodawanieUwagChoiceBoxPrzedmiot.setItems(listaPrzedmiotow);

            if (dodawanieUwagChoiceBoxPrzedmiot.getSelectionModel().isEmpty()) {
                dodawanieUwagChoiceBoxPrzedmiot.getSelectionModel().select(0);
            }

            Query<Uczniowie> query = session.createQuery("SELECT u FROM Uczniowie u WHERE u.klasyByKlasaId.nazwaKlasy='" + dodawanieUwagChoiceBoxKlasa.getValue() + "'", Uczniowie.class);
            ObservableList<Uczniowie> listaUczniow = FXCollections.observableArrayList(query.list());

            dodawanieUwagColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            dodawanieUwagColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

            dodawanieUwagTableView.setItems(listaUczniow);

        } else if (tab.equals(tabDodajOcene)) {

            if (dodawanieOcenChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                Query<Klasy> query1 = session.createQuery("SELECT k FROM Klasy k", Klasy.class);
                ObservableList<Klasy> listaKlas = FXCollections.observableArrayList(query1.list());

                dodawanieOcenChoiceBoxKlasa.setItems(listaKlas);
                dodawanieOcenChoiceBoxKlasa.getSelectionModel().select(0);
            }

            Query<Przedmioty> query1 = session.createQuery("SELECT k.przedmiotyByPrzedmiotId FROM KlasyPrzedmioty k WHERE k.klasyByKlasaId.nazwaKlasy='" + dodawanieOcenChoiceBoxKlasa.getValue() + "'", Przedmioty.class);
            ObservableList<Przedmioty> listaPrzedmiotow = FXCollections.observableArrayList(query1.list());

            dodawanieOcenChoiceBoxPrzedmiot.setItems(listaPrzedmiotow);

            if (dodawanieOcenChoiceBoxPrzedmiot.getSelectionModel().isEmpty()) {
                dodawanieOcenChoiceBoxPrzedmiot.getSelectionModel().select(0);
            }

            Query<Uczniowie> query = session.createQuery("SELECT u FROM Uczniowie u WHERE u.klasyByKlasaId.nazwaKlasy='" + dodawanieOcenChoiceBoxKlasa.getValue() + "'", Uczniowie.class);
            ObservableList<Uczniowie> listaUczniow = FXCollections.observableArrayList(query.list());

            dodawanieOcenColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            dodawanieOcenColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

            dodawanieOcenTableView.setItems(listaUczniow);

        } else if (tab.equals(tabWpisywanieNieobecnosci)) {

            if (dodawanieNieobecnosciChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                Query<Klasy> query1 = session.createQuery("SELECT k FROM Klasy k", Klasy.class);
                ObservableList<Klasy> listaKlas = FXCollections.observableArrayList(query1.list());

                dodawanieNieobecnosciChoiceBoxKlasa.setItems(listaKlas);
                dodawanieNieobecnosciChoiceBoxKlasa.getSelectionModel().select(0);
            }

            Query<Przedmioty> query1 = session.createQuery("SELECT k.przedmiotyByPrzedmiotId FROM KlasyPrzedmioty k WHERE k.klasyByKlasaId.nazwaKlasy='" + dodawanieNieobecnosciChoiceBoxKlasa.getValue() + "'", Przedmioty.class);
            ObservableList<Przedmioty> listaPrzedmiotow = FXCollections.observableArrayList(query1.list());

            dodawanieNieobecnosciChoiceBoxPrzedmiot.setItems(listaPrzedmiotow);

            if (dodawanieNieobecnosciChoiceBoxPrzedmiot.getSelectionModel().isEmpty()) {
                dodawanieNieobecnosciChoiceBoxPrzedmiot.getSelectionModel().select(0);
            }

            Query<Uczniowie> query = session.createQuery("SELECT u FROM Uczniowie u WHERE u.klasyByKlasaId.nazwaKlasy='" + dodawanieNieobecnosciChoiceBoxKlasa.getValue() + "'", Uczniowie.class);
            ObservableList<Uczniowie> listaUczniow = FXCollections.observableArrayList(query.list());

            wpisywanieNieobecnosciColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            wpisywanieNieobecnosciColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

            wpisywanieNieobecnosciTableView.setItems(listaUczniow);

        } else if (tab.equals(tabAkceptacjaUsprawiedliwien)) {

            if (akceptacjaUsprawiedliwienChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                Query<Klasy> query1 = session.createQuery("SELECT k FROM Klasy k", Klasy.class);
                ObservableList<Klasy> listaKlas = FXCollections.observableArrayList(query1.list());

                akceptacjaUsprawiedliwienChoiceBoxKlasa.setItems(listaKlas);
                akceptacjaUsprawiedliwienChoiceBoxKlasa.getSelectionModel().select(0);
            }

            Query<Nieobecnosci> query = session.createQuery("SELECT n FROM Nieobecnosci n, Uczniowie u, Klasy k WHERE n.trescUsprawiedliwienia not like '' and n.uczniowieByUczenId=u and u.klasyByKlasaId=k and k='" + akceptacjaUsprawiedliwienChoiceBoxKlasa.getValue() + "'", Nieobecnosci.class);
            ObservableList<Nieobecnosci> listaUsprawiedliwien = FXCollections.observableArrayList(query.list());

            akceptacjaUsprawiedliwienColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            akceptacjaUsprawiedliwienColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
            akceptacjaUsprawiedliwienColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
            akceptacjaUsprawiedliwienColumnTresc.setCellValueFactory(new PropertyValueFactory<>("trescUsprawiedliwienia"));

            akceptacjaUsprawiedliwienTableView.setItems(listaUsprawiedliwien);

        }

        //================================================================================
        // Dyrektor
        //================================================================================

        else if (tab.equals(tabDodajUczniow)) {

            Query<Klasy> query1 = session.createQuery("SELECT k FROM Klasy k", Klasy.class);
            ObservableList<Klasy> listaKlas = FXCollections.observableArrayList(query1.list());

            dodajUczniowChoiceBoxKlasa.setItems(listaKlas);
            dodajUczniowChoiceBoxKlasa.getSelectionModel().select(0);

            Query<Rodzice> query2 = session.createQuery("SELECT r FROM Rodzice r", Rodzice.class);
            ObservableList<Rodzice> listaRodzicow = FXCollections.observableArrayList(query2.list());

            dodajUczniowColumnImieOjca.setCellValueFactory(new PropertyValueFactory<>("imieOjca"));
            dodajUczniowColumnNazwiskoOjca.setCellValueFactory(new PropertyValueFactory<>("nazwiskoOjca"));
            dodajUczniowColumnImieMatki.setCellValueFactory(new PropertyValueFactory<>("imieMatki"));
            dodajUczniowColumnNazwiskoMatki.setCellValueFactory(new PropertyValueFactory<>("nazwiskoMatki"));

            dodajUczniowTableView.setItems(listaRodzicow);

        } else if (tab.equals(tabDodajPrzedmioty)) {

            Query<Nauczyciele> query1 = session.createQuery("SELECT n FROM Nauczyciele n", Nauczyciele.class);
            ObservableList<Nauczyciele> listaNauczycieli = FXCollections.observableArrayList(query1.list());

            dodajPrzedmiotyColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            dodajPrzedmiotyColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

            dodajPrzedmiotyTableView.setItems(listaNauczycieli);

        } else if (tab.equals(tabPrzydzielPrzedmiotDoKlasy)) {

            Query<Przedmioty> query1 = session.createQuery("SELECT p FROM Przedmioty p", Przedmioty.class);
            ObservableList<Przedmioty> listaPrzedmiotow = FXCollections.observableArrayList(query1.list());

            przydzielPrzedmiotDoKlasyColumnNazwaPrzedmiotu.setCellValueFactory(new PropertyValueFactory<>("nazwaPrzedmiotu"));
            przydzielPrzedmiotDoKlasyColumnProwadzacy.setCellValueFactory(new PropertyValueFactory<>("nauczycieleByNauczycielId"));

            przydzielPrzedmiotDoKlasyTableView.setItems(listaPrzedmiotow);

            if (przydzielPrzedmiotDoKlasyChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                Query<Klasy> query2 = session.createQuery("SELECT k FROM Klasy k", Klasy.class);
                ObservableList<Klasy> listaKlas = FXCollections.observableArrayList(query2.list());

                przydzielPrzedmiotDoKlasyChoiceBoxKlasa.setItems(listaKlas);
                przydzielPrzedmiotDoKlasyChoiceBoxKlasa.getSelectionModel().select(0);
            }
        }

    }

    private void saveData(Tab tab) {

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

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Pomyslnie dodano!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });

                session.close();
                session = SessionController.getSession();

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Podaj tresc!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
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

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Komunikat");
                    alert.setHeaderText(null);
                    alert.setContentText("Pomyślnie dodano uwage!");

                    ButtonType buttonTypeYes = new ButtonType("OK");

                    alert.getButtonTypes().setAll(buttonTypeYes);


                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == buttonTypeYes) {
                        }
                    });
                  
                    session.close();
                    session = SessionController.getSession();
                } else if (dodawanieUwagDatePickerData.getValue() == null){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Komunikat");
                    alert.setHeaderText(null);
                    alert.setContentText("Uzupelnij pole data!");

                    ButtonType buttonTypeYes = new ButtonType("OK");

                    alert.getButtonTypes().setAll(buttonTypeYes);

                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == buttonTypeYes) {
                        }
                    });
                } else if (dodawanieUwagTextFieldTresc.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Komunikat");
                    alert.setHeaderText(null);
                    alert.setContentText("Uzupelnij pole tresc!");

                    ButtonType buttonTypeYes = new ButtonType("OK");

                    alert.getButtonTypes().setAll(buttonTypeYes);

                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == buttonTypeYes) {
                        }
                    });
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Nie wybrano ucznia!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            }

        } else if (tab.equals(tabDodajOcene)) {

            if(dodawanieOcenTableView.getSelectionModel().getSelectedItem() != null) {

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

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Komunikat");
                    alert.setHeaderText(null);
                    alert.setContentText("Pomyślnie dodano ocene!");

                    ButtonType buttonTypeYes = new ButtonType("OK");

                    alert.getButtonTypes().setAll(buttonTypeYes);

                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == buttonTypeYes) {
                        }
                    });
                    session.close();
                    session = SessionController.getSession();
                } else if (dodawanieOcenInputStopien.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Komunikat");
                    alert.setHeaderText(null);
                    alert.setContentText("Nie podano stopnia!");

                    ButtonType buttonTypeYes = new ButtonType("OK");

                    alert.getButtonTypes().setAll(buttonTypeYes);

                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == buttonTypeYes) {
                        }
                    });
                } else if (dodawanieOcenInputZaco.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Komunikat");
                    alert.setHeaderText(null);
                    alert.setContentText("Nie podano opisu!");

                    ButtonType buttonTypeYes = new ButtonType("OK");

                    alert.getButtonTypes().setAll(buttonTypeYes);

                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == buttonTypeYes) {
                        }
                    });
                } else if (dodawanieOcenInputData.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Komunikat");
                    alert.setHeaderText(null);
                    alert.setContentText("Nie podano daty!");

                    ButtonType buttonTypeYes = new ButtonType("OK");

                    alert.getButtonTypes().setAll(buttonTypeYes);

                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == buttonTypeYes) {
                        }
                    });
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Nie wybrano ucznia!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
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

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Komunikat");
                    alert.setHeaderText(null);
                    alert.setContentText("Pomyślnie dodano nieobecnosc!");

                    ButtonType buttonTypeYes = new ButtonType("OK");

                    alert.getButtonTypes().setAll(buttonTypeYes);

                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == buttonTypeYes) {
                        }
                    });
                    session.close();
                    session = SessionController.getSession();
                } else {
                  
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Komunikat");
                    alert.setHeaderText(null);
                    alert.setContentText("Nie podano daty!");

                    ButtonType buttonTypeYes = new ButtonType("OK");

                    alert.getButtonTypes().setAll(buttonTypeYes);

                    alert.showAndWait().ifPresent(rs -> {
                        if (rs == buttonTypeYes) {
                        }
                    });
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Nie wybrano ucznia!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            }

        } else if (tab.equals(tabAkceptacjaUsprawiedliwien)) {

            if (akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem() != null) {

                Nieobecnosci nieobecnosc = akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem();

                nieobecnosc.setWartosc("usprawiedliwiona");

                session.beginTransaction();
                session.update(nieobecnosc);
                session.getTransaction().commit();

                session.close();
                session = SessionController.getSession();

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Nie wybrano ucznia!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            }

        }

        //================================================================================
        // Dyrektor
        //================================================================================

        else if (tab.equals(tabDodajRodzicow)) {

            if (!dodajRodzicowInputLogin.getText().isEmpty()
                    && !dodajRodzicowInputHaslo.getText().isEmpty()
                    && !dodajRodzicowInputImieOjca.getText().isEmpty()
                    && !dodajRodzicowInputNazwiskoOjca.getText().isEmpty()
                    && !dodajRodzicowInputImieMatki.getText().isEmpty()
                    && !dodajRodzicowInputNazwiskoMatki.getText().isEmpty()
            ) {
                Query<Role> query1 = session.createQuery("SELECT r FROM Role r WHERE r.nazwaRoli LIKE 'rodzic'", Role.class);
                Role rolaRodzic = FXCollections.observableArrayList(query1.list()).get(0);

                Konta noweKonto = new Konta();
                noweKonto.setLogin(dodajRodzicowInputLogin.getText());
                noweKonto.setHaslo(dodajRodzicowInputHaslo.getText());
                noweKonto.setRoleByRolaId(rolaRodzic);

                session.beginTransaction();
                session.save(noweKonto);
                session.getTransaction().commit();
                session.close();
                session = SessionController.getSession();

                Query<Konta> query2 = session.createQuery("SELECT k FROM Konta k WHERE k.login='" + dodajRodzicowInputLogin.getText() + "'", Konta.class);
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

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Pomyslnie dodano rodzica!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });

                session.close();
                session = SessionController.getSession();
            } else if (dodajRodzicowInputLogin.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Nie podano loginu!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            } else if (dodajRodzicowInputHaslo.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Nie podano hasla!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            } else if (dodajRodzicowInputImieOjca.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Nie podano imienia ojca!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            } else if (dodajRodzicowInputNazwiskoOjca.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Nie podano nazwiska ojca!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            } else if (dodajRodzicowInputImieMatki.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Nie podano imienia matki!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            } else if (dodajRodzicowInputNazwiskoMatki.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Nie podano nazwiska matki!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            }

        } else if (tab.equals(tabDodajUczniow)) {

            Query<Role> query1 = session.createQuery("SELECT r FROM Role r WHERE r.nazwaRoli LIKE 'uczen'", Role.class);
            Role rolaUczen = FXCollections.observableArrayList(query1.list()).get(0);

            Konta noweKonto = new Konta();
            if (!dodajUczniowInputLogin.getText().isEmpty() && !dodajUczniowInputHaslo.getText().isEmpty()) {
                noweKonto.setLogin(dodajUczniowInputLogin.getText());
                noweKonto.setHaslo(dodajUczniowInputHaslo.getText());
                noweKonto.setRoleByRolaId(rolaUczen);
            }

            session.beginTransaction();
            session.save(noweKonto);
            session.getTransaction().commit();
            session.close();
            session = SessionController.getSession();

            Query<Konta> query2 = session.createQuery("SELECT k FROM Konta k WHERE k.login='" + dodajUczniowInputLogin.getText() + "'", Konta.class);
            Konta konto = FXCollections.observableArrayList(query2.list()).get(0);

            Uczniowie nowyUczen = new Uczniowie();

            if (!dodajUczniowInputImie.getText().isEmpty() && !dodajUczniowInputNazwisko.getText().isEmpty() && dodajUczniowTableView.getSelectionModel().getSelectedItem() != null) {
                nowyUczen.setKontaByKontoId(konto);
                nowyUczen.setImie(dodajUczniowInputImie.getText());
                nowyUczen.setNazwisko(dodajUczniowInputNazwisko.getText());
                nowyUczen.setKlasyByKlasaId(dodajUczniowChoiceBoxKlasa.getValue());
                nowyUczen.setRodziceByRodzicId(dodajUczniowTableView.getSelectionModel().getSelectedItem());
                session.beginTransaction();
                session.save(nowyUczen);
                session.getTransaction().commit();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Pomyslnie dodano ucznia!");


                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
                session.close();
                session = SessionController.getSession();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Uzupelnij wszystkie dane!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            }
        }
        else if (tab.equals(tabDodajNauczycieli)) {

            if (!dodajNauczycieliInputLogin.getText().isEmpty() && !dodajNauczycieliInputHaslo.getText().isEmpty() && !dodajNauczycieliInputImie.getText().isEmpty() && !dodajNauczycieliInputNazwisko.getText().isEmpty()) {
                Query<Role> query1 = session.createQuery("SELECT r FROM Role r WHERE r.nazwaRoli LIKE 'nauczyciel'", Role.class);
                Role rolaNauczyciel = FXCollections.observableArrayList(query1.list()).get(0);

                Konta noweKonto = new Konta();
                noweKonto.setLogin(dodajNauczycieliInputLogin.getText());
                noweKonto.setHaslo(dodajNauczycieliInputHaslo.getText());
                noweKonto.setRoleByRolaId(rolaNauczyciel);

                session.beginTransaction();
                session.save(noweKonto);
                session.getTransaction().commit();
                session.close();
                session = SessionController.getSession();

                Query<Konta> query2 = session.createQuery("SELECT k FROM Konta k WHERE k.login='" + dodajNauczycieliInputLogin.getText() + "'", Konta.class);
                Konta konto = FXCollections.observableArrayList(query2.list()).get(0);

                Nauczyciele nowyNauczyciel = new Nauczyciele();

                nowyNauczyciel.setKontaByKontoId(konto);
                nowyNauczyciel.setImie(dodajNauczycieliInputImie.getText());
                nowyNauczyciel.setNazwisko(dodajNauczycieliInputNazwisko.getText());

                session.beginTransaction();
                session.save(nowyNauczyciel);
                session.getTransaction().commit();

                session.close();
                session = SessionController.getSession();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Uzupelnij wszystkie dane!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            }

        } else if (tab.equals(tabDodajPrzedmioty)) {

            if (dodajPrzedmiotyTableView.getSelectionModel().getSelectedItem() != null && !dodajPrzedmiotyInputNazwaPrzedmioty.getText().isEmpty()) {
                Przedmioty nowyPrzedmiot = new Przedmioty();
                nowyPrzedmiot.setNazwaPrzedmiotu(dodajPrzedmiotyInputNazwaPrzedmioty.getText());
                nowyPrzedmiot.setNauczycieleByNauczycielId(dodajPrzedmiotyTableView.getSelectionModel().getSelectedItem());

                session.beginTransaction();
                session.save(nowyPrzedmiot);
                session.getTransaction().commit();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Pomyslnie dodano przedmiot!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
                session.close();
                session = SessionController.getSession();
            } else if (dodajPrzedmiotyTableView.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Wybierz nauczyciela!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            } else if (dodajPrzedmiotyInputNazwaPrzedmioty.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Podaj przedmiot!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            }

        } else if (tab.equals(tabPrzydzielPrzedmiotDoKlasy)) {

            if (przydzielPrzedmiotDoKlasyTableView.getSelectionModel().getSelectedItem() != null) {
                KlasyPrzedmioty klasyPrzedmioty = new KlasyPrzedmioty();
                klasyPrzedmioty.setKlasyByKlasaId(przydzielPrzedmiotDoKlasyChoiceBoxKlasa.getValue());
                klasyPrzedmioty.setPrzedmiotyByPrzedmiotId(przydzielPrzedmiotDoKlasyTableView.getSelectionModel().getSelectedItem());

                session.beginTransaction();
                session.save(klasyPrzedmioty);
                session.getTransaction().commit();
                session.close();
                session = SessionController.getSession();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Komunikat");
                alert.setHeaderText(null);
                alert.setContentText("Wybierz nauczyciela!");

                ButtonType buttonTypeYes = new ButtonType("OK");

                alert.getButtonTypes().setAll(buttonTypeYes);

                alert.showAndWait().ifPresent(rs -> {
                    if (rs == buttonTypeYes) {
                    }
                });
            }

        }

    }

    //    Function that removes tabs id user has no privileges to see them
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


    private void hideElementsDyrektor() {

        tabPane.getTabs().remove(tabListaUczniow);
        tabPane.getTabs().remove(tabDodajRodzicow);
        tabPane.getTabs().remove(tabDodajUczniow);
        tabPane.getTabs().remove(tabDodajNauczycieli);
        tabPane.getTabs().remove(tabDodajPrzedmioty);
        tabPane.getTabs().remove(tabPrzydzielPrzedmiotDoKlasy);

    }

    private void hideElementsNauczyciel() {

        tabPane.getTabs().remove(tabDodawanieUwag);
        tabPane.getTabs().remove(tabDodajOcene);
        tabPane.getTabs().remove(tabWpisywanieNieobecnosci);
        tabPane.getTabs().remove(tabAkceptacjaUsprawiedliwien);

    }

    private void hideElementsRodzic() {

        tabPane.getTabs().remove(tabUsprawiedliwienia);
      
    }

    private void hideElementsUczen() {

        tabPane.getTabs().remove(tabOceny);
        tabPane.getTabs().remove(tabNieobecnosci);
        tabPane.getTabs().remove(tabUwagi);
    }

    public void generateGrades(ActionEvent actionEvent) throws IOException, DocumentException {
        Query<Oceny> query1 = session.createQuery("SELECT o FROM Oceny o", Oceny.class);
        ObservableList<Oceny> listaOcen = FXCollections.observableArrayList(query1.list());

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
                    new PdfGenerator().PDFGenerateGrades(listaOcen);
                } catch (IOException | DocumentException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //    Handle refresh button action
    public void refreshActionHandle(ActionEvent actionEvent) {
        refresh();
    }

    //    Function that refresh actually selected tab
    private void refresh() {
        loadData(currentTab);
    }

    public void saveData(ActionEvent actionEvent) {
        saveData(currentTab);
    }

    public void deleteData(ActionEvent actionEvent) {

        if (currentTab.equals(tabAkceptacjaUsprawiedliwien)) {

            if (akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem() != null) {

                Nieobecnosci nieobecnosc = akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem();

                nieobecnosc.setWartosc("nieusprawiedliwiona");

                session.beginTransaction();
                session.update(nieobecnosc);
                session.getTransaction().commit();

                session.close();
                session = SessionController.getSession();

            }

        }

    }

}

