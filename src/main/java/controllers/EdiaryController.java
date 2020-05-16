package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mapping.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Date;
import java.sql.Time;


import static controllers.LoginController.loggedUserRole;

// Class handling Ediary Window actions
public class EdiaryController {

    public TabPane tabPane;
    public Tab tabOceny;
    public Tab tabNieobecnosci;
    public Tab tabUwagi;
    public Tab tabUsprawiedliwienia;
    public Tab tabListaUczniow;
    public Tab tabDodawanieUwag;
    public Tab tabDodajOcene;
    public Tab tabWpisywanieNieobecnosci;
    public Tab tabAkceptacjaUsprawiedliwien;
    public Tab tabDodajUczniow;
    public Tab tabDodajNauczycieli;
    public Tab tabDodajPrzedmiot;
    public Tab tabPrzydzielNauczycielaDoPrzedmiotu;
    public Tab tabPrzydzielPrzedmiotDoKlasy;
    public TableView<Uczniowie> listaUczniowTableView;
    public TableColumn<Uczniowie, String> listaUczniowColumnKlasa;
    public TableColumn<Uczniowie, String> listaUczniowColumnNazwisko;
    public TableColumn<Uczniowie, String> listaUczniowColumnImie;
    public TableView<Uczniowie> dodawanieUwagTableView;
    public TableColumn<Uczniowie, String> dodawanieUwagColumnNazwisko;
    public TableColumn<Uczniowie, String> dodawanieUwagColumnImie;

    public TableView<Uczniowie> dodawanieOcenTableView;
    public TableColumn<Uczniowie, String> dodawanieOcenColumnNazwisko;
    public TableColumn<Uczniowie, String> dodawanieOcenColumnImie;

    // Dodawanie ocen
    public DatePicker dodawanieOcenInputData;
    public TextField dodawanieOcenInputGodzina;
    public TextArea dodawanieOcenInputGodzinaZaco;
    public Button dodawanieOcenButtonDodaj;
    public ChoiceBox<Przedmioty> dodawanieOcenChoiceBoxPrzedmiot;
    public ChoiceBox<Klasy> dodawanieOcenChoiceBoxKlasa;
    public TextField dodawanieOcenInputStopien;

    // Wpisywanie nieobecnosci
    public TableView<Uczniowie> wpisywanieNieobecnosciTableView;
    public TableColumn<Uczniowie, String> wpisywanieNieobecnosciColumnNazwisko;
    public TableColumn<Uczniowie, String> wpisywanieNieobecnosciColumnImie;
    public ChoiceBox<Przedmioty> dodawanieNieobecnosciChoiceBoxPrzedmiot;
    public ChoiceBox<Klasy> dodawanieNieobecnosciChoiceBoxKlasa;
    public DatePicker wpisywanieNieobecnosciInputData;
    public TextField wpisywanieNieobecnosciInputGodzina;

    // Akceptacja usprawiedliwien
    public TableView<Nieobecnosci> akceptacjaUsprawiedliwienTableView;
    public TableColumn<Nieobecnosci, String> akceptacjaUsprawiedliwienColumnNazwisko;
    public TableColumn<Nieobecnosci, String> akceptacjaUsprawiedliwienColumnImie;
    public TableColumn<Nieobecnosci, Date> akceptacjaUsprawiedliwienColumnData;
    public TableColumn<Nieobecnosci, Time> akceptacjaUsprawiedliwienColumnGodzina;
    public TableColumn<Nieobecnosci, String> akceptacjaUsprawiedliwienColumnTresc;
    public ChoiceBox<Przedmioty> akceptacjaUsprawiedliwienChoiceBoxPrzedmiot;
    public ChoiceBox<Klasy> akceptacjaUsprawiedliwienChoiceBoxKlasa;
    public TextArea akceptacjaUsprawiedliwienTextAreaTresc;

    public ChoiceBox<Klasy> listaUczniowChoiceBoxKlasa;
    public ChoiceBox<Przedmioty> dodawanieUwagChoiceBoxPrzedmiot;
    public ChoiceBox<Klasy> dodawanieUwagChoiceBoxKlasa;

