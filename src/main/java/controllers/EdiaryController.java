package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mapping.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


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
    public Tab tabDodajUsunNauczycieli;
    public Tab tabDodajUsunPrzedmiot;
    public Tab tabPrzydzielNauczycielaDoPrzedmiotu;
    public Tab tabPrzydzielPrzedmiotDoKlasy;
    public TableView<Uczen> listaUczniowTableView;
    public TableColumn<Uczen, String> listaUczniowColumnKlasa;
    public TableColumn<Uczen, String> listaUczniowColumnNazwisko;
    public TableColumn<Uczen, String> listaUczniowColumnImie;
    public TableView<Uczen> dodawanieUwagTableView;
    public TableColumn<Uczen, String> dodawanieUwagColumnNazwisko;
    public TableColumn<Uczen, String> dodawanieUwagColumnImie;

    public TableView<Uczen> dodawanieOcenTableView;
    public TableColumn<Uczen, String> dodawanieOcenColumnNazwisko;
    public TableColumn<Uczen, String> dodawanieOcenColumnImie;

    // Dodawanie ocen
    public DatePicker dodawanieOcenInputData;
    public TextField dodawanieOcenInputGodzina;
    public TextArea dodawanieOcenInputGodzinaZaco;
    public Button dodawanieOcenButtonDodaj;
    public ChoiceBox<Przedmiot> dodawanieOcenChoiceBoxPrzedmiot;
    public ChoiceBox<Klasa> dodawanieOcenChoiceBoxKlasa;
    public TextField dodawanieOcenInputStopien;

    // Wpisywanie nieobecnosci
    public TableView<Uczen> wpisywanieNieobecnosciTableView;
    public TableColumn<Uczen, String> wpisywanieNieobecnosciColumnNazwisko;
    public TableColumn<Uczen, String> wpisywanieNieobecnosciColumnImie;
    public ChoiceBox<Przedmiot> dodawanieNieobecnosciChoiceBoxPrzedmiot;
    public ChoiceBox<Klasa> dodawanieNieobecnosciChoiceBoxKlasa;
    public DatePicker wpisywanieNieobecnosciInputData;
    public TextField wpisywanieNieobecnosciInputGodzina;

    // Akceptacja usprawiedliwien
    public TableView<Usprawiedliwienia> akceptacjaUsprawiedliwienTableView;
    public TableColumn<Uczen, String> akceptacjaUsprawiedliwienColumnNazwisko;
    public TableColumn<Uczen, String> akceptacjaUsprawiedliwienColumnImie;
    public TableColumn<Usprawiedliwienia, Date> akceptacjaUsprawiedliwienColumnData;
    public TableColumn<Usprawiedliwienia, Time> akceptacjaUsprawiedliwienColumnGodzina;
    public TableColumn<Usprawiedliwienia, String> akceptacjaUsprawiedliwienColumnTresc;
    public ChoiceBox<Przedmiot> akceptacjaUsprawiedliwienChoiceBoxPrzedmiot;
    public ChoiceBox<Klasa> akceptacjaUsprawiedliwienChoiceBoxKlasa;
    public TextArea akceptacjaUsprawiedliwienTextAreaTresc;

    public ChoiceBox<Klasa> listaUczniowChoiceBoxKlasa;
    public ChoiceBox<Przedmiot> dodawanieUwagChoiceBoxPrzedmiot;
    public ChoiceBox<Klasa> dodawanieUwagChoiceBoxKlasa;

    public TableView<Ocena> ocenyTableEdiary;

    public TableColumn<Ocena, Date> ocenyColumnData;
    public TableColumn<Ocena, String> ocenyColumnPrzedmiot;
    public TableColumn<Ocena, String> ocenyColumnOcena;
    public TableColumn<Ocena, String> ocenyColumnZaCo;

    public TableView<Nauczyciel> przydzielNauczycielaTableView;
    public TableColumn<Nauczyciel, String> przydzielNauczycielaColumnNazwisko;
    public TableColumn<Nauczyciel, String> przydzielNauczycielaColumnImie;
    public ChoiceBox<Przedmiot> przydzielNauczycielaChoiceBoxPrzedmiot;

    public TableView<Zajecia> przydzielPrzedmiotDoKlasyTableView;
    public TableColumn<Zajecia, String> przydzielPrzedmiotDoKlasyColumnKlasa;
    public TableColumn<Zajecia, String> przydzielPrzedmiotDoKlasyColumnPrzedmioty;
    public ChoiceBox<Zajecia> przydzielPrzedmiotDoKlasyChoiceBoxPrzedmiot;
    public ChoiceBox<Zajecia> przydzielPrzedmiotDoKlasyChoiceBoxKlasa;

    public TableView<Usprawiedliwienia> usprawiedliwieniaTableView;
    public TableColumn<Usprawiedliwienia, Date> usprawiedliwieniaColumnData;
    public TableColumn<Usprawiedliwienia, Time> usprawiedliwieniaColumnGodzina;
    public TableColumn<Usprawiedliwienia, String> usprawiedliwieniaColumnPrzedmiot;
    public TableColumn<Usprawiedliwienia, String> usprawiedliwieniaColumnTresc;
    public TextArea usprawiedliwieniaTextAreaTresc;

    /*TODO*/
    public TableColumn uwagiColumnUwagi;
    public TableColumn uwagiColumnPrzedmiot;
    public TableColumn uwagiColumnData;
    public TableView uwagiTableView;

    public TableView<Obecnosc> nieobecnosciTableView;
    public TableColumn<Obecnosc, Date> nieobecnosciColumnData;
    public TableColumn<Obecnosc, Time> nieobecnosciColumnGodzina;
    public TableColumn<Obecnosc, String> nieobecnosciColumnPrzedmiot;
    public TableColumn<Obecnosc, String> nieobecnosciColumnUsprawiedliwiona;


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
        /*
            NAUCZYCIEL
         */

        if (tab.equals(tabListaUczniow)) {
            if (listaUczniowChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                Query query1 = session.createQuery("SELECT k FROM Klasa k");
                ObservableList listaKlas = FXCollections.observableArrayList(query1.list());

                listaUczniowChoiceBoxKlasa.setItems(listaKlas);
                listaUczniowChoiceBoxKlasa.getSelectionModel().select(0);
            }

            Query query2 = session.createQuery("SELECT u FROM Uczen u, Klasa k WHERE u.klasa = k and k.nazwaKlasy='" + listaUczniowChoiceBoxKlasa.getValue() + "'");
            ObservableList<Uczen> listaUczniow = FXCollections.observableArrayList(query2.list());

            listaUczniowColumnKlasa.setCellValueFactory(new PropertyValueFactory<>("nazwaKlasy"));
            listaUczniowColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            listaUczniowColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

            listaUczniowTableView.setItems(listaUczniow);

        } else if (tab.equals(tabDodawanieUwag)) {
            if (dodawanieUwagChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                Query query1 = session.createQuery("SELECT k FROM Klasa k");
                ObservableList listaKlas = FXCollections.observableArrayList(query1.list());

                dodawanieUwagChoiceBoxKlasa.setItems(listaKlas);
                dodawanieUwagChoiceBoxKlasa.getSelectionModel().select(0);
            }

            Query query1 = session.createQuery("SELECT z.przedmiot.nazwaPrzedmiotu FROM Zajecia z WHERE z.klasa.nazwaKlasy='" + dodawanieUwagChoiceBoxKlasa.getValue() + "'");
            ObservableList listaPrzedmiotow = FXCollections.observableArrayList(query1.list());

            dodawanieUwagChoiceBoxPrzedmiot.setItems(listaPrzedmiotow);

            if (dodawanieUwagChoiceBoxPrzedmiot.getSelectionModel().isEmpty()) {
                dodawanieUwagChoiceBoxPrzedmiot.getSelectionModel().select(0);
            }

            Query query = session.createQuery("SELECT u FROM Uczen u, Klasa k WHERE u.klasa='" + dodawanieUwagChoiceBoxKlasa.getValue() + "' and u.klasa=k");
            ObservableList<Uczen> listaUczniow = FXCollections.observableArrayList(query.list());

            dodawanieUwagColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            dodawanieUwagColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

            dodawanieUwagTableView.setItems(listaUczniow);

        } else if (tab.equals(tabDodajOcene)) {
            if (dodawanieOcenChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                Query query1 = session.createQuery("SELECT k FROM Klasa k");
                ObservableList listaKlas = FXCollections.observableArrayList(query1.list());

                dodawanieOcenChoiceBoxKlasa.setItems(listaKlas);
                dodawanieOcenChoiceBoxKlasa.getSelectionModel().select(0);
            }

            Query query1 = session.createQuery("SELECT z.przedmiot.nazwaPrzedmiotu FROM Zajecia z WHERE z.klasa.nazwaKlasy='" + dodawanieOcenChoiceBoxKlasa.getValue() + "'");
            ObservableList listaPrzedmiotow = FXCollections.observableArrayList(query1.list());

            dodawanieOcenChoiceBoxPrzedmiot.setItems(listaPrzedmiotow);

            if (dodawanieOcenChoiceBoxPrzedmiot.getSelectionModel().isEmpty()) {
                dodawanieOcenChoiceBoxPrzedmiot.getSelectionModel().select(0);
            }

            Query query = session.createQuery("SELECT u FROM Uczen u, Klasa k WHERE u.klasa='" + dodawanieOcenChoiceBoxKlasa.getValue() + "' and u.klasa=k");
            ObservableList<Uczen> listaUczniow = FXCollections.observableArrayList(query.list());

            dodawanieOcenColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            dodawanieOcenColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

            dodawanieOcenTableView.setItems(listaUczniow);

        } else if (tab.equals(tabWpisywanieNieobecnosci)) {
            if (dodawanieNieobecnosciChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                Query query1 = session.createQuery("SELECT k FROM Klasa k");
                ObservableList listaKlas = FXCollections.observableArrayList(query1.list());

                dodawanieNieobecnosciChoiceBoxKlasa.setItems(listaKlas);
                dodawanieNieobecnosciChoiceBoxKlasa.getSelectionModel().select(0);
            }

            Query query1 = session.createQuery("SELECT z.przedmiot.nazwaPrzedmiotu FROM Zajecia z WHERE z.klasa.nazwaKlasy='" + dodawanieNieobecnosciChoiceBoxKlasa.getValue() + "'");
            ObservableList listaPrzedmiotow = FXCollections.observableArrayList(query1.list());

            dodawanieNieobecnosciChoiceBoxPrzedmiot.setItems(listaPrzedmiotow);

            if (dodawanieNieobecnosciChoiceBoxPrzedmiot.getSelectionModel().isEmpty()) {
                dodawanieNieobecnosciChoiceBoxPrzedmiot.getSelectionModel().select(0);
            }

            Query query = session.createQuery("SELECT u FROM Uczen u, Klasa k WHERE u.klasa='" + dodawanieNieobecnosciChoiceBoxKlasa.getValue() + "' and u.klasa=k");
            ObservableList<Uczen> listaUczniow = FXCollections.observableArrayList(query.list());

            wpisywanieNieobecnosciColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            wpisywanieNieobecnosciColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));

            wpisywanieNieobecnosciTableView.setItems(listaUczniow);

        } else if (tab.equals(tabAkceptacjaUsprawiedliwien)) {
            if (akceptacjaUsprawiedliwienChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                Query query1 = session.createQuery("SELECT k FROM Klasa k");
                ObservableList listaKlas = FXCollections.observableArrayList(query1.list());

                akceptacjaUsprawiedliwienChoiceBoxKlasa.setItems(listaKlas);
                akceptacjaUsprawiedliwienChoiceBoxKlasa.getSelectionModel().select(0);
            }

            Query query1 = session.createQuery("SELECT z.przedmiot.nazwaPrzedmiotu FROM Zajecia z WHERE z.klasa.nazwaKlasy='" + akceptacjaUsprawiedliwienChoiceBoxKlasa.getValue() + "'");
            ObservableList listaPrzedmiotow = FXCollections.observableArrayList(query1.list());

            akceptacjaUsprawiedliwienChoiceBoxPrzedmiot.setItems(listaPrzedmiotow);

            if (akceptacjaUsprawiedliwienChoiceBoxPrzedmiot.getSelectionModel().isEmpty()) {
                akceptacjaUsprawiedliwienChoiceBoxPrzedmiot.getSelectionModel().select(0);
            }

            Query query = session.createQuery("SELECT us FROM Usprawiedliwienia us WHERE us.uczen.klasa.nazwaKlasy='" + akceptacjaUsprawiedliwienChoiceBoxKlasa.getValue() + "' and us.obecnosc.wartosc like '0'");
            ObservableList<Usprawiedliwienia> listaUsprawiedliwien = FXCollections.observableArrayList(query.list());

            akceptacjaUsprawiedliwienColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            akceptacjaUsprawiedliwienColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
            akceptacjaUsprawiedliwienColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
            akceptacjaUsprawiedliwienColumnGodzina.setCellValueFactory(new PropertyValueFactory<>("godzina"));
            akceptacjaUsprawiedliwienColumnTresc.setCellValueFactory(new PropertyValueFactory<>("tresc"));


            akceptacjaUsprawiedliwienTableView.setItems(listaUsprawiedliwien);
            /*
                Rodzic
             */


        } else if (tab.equals(tabOceny)) {

            Query query1 = session.createQuery("SELECT o FROM Ocena o");
            ObservableList<Ocena> listaOcen = FXCollections.observableArrayList(query1.list());

            ocenyColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
            ocenyColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("nazwaPrzedmiotu"));
            ocenyColumnOcena.setCellValueFactory(new PropertyValueFactory<>("stopien"));
            ocenyColumnZaCo.setCellValueFactory(new PropertyValueFactory<>("opis"));

            ocenyTableEdiary.setItems(listaOcen);

        }
                        /*
                Dyrektor
             */
        else if (tab.equals(tabPrzydzielNauczycielaDoPrzedmiotu)) {

            Query query1 = session.createQuery("SELECT n FROM Nauczyciel n");
            ObservableList<Nauczyciel> listaNauczycieli = FXCollections.observableArrayList(query1.list());

            przydzielNauczycielaColumnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
            przydzielNauczycielaColumnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));

            przydzielNauczycielaTableView.setItems(listaNauczycieli);

            Query query2 = session.createQuery("SELECT p.nazwaPrzedmiotu FROM Przedmiot p");
            ObservableList<Przedmiot> listaPrzedmiotow = FXCollections.observableArrayList(query2.list());

            przydzielNauczycielaChoiceBoxPrzedmiot.setItems(listaPrzedmiotow);

            if (przydzielNauczycielaChoiceBoxPrzedmiot.getSelectionModel().isEmpty()) {
                przydzielNauczycielaChoiceBoxPrzedmiot.getSelectionModel().select(0);
            }


        } else if (tab.equals(tabPrzydzielPrzedmiotDoKlasy)) {

            Query query1 = session.createQuery("SELECT z FROM Zajecia z");
            ObservableList<Zajecia> listaZajec = FXCollections.observableArrayList(query1.list());

            przydzielPrzedmiotDoKlasyColumnKlasa.setCellValueFactory(new PropertyValueFactory<>("nazwaKlasy"));
            przydzielPrzedmiotDoKlasyColumnPrzedmioty.setCellValueFactory(new PropertyValueFactory<>("nazwaPrzedmiotu"));

            przydzielPrzedmiotDoKlasyTableView.setItems(listaZajec);

            if (przydzielPrzedmiotDoKlasyChoiceBoxKlasa.getSelectionModel().isEmpty()) {
                Query query2 = session.createQuery("SELECT k FROM Klasa k");
                ObservableList listaKlas = FXCollections.observableArrayList(query2.list());

                przydzielPrzedmiotDoKlasyChoiceBoxKlasa.setItems(listaKlas);
                przydzielPrzedmiotDoKlasyChoiceBoxKlasa.getSelectionModel().select(0);
            }

            Query query3 = session.createQuery("SELECT z.przedmiot.nazwaPrzedmiotu FROM Zajecia z WHERE z.klasa.nazwaKlasy='" + przydzielPrzedmiotDoKlasyChoiceBoxKlasa.getValue() + "'");
            ObservableList listaPrzedmiotow = FXCollections.observableArrayList(query3.list());

            przydzielPrzedmiotDoKlasyChoiceBoxPrzedmiot.setItems(listaPrzedmiotow);

            if (przydzielPrzedmiotDoKlasyChoiceBoxPrzedmiot.getSelectionModel().isEmpty()) {
                przydzielPrzedmiotDoKlasyChoiceBoxPrzedmiot.getSelectionModel().select(0);
            }


        }
                        /*
                Rodzic/Uczen
             */
        else if (tab.equals(tabUsprawiedliwienia)) {

            Query query1 = session.createQuery("SELECT u FROM Usprawiedliwienia u");
            ObservableList<Usprawiedliwienia> listaUsprawiedliwien = FXCollections.observableArrayList(query1.list());

            usprawiedliwieniaColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
            usprawiedliwieniaColumnGodzina.setCellValueFactory(new PropertyValueFactory<>("godzina"));
            usprawiedliwieniaColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("nazwaPrzedmiotu"));
            usprawiedliwieniaColumnTresc.setCellValueFactory(new PropertyValueFactory<>("tresc"));

            usprawiedliwieniaTableView.setItems(listaUsprawiedliwien);










        }


        /*TODO*/
