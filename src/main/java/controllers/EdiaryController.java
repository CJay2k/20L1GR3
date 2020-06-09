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

    public GridPane tabOcenyGridPaneDziecko;
    public GridPane tabNieobecnosciGridPaneDziecko;
    public GridPane tabUwagiGridPaneDziecko;
    public GridPane tabUsprawiedliwieniaGridPaneDziecko;

    public ChoiceBox<Uczniowie> ocenyChoiceBoxDziecko;
    public ChoiceBox<Uczniowie> nieobecnosciChoiceBoxDziecko;
    public ChoiceBox<Uczniowie> uwagiChoiceBoxDziecko;
    public ChoiceBox<Uczniowie> usprawiedliwieniaChoiceBoxDziecko;

    //================================================================================
    // Nauczyciel
    //================================================================================

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
    public TableColumn<Nieobecnosci, String> akceptacjaUsprawiedliwienColumnPrzedmiot;
    public TableColumn<Nieobecnosci, String> akceptacjaUsprawiedliwienColumnTresc;

    //================================================================================
    // Dyrektor
    //================================================================================

    public Tab tabListaUczniow;
    public ChoiceBox<Klasy> listaUczniowChoiceBoxKlasa;
    public TableView<Uczniowie> listaUczniowTableView;
    public TableColumn<Uczniowie, String> listaUczniowColumnKlasa;
    public TableColumn<Uczniowie, String> listaUczniowColumnNazwisko;
    public TableColumn<Uczniowie, String> listaUczniowColumnImie;

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

    private Tab currentTab;
    private Nauczyciele loggedNauczyciel;
    private Rodzice loggedRodzic;
    private Uczniowie loggedUczen;

    //    Function run when user logs on
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

    private void loadUczniowieToTableView(ChoiceBox<Klasy> choiceBoxKlasa, TableColumn<Uczniowie, String> columnNazwisko, TableColumn<Uczniowie, String> columnImie, TableView<Uczniowie> tableView) {

        try (Session session = SessionController.getSession()) {

            Query<Uczniowie> query = session.createQuery("FROM Uczniowie u WHERE u.klasyByKlasaId.nazwaKlasy='" + choiceBoxKlasa.getValue() + "'", Uczniowie.class);

            columnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            columnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

            tableView.setItems(FXCollections.observableArrayList(query.list()));
        }
    }

    private void loadPrzedmiotToChoiceBox(ChoiceBox<Przedmioty> cb) {

        try (Session session = SessionController.getSession()) {

            Query<Przedmioty> query = session.createQuery("SELECT kp.przedmiotyByPrzedmiotId FROM KlasyPrzedmioty kp WHERE kp.przedmiotyByPrzedmiotId.nauczycieleByNauczycielId.id=" + loggedNauczyciel.getNauczycielId() + " AND kp.klasyByKlasaId.nazwaKlasy='" + dodawanieUwagChoiceBoxKlasa.getValue() + "'", Przedmioty.class);

            cb.setItems(FXCollections.observableArrayList(query.list()));

            if (cb.getSelectionModel().isEmpty()) {
                cb.getSelectionModel().select(0);
            }
        }
    }

    private void loadKlasaToChoiceBox(ChoiceBox<Klasy> cb) {
        try (Session session = SessionController.getSession()) {
            if (cb.getSelectionModel().isEmpty()) {

                Query<Klasy> query = session.createQuery("SELECT kp.klasyByKlasaId FROM KlasyPrzedmioty kp WHERE kp.przedmiotyByPrzedmiotId.nauczycieleByNauczycielId.id=" + loggedNauczyciel.getNauczycielId(), Klasy.class);

                cb.setItems(FXCollections.observableArrayList(query.list()));
                cb.getSelectionModel().select(0);
            }
        }
    }

    private void loadDzieciToChoiceBox(ChoiceBox<Uczniowie> cb) {

        try (Session session = SessionController.getSession()) {
            if (cb.getSelectionModel().isEmpty()) {
                Query<Uczniowie> query1 = session.createQuery("FROM Uczniowie u WHERE u.rodziceByRodzicId.id=" + loggedRodzic.getRodzicId(), Uczniowie.class);

                cb.setItems(FXCollections.observableArrayList(query1.list()));
                cb.getSelectionModel().select(0);
            }
        }

    }

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

    private void showAlert(Alert.AlertType warning, String content) {
        Alert alert = new Alert(warning);
        alert.setTitle("Komunikat");
        alert.setHeaderText(null);
        alert.setContentText(content);

        ButtonType buttonTypeYes = new ButtonType("OK");

        alert.getButtonTypes().setAll(buttonTypeYes);

        alert.showAndWait();
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
        tabOcenyGridPaneDziecko.setManaged(false);
        tabNieobecnosciGridPaneDziecko.setManaged(false);
        tabUwagiGridPaneDziecko.setManaged(false);
        tabUsprawiedliwieniaGridPaneDziecko.setManaged(false);

    }

    private void hideElementsUczen() {

        tabPane.getTabs().remove(tabOceny);
        tabPane.getTabs().remove(tabNieobecnosci);
        tabPane.getTabs().remove(tabUwagi);
    }

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
                    } catch (IOException | DocumentException e) {
                        e.printStackTrace();
                    }

                    showAlert(Alert.AlertType.CONFIRMATION, "PDF został zapisany w folderze z aplikacją.");
                }
            });

        }

    }

    //    Handle refresh button action
    public void refreshActionHandle() {
        loadData(currentTab);
    }

    public void saveData() {
        saveData(currentTab);
        loadData(currentTab);
    }

    public void clearButtonActionHandle() {
        clear(currentTab);
    }

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

    public void searchButtonActionHandle() {

        try (Session session = SessionController.getSession()) {
            Query<Rodzice> query = session.createQuery("FROM Rodzice r WHERE r.imieOjca LIKE '%" + dodajUczniowInputSzukaj.getText() + "%' OR r.nazwiskoOjca LIKE '%" + dodajUczniowInputSzukaj.getText() + "%' OR r.imieMatki LIKE '%" + dodajUczniowInputSzukaj.getText() + "%' OR r.nazwiskoMatki LIKE '%" + dodajUczniowInputSzukaj.getText() + "%'", Rodzice.class);

            dodajUczniowTableView.setItems(FXCollections.observableArrayList(query.list()));
        }
    }
}