    public TableView<Oceny> ocenyTableEdiary;

    public TableColumn<Oceny, Date> ocenyColumnData;
    public TableColumn<Oceny, String> ocenyColumnPrzedmiot;
    public TableColumn<Oceny, String> ocenyColumnOcena;
    public TableColumn<Oceny, String> ocenyColumnZaCo;

    public TableView<Nauczyciele> przydzielNauczycielaTableView;
    public TableColumn<Nauczyciele, String> przydzielNauczycielaColumnNazwisko;
    public TableColumn<Nauczyciele, String> przydzielNauczycielaColumnImie;
    public ChoiceBox<Przedmioty> przydzielNauczycielaChoiceBoxPrzedmiot;

    public TableView<Przedmioty> przydzielPrzedmiotDoKlasyTableView;
    public TableColumn<Przedmioty, String> przydzielPrzedmiotDoKlasyColumnNazwaPrzedmiotu;
    public TableColumn<Przedmioty, String> przydzielPrzedmiotDoKlasyColumnProwadzacy;
    public ChoiceBox<Klasy> przydzielPrzedmiotDoKlasyChoiceBoxPrzedmiot;
    public ChoiceBox<Klasy> przydzielPrzedmiotDoKlasyChoiceBoxKlasa;

    public TableView<Nieobecnosci> usprawiedliwieniaTableView;
    public TableColumn<Nieobecnosci, Date> usprawiedliwieniaColumnData;
    public TableColumn<Nieobecnosci, Time> usprawiedliwieniaColumnGodzina;
    public TableColumn<Nieobecnosci, String> usprawiedliwieniaColumnPrzedmiot;
    public TableColumn<Nieobecnosci, String> usprawiedliwieniaColumnTresc;
    public TextArea usprawiedliwieniaTextAreaTresc;

    /*TODO*/
    public TableColumn<Uwagi, String> uwagiColumnTresc;
    public TableColumn<Uwagi, String> uwagiColumnPrzedmiot;
    public TableColumn<Uwagi, Date> uwagiColumnData;
    public TableView<Uwagi> uwagiTableView;

    // Nieobecnosci
    public TableView<Nieobecnosci> nieobecnosciTableView;
    public TableColumn<Nieobecnosci, Date> nieobecnosciColumnData;
    public TableColumn<Nieobecnosci, Time> nieobecnosciColumnGodzina;
    public TableColumn<Nieobecnosci, String> nieobecnosciColumnPrzedmiot;
    public TableColumn<Nieobecnosci, String> nieobecnosciColumnStatus;

    // Dodawanie uczniów
    public TextField dodajUczniowTextFieldImie;
    public TextField dodajUczniowTextFieldNazwisko;
    public TextField dodajUczniowTextFieldPesel;
    public ChoiceBox<Klasy> dodajUczniowChoiceBox;

    // Dodaj usun przedmiot
    public ChoiceBox dodajPrzedmiotChoiceBox;
    public Tab tabDodajRodzicow;

    public TableView<Rodzice> dodajUczniowTableView;
    public TableColumn<Rodzice, String> dodajUczniowColumnImieOjca;
    public TableColumn<Rodzice, String> dodajUczniowColumnNazwiskoOjca;
    public TableColumn<Rodzice, String> dodajUczniowColumnImieMatki;
    public TableColumn<Rodzice, String> dodajUczniowColumnNazwiskoMatki;

    public TableView<Przedmioty> dodajPrzedmiotyTableView;
    public TableColumn<Przedmioty, String> dodajPrzedmiotyColumnNazwaPrzedmiotu;

    private Tab currentTab;

    Session session;