//        else if (tab.equals(tabUwagi)) {
//
//            Query query1 = session.createQuery("SELECT u FROM Uwagi u");
//            ObservableList<Usprawiedliwienia> listaUsprawiedliwien = FXCollections.observableArrayList(query1.list());
//
//            usprawiedliwieniaColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
//            usprawiedliwieniaColumnGodzina.setCellValueFactory(new PropertyValueFactory<>("godzina"));
//            usprawiedliwieniaColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("nazwaPrzedmiotu"));
//            usprawiedliwieniaColumnTresc.setCellValueFactory(new PropertyValueFactory<>("tresc"));
//
//            usprawiedliwieniaTableView.setItems(listaUsprawiedliwien);
//
//        }


        else if (tab.equals(tabNieobecnosci)) {

            Query query1 = session.createQuery("SELECT n FROM Obecnosc n");
            ObservableList<Obecnosc> listaNieobesnoci = FXCollections.observableArrayList(query1.list());

            nieobecnosciColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
            nieobecnosciColumnGodzina.setCellValueFactory(new PropertyValueFactory<>("godzina"));
            nieobecnosciColumnPrzedmiot.setCellValueFactory(new PropertyValueFactory<>("nazwaPrzedmiotu"));
            nieobecnosciColumnUsprawiedliwiona.setCellValueFactory(new PropertyValueFactory<>("wartosc"));

            nieobecnosciTableView.setItems(listaNieobesnoci);

        }
    }

    private void saveData(Tab tab){
        if (tab.equals(tabDodajOcene)) {
            if(dodawanieOcenTableView.getSelectionModel().getSelectedItem() != null){
                Uczen uczen = dodawanieOcenTableView.getSelectionModel().getSelectedItem();

                if(!dodawanieOcenInputStopien.getText().isEmpty() && !dodawanieOcenInputData.getValue().toString().isEmpty() && !dodawanieOcenInputGodzinaZaco.getText().isEmpty()){
                    Query query1 = session.createQuery("SELECT p FROM Przedmiot p WHERE p.nazwaPrzedmiotu='" + dodawanieOcenChoiceBoxPrzedmiot.getSelectionModel().getSelectedItem() + "'");
                    ObservableList<Przedmiot> p = FXCollections.observableArrayList(query1.list());

                    Przedmiot przedmiot = p.get(0);

                    session.beginTransaction();
                    Ocena ocena = new Ocena(przedmiot, uczen, dodawanieOcenInputStopien.getText(), Date.valueOf(dodawanieOcenInputData.getValue()), dodawanieOcenInputGodzinaZaco.getText());
                    session.save(ocena);
                    session.getTransaction().commit();
                }
            }

        }else if (tab.equals(tabWpisywanieNieobecnosci)) {
            if(wpisywanieNieobecnosciTableView.getSelectionModel().getSelectedItem() != null){
                Uczen uczen = wpisywanieNieobecnosciTableView.getSelectionModel().getSelectedItem();

                if(!wpisywanieNieobecnosciInputGodzina.getText().isEmpty() && !wpisywanieNieobecnosciInputData.getValue().toString().isEmpty()){
                    Query query1 = session.createQuery("SELECT p FROM Przedmiot p WHERE p.nazwaPrzedmiotu='" + dodawanieNieobecnosciChoiceBoxPrzedmiot.getSelectionModel().getSelectedItem() + "'");
                    ObservableList<Przedmiot> p = FXCollections.observableArrayList(query1.list());

                    Przedmiot przedmiot = p.get(0);

                    session.beginTransaction();
                    Obecnosc obecnosc = new Obecnosc(przedmiot, uczen, Date.valueOf(wpisywanieNieobecnosciInputData.getValue()), Time.valueOf(wpisywanieNieobecnosciInputGodzina.getText()), "0");
                    session.save(obecnosc);
                    session.getTransaction().commit();
                }
            }

        }else if (tab.equals(tabAkceptacjaUsprawiedliwien)) {
            if(akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem() != null){

                Usprawiedliwienia usprawiedliwienia = akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem();
                Obecnosc obecnosc = usprawiedliwienia.getObecnosc();
                obecnosc.setWartosc("1");

                session.beginTransaction();
                session.update(obecnosc);
                session.getTransaction().commit();
            }
        }else if (tab.equals(tabUsprawiedliwienia)){
            if(usprawiedliwieniaTableView.getSelectionModel().getSelectedItem() != null) {
                Usprawiedliwienia usprawiedliwienia = usprawiedliwieniaTableView.getSelectionModel().getSelectedItem();

                usprawiedliwienia.setTresc(usprawiedliwieniaTextAreaTresc.getText());
                System.out.println(usprawiedliwieniaTextAreaTresc.getText());

                session.beginTransaction();
                session.update(usprawiedliwienia);
                session.getTransaction().commit();
            }
        }
    }

    //    Function that removes tabs id user has no privileges to see them
    private void hideElements() {
        if (loggedUserRole == 0) { // Role Dyrektor
            tabPane.getTabs().remove(tabOceny);
            tabPane.getTabs().remove(tabNieobecnosci);
            tabPane.getTabs().remove(tabUwagi);
            tabPane.getTabs().remove(tabUsprawiedliwienia);
            tabPane.getTabs().remove(tabDodawanieUwag);
            tabPane.getTabs().remove(tabDodajOcene);
            tabPane.getTabs().remove(tabWpisywanieNieobecnosci);
            tabPane.getTabs().remove(tabAkceptacjaUsprawiedliwien);

        } else if (loggedUserRole == 1) { // Role Nauczyciel
            tabPane.getTabs().remove(tabOceny);
            tabPane.getTabs().remove(tabNieobecnosci);
            tabPane.getTabs().remove(tabUwagi);
            tabPane.getTabs().remove(tabUsprawiedliwienia);
            tabPane.getTabs().remove(tabDodajUczniow);
            tabPane.getTabs().remove(tabDodajUsunNauczycieli);
            tabPane.getTabs().remove(tabDodajUsunPrzedmiot);
            tabPane.getTabs().remove(tabPrzydzielPrzedmiotDoKlasy);
            tabPane.getTabs().remove(tabPrzydzielNauczycielaDoPrzedmiotu);
            //TODO
            tabPane.getTabs().remove(tabDodawanieUwag);

        } else if (loggedUserRole == 2) { // Role Rodzic
            tabPane.getTabs().remove(tabListaUczniow);
            tabPane.getTabs().remove(tabDodawanieUwag);
            tabPane.getTabs().remove(tabDodajOcene);
            tabPane.getTabs().remove(tabWpisywanieNieobecnosci);
            tabPane.getTabs().remove(tabAkceptacjaUsprawiedliwien);
            tabPane.getTabs().remove(tabDodajUczniow);
            tabPane.getTabs().remove(tabDodajUsunNauczycieli);
            tabPane.getTabs().remove(tabDodajUsunPrzedmiot);
            tabPane.getTabs().remove(tabPrzydzielPrzedmiotDoKlasy);
            tabPane.getTabs().remove(tabPrzydzielNauczycielaDoPrzedmiotu);
            //TODO
            tabPane.getTabs().remove(tabUwagi);

        } else if (loggedUserRole == 3) { // Role Uczen
            tabPane.getTabs().remove(tabUsprawiedliwienia);
            tabPane.getTabs().remove(tabListaUczniow);
            tabPane.getTabs().remove(tabDodawanieUwag);
            tabPane.getTabs().remove(tabDodajOcene);
            tabPane.getTabs().remove(tabWpisywanieNieobecnosci);
            tabPane.getTabs().remove(tabAkceptacjaUsprawiedliwien);
            tabPane.getTabs().remove(tabDodajUczniow);
            tabPane.getTabs().remove(tabDodajUsunNauczycieli);
            tabPane.getTabs().remove(tabDodajUsunPrzedmiot);
            tabPane.getTabs().remove(tabPrzydzielPrzedmiotDoKlasy);
            tabPane.getTabs().remove(tabPrzydzielNauczycielaDoPrzedmiotu);
            //TODO
            tabPane.getTabs().remove(tabUwagi);

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
        }
    }

    public void saveData(ActionEvent actionEvent) {
        if (currentTab.equals(tabDodajOcene)) {
            saveData(tabDodajOcene);
        } else if (currentTab.equals(tabWpisywanieNieobecnosci)) {
            saveData(tabWpisywanieNieobecnosci);
        } else if (currentTab.equals(tabAkceptacjaUsprawiedliwien)) {
            saveData(tabAkceptacjaUsprawiedliwien);
        } else if (currentTab.equals(tabOceny)) {
            saveData(tabOceny);
        } else if (currentTab.equals(tabPrzydzielNauczycielaDoPrzedmiotu)) {
            saveData(tabPrzydzielNauczycielaDoPrzedmiotu);
        } else if (currentTab.equals(tabPrzydzielPrzedmiotDoKlasy)) {
            saveData(tabPrzydzielPrzedmiotDoKlasy);
        } else if (currentTab.equals(tabUsprawiedliwienia)) {
            saveData(tabUsprawiedliwienia);
        } else if (currentTab.equals(tabNieobecnosci)) {
            saveData(tabNieobecnosci);
        }
    }

    public void deleteData(ActionEvent actionEvent){
        if (currentTab.equals(tabAkceptacjaUsprawiedliwien)) {
            if(akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem() != null){

                Usprawiedliwienia usprawiedliwienia = akceptacjaUsprawiedliwienTableView.getSelectionModel().getSelectedItem();
                Obecnosc obecnosc = usprawiedliwienia.getObecnosc();
                obecnosc.setWartosc("2");

                session.beginTransaction();
                session.update(obecnosc);
                session.getTransaction().commit();
            }
        }
    }
//    public void shutdown(){
//        session.close();
//    }
}