    //    Function run when user logs on
    public void initialize() {
        hideElements();
        session = SessionController.getSession();
        currentTab = tabPane.getSelectionModel().getSelectedItem();
        refresh();

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            currentTab = newTab;
            refresh();
        });
    }

    //    Function that load data from database to tableviews
    private void loadData(Tab tab) {
        //================================================================================
        // Uczeń
        //================================================================================

        if (tab.equals(tabOceny)) {

            Query<Oceny> query1 = session.createQuery("SELECT o FROM Oceny o", Oceny.class);
            ObservableList<Oceny> listaOcen = FXCollections.observableArrayList(query1.list());

            ocenyColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
            ocenyColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("przedmiotyByPrzedmiotId"));
            ocenyColumnOcena.setCellValueFactory(new PropertyValueFactory<>("wartosc"));
            ocenyColumnZaCo.setCellValueFactory(new PropertyValueFactory<>("opis"));

            ocenyTableEdiary.setItems(listaOcen);

        }else if (tab.equals(tabNieobecnosci)) {

            Query<Nieobecnosci> query1 = session.createQuery("SELECT n FROM Nieobecnosci n", Nieobecnosci.class);
            ObservableList<Nieobecnosci> listaNieobesnoci = FXCollections.observableArrayList(query1.list());

            nieobecnosciColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
            nieobecnosciColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("przedmiotyByPrzedmiotId"));
            nieobecnosciColumnStatus.setCellValueFactory(new PropertyValueFactory<>("wartosc"));

            nieobecnosciTableView.setItems(listaNieobesnoci);

        }else if (tab.equals(tabUwagi)) {

            Query<Uwagi> query1 = session.createQuery("SELECT u FROM Uwagi u", Uwagi.class);
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

            Query<Przedmioty> query1 = session.createQuery("SELECT k.przedmiotyByPrzedmiotId FROM KlasyPrzedmioty k WHERE k.klasyByKlasaId.nazwaKlasy='" + akceptacjaUsprawiedliwienChoiceBoxKlasa.getValue() + "'", Przedmioty.class);
            ObservableList<Przedmioty> listaPrzedmiotow = FXCollections.observableArrayList(query1.list());

            akceptacjaUsprawiedliwienChoiceBoxPrzedmiot.setItems(listaPrzedmiotow);

            if (akceptacjaUsprawiedliwienChoiceBoxPrzedmiot.getSelectionModel().isEmpty()) {
                akceptacjaUsprawiedliwienChoiceBoxPrzedmiot.getSelectionModel().select(0);
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

            dodajUczniowChoiceBox.setItems(listaKlas);
            dodajUczniowChoiceBox.getSelectionModel().select(0);

            Query<Rodzice> query2 = session.createQuery("SELECT r FROM Rodzice r", Rodzice.class);
            ObservableList<Rodzice> listaRodzicow = FXCollections.observableArrayList(query2.list());

            dodajUczniowColumnImieOjca.setCellValueFactory(new PropertyValueFactory<>("imieOjca"));
            dodajUczniowColumnNazwiskoOjca.setCellValueFactory(new PropertyValueFactory<>("nazwiskoOjca"));
            dodajUczniowColumnImieMatki.setCellValueFactory(new PropertyValueFactory<>("imieMatki"));
            dodajUczniowColumnNazwiskoMatki.setCellValueFactory(new PropertyValueFactory<>("nazwiskoMatki"));

            dodajUczniowTableView.setItems(listaRodzicow);

        } else if (tab.equals(tabDodajPrzedmiot)) {

            Query<Przedmioty> query = session.createQuery("SELECT p FROM Przedmioty p", Przedmioty.class);
            ObservableList<Przedmioty> listaPrzedmiotow = FXCollections.observableArrayList(query.list());

            dodajPrzedmiotyColumnNazwaPrzedmiotu.setCellValueFactory(new PropertyValueFactory<>("nazwaPrzedmiotu"));

            dodajPrzedmiotyTableView.setItems(listaPrzedmiotow);

        } else if (tab.equals(tabPrzydzielNauczycielaDoPrzedmiotu)) {

            Query<Nauczyciele> query1 = session.createQuery("SELECT n FROM Nauczyciele n", Nauczyciele.class);
            ObservableList<Nauczyciele> listaNauczycieli = FXCollections.observableArrayList(query1.list());

            przydzielNauczycielaColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            przydzielNauczycielaColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

            przydzielNauczycielaTableView.setItems(listaNauczycieli);

            Query<Przedmioty> query2 = session.createQuery("SELECT p FROM Przedmioty p", Przedmioty.class);
            ObservableList<Przedmioty> listaPrzedmiotow = FXCollections.observableArrayList(query2.list());

            przydzielNauczycielaChoiceBoxPrzedmiot.setItems(listaPrzedmiotow);

            if (przydzielNauczycielaChoiceBoxPrzedmiot.getSelectionModel().isEmpty()) {
                przydzielNauczycielaChoiceBoxPrzedmiot.getSelectionModel().select(0);
            }


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

    private void saveData(Tab tab){
//        if (tab.equals(tabDodajOcene)) {
//            if(dodawanieOcenTableView.getSelectionModel().getSelectedItem() != null){
//                Uczniowie uczen = dodawanieOcenTableView.getSelectionModel().getSelectedItem();
//
//                if(!dodawanieOcenInputStopien.getText().isEmpty() && !dodawanieOcenInputData.getValue().toString().isEmpty() && !dodawanieOcenInputGodzinaZaco.getText().isEmpty()){
//                    Query query1 = session.createQuery("SELECT p FROM Przedmiot p WHERE p.nazwaPrzedmiotu='" + dodawanieOcenChoiceBoxPrzedmiot.getSelectionModel().getSelectedItem() + "'");
//                    ObservableList<Przedmioty> p = FXCollections.observableArrayList(query1.list());
//
//                    Przedmioty przedmiot = p.get(0);
//
//                    session.beginTransaction();
//                    Oceny ocena = new Oceny(przedmiot, uczen, dodawanieOcenInputStopien.getText(), Date.valueOf(dodawanieOcenInputData.getValue()), dodawanieOcenInputGodzinaZaco.getText());
//                    session.save(ocena);
//                    session.getTransaction().commit();
//
//                    session.close();
//                    session = SessionController.getSession();
//                }
//            }
//
//        }else if (tab.equals(tabWpisywanieNieobecnosci)) {
//            if(wpisywanieNieobecnosciTableView.getSelectionModel().getSelectedItem() != null){
//                Uczen uczen = wpisywanieNieobecnosciTableView.getSelectionModel().getSelectedItem();
//
//                if(!wpisywanieNieobecnosciInputGodzina.getText().isEmpty() && !wpisywanieNieobecnosciInputData.getValue().toString().isEmpty()){
//                    Query query1 = session.createQuery("SELECT p FROM Przedmiot p WHERE p.nazwaPrzedmiotu='" + dodawanieNieobecnosciChoiceBoxPrzedmiot.getSelectionModel().getSelectedItem() + "'");
//                    ObservableList<Przedmioty> p = FXCollections.observableArrayList(query1.list());
//
//                    Przedmioty przedmiot = p.get(0);
//
//                    session.beginTransaction();
//                    Nieobecnosci obecnosc = new Nieobecnosci(przedmiot, uczen, Date.valueOf(wpisywanieNieobecnosciInputData.getValue()), Time.valueOf(wpisywanieNieobecnosciInputGodzina.getText()), "0");
//                    session.save(obecnosc);
//                    session.getTransaction().commit();
//
//                    session.close();
//                    session = SessionController.getSession();
//                }
//            }
//
//        }else if (tab.equals(tabAkceptacjaUsprawiedliwien)) {
//            if(akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem() != null){
//
//                Nieobecnosci usprawiedliwienia = akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem();
//                Nieobecnosci obecnosc = usprawiedliwienia.getWartosc();
//                obecnosc.setWartosc("1");
//
//                session.beginTransaction();
//                session.update(obecnosc);
//                session.getTransaction().commit();
//
//                session.close();
//                session = SessionController.getSession();
//            }
//        }else if (tab.equals(tabUsprawiedliwienia)){
//            if(usprawiedliwieniaTableView.getSelectionModel().getSelectedItem() != null) {
//                Nieobecnosci nieobecnosci = usprawiedliwieniaTableView.getSelectionModel().getSelectedItem();
//
//                nieobecnosci.setWartosc(usprawiedliwieniaTextAreaTresc.getText());
//
//                session.beginTransaction();
//                session.update(nieobecnosci);
//                session.getTransaction().commit();
//
//                session.close();
//                session = SessionController.getSession();
//
//            }
//        }else if (tab.equals(tabDodajUczniow)) {
//            Query query1 = session.createQuery("SELECT k FROM Klasa k WHERE k.nazwaKlasy='" + dodajUczniowChoiceBox.getSelectionModel().getSelectedItem() + "'");
//            ObservableList<Klasy> k = FXCollections.observableArrayList(query1.list());
//            Klasy klasa = k.get(0);
//            //Autoryzacja autoryzacja = new Autoryzacja(Long.parseLong(dodajUczniowTextFieldPesel.getText()), dodajUczniowTextFieldPesel.getText(), dodajUczniowTextFieldPesel.getText(), 3);
//
////            Uczniowie uczen = new Uczniowie(klasy, Long.parseLong(dodajUczniowTextFieldPesel.getText()), dodajUczniowTextFieldImie.getText(), dodajUczniowTextFieldNazwisko.getText());
////
////            session.beginTransaction();
////            session.save(uczen);
////            session.getTransaction().commit();
////
////            session.close();
////            session = SessionController.getSession();
//        }

    }

    //    Function that removes tabs id user has no privileges to see them
    private void hideElements() {
        if (loggedUserRole == 1) { // Role Dyrektor
            tabPane.getTabs().remove(tabOceny);
            tabPane.getTabs().remove(tabNieobecnosci);
            tabPane.getTabs().remove(tabUwagi);
            tabPane.getTabs().remove(tabUsprawiedliwienia);
            tabPane.getTabs().remove(tabDodawanieUwag);
            tabPane.getTabs().remove(tabDodajOcene);
            tabPane.getTabs().remove(tabWpisywanieNieobecnosci);
            tabPane.getTabs().remove(tabAkceptacjaUsprawiedliwien);

        } else if (loggedUserRole == 2) { // Role Nauczyciel
            tabPane.getTabs().remove(tabOceny);
            tabPane.getTabs().remove(tabNieobecnosci);
            tabPane.getTabs().remove(tabUwagi);
            tabPane.getTabs().remove(tabUsprawiedliwienia);
            tabPane.getTabs().remove(tabDodajUczniow);
            tabPane.getTabs().remove(tabDodajNauczycieli);
            tabPane.getTabs().remove(tabDodajPrzedmiot);
            tabPane.getTabs().remove(tabPrzydzielPrzedmiotDoKlasy);
            tabPane.getTabs().remove(tabPrzydzielNauczycielaDoPrzedmiotu);

        } else if (loggedUserRole == 3) { // Role Rodzic
            tabPane.getTabs().remove(tabListaUczniow);
            tabPane.getTabs().remove(tabDodawanieUwag);
            tabPane.getTabs().remove(tabDodajOcene);
            tabPane.getTabs().remove(tabWpisywanieNieobecnosci);
            tabPane.getTabs().remove(tabAkceptacjaUsprawiedliwien);
            tabPane.getTabs().remove(tabDodajUczniow);
            tabPane.getTabs().remove(tabDodajNauczycieli);
            tabPane.getTabs().remove(tabDodajPrzedmiot);
            tabPane.getTabs().remove(tabPrzydzielPrzedmiotDoKlasy);
            tabPane.getTabs().remove(tabPrzydzielNauczycielaDoPrzedmiotu);

        } else if (loggedUserRole == 4) { // Role Uczen
            tabPane.getTabs().remove(tabUsprawiedliwienia);
            tabPane.getTabs().remove(tabListaUczniow);
            tabPane.getTabs().remove(tabDodawanieUwag);
            tabPane.getTabs().remove(tabDodajOcene);
            tabPane.getTabs().remove(tabWpisywanieNieobecnosci);
            tabPane.getTabs().remove(tabAkceptacjaUsprawiedliwien);
            tabPane.getTabs().remove(tabDodajUczniow);
            tabPane.getTabs().remove(tabDodajNauczycieli);
            tabPane.getTabs().remove(tabDodajPrzedmiot);
            tabPane.getTabs().remove(tabPrzydzielPrzedmiotDoKlasy);
            tabPane.getTabs().remove(tabPrzydzielNauczycielaDoPrzedmiotu);
        }
    }

    //    Handle refresh button action
    public void refreshActionHandle(ActionEvent actionEvent) {
        refresh();
    }

    //    Function that refresh actually selected tab
    private void refresh() {
        if (currentTab.equals(tabListaUczniow)) {
            loadData(tabListaUczniow);
        } else if (currentTab.equals(tabDodawanieUwag)) {
            loadData(tabDodawanieUwag);
        } else if (currentTab.equals(tabDodajOcene)) {
            loadData(tabDodajOcene);
        } else if (currentTab.equals(tabWpisywanieNieobecnosci)) {
            loadData(tabWpisywanieNieobecnosci);
        } else if (currentTab.equals(tabAkceptacjaUsprawiedliwien)) {
            loadData(tabAkceptacjaUsprawiedliwien);
        } else if (currentTab.equals(tabOceny)) {
            loadData(tabOceny);
        } else if (currentTab.equals(tabPrzydzielNauczycielaDoPrzedmiotu)) {
            loadData(tabPrzydzielNauczycielaDoPrzedmiotu);
        } else if (currentTab.equals(tabPrzydzielPrzedmiotDoKlasy)) {
            loadData(tabPrzydzielPrzedmiotDoKlasy);
        } else if (currentTab.equals(tabUsprawiedliwienia)) {
            loadData(tabUsprawiedliwienia);
        } else if (currentTab.equals(tabNieobecnosci)) {
            loadData(tabNieobecnosci);
        } else if (currentTab.equals(tabDodajUczniow)) {
            loadData(tabDodajUczniow);
        }else if (currentTab.equals(tabUwagi)) {
            loadData(tabUwagi);
        }else if (currentTab.equals(tabDodajPrzedmiot)) {
            loadData(tabDodajPrzedmiot);
        }
    }

    public void saveData(ActionEvent actionEvent) {
//        if (currentTab.equals(tabDodajOcene)) {
//            saveData(tabDodajOcene);
//        } else if (currentTab.equals(tabWpisywanieNieobecnosci)) {
//            saveData(tabWpisywanieNieobecnosci);
//        } else if (currentTab.equals(tabAkceptacjaUsprawiedliwien)) {
//            saveData(tabAkceptacjaUsprawiedliwien);
//        } else if (currentTab.equals(tabOceny)) {
//            saveData(tabOceny);
//        } else if (currentTab.equals(tabPrzydzielNauczycielaDoPrzedmiotu)) {
//            saveData(tabPrzydzielNauczycielaDoPrzedmiotu);
//        } else if (currentTab.equals(tabPrzydzielPrzedmiotDoKlasy)) {
//            saveData(tabPrzydzielPrzedmiotDoKlasy);
//        } else if (currentTab.equals(tabUsprawiedliwienia)) {
//            saveData(tabUsprawiedliwienia);
//        } else if (currentTab.equals(tabNieobecnosci)) {
//            saveData(tabNieobecnosci);
//        } else if (currentTab.equals(tabDodajUczniow)) {
//            saveData(tabDodajUczniow);
//        }
    }

    public void deleteData(ActionEvent actionEvent){
//        if (currentTab.equals(tabAkceptacjaUsprawiedliwien)) {
//            if(akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem() != null){
//
//                Usprawiedliwienia usprawiedliwienia = akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem();
//                Obecnosc obecnosc = usprawiedliwienia.getObecnosc();
//                obecnosc.setWartosc("2");
//
//                session.beginTransaction();
//                session.update(obecnosc);
//                session.getTransaction().commit();
//
//                session.close();
//                session = SessionController.getSession();
//            }
//        }
    }
//    public void shutdown(){
//        session.close();
//    }
}
