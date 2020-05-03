package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mapping.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Time;
import java.util.Date;

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
    public TableView<Uczen> wpisywanieNieobecnosciTableView;
    public TableColumn<Uczen, String> wpisywanieNieobecnosciColumnNazwisko;
    public TableColumn<Uczen, String> wpisywanieNieobecnosciColumnImie;
    public TableView<Uczen> akceptacjaUsprawiedliwienTableView;
    public TableColumn<Uczen, String> akceptacjaUsprawiedliwienColumnNazwisko;
    public TableColumn<Uczen, String> akceptacjaUsprawiedliwienColumnImie;
    public TableColumn<Usprawiedliwienia, Date> akceptacjaUsprawiedliwienColumnData;
    public TableColumn<Usprawiedliwienia, Time> akceptacjaUsprawiedliwienColumnGodzina;
    public TableColumn<Usprawiedliwienia, String> akceptacjaUsprawiedliwienColumnTresc;
    public ChoiceBox<Klasa> listaUczniowChoiceBoxKlasa;
    public ChoiceBox<Przedmiot> dodawanieUwagChoiceBoxPrzedmiot;
    public ChoiceBox<Klasa> dodawanieUwagChoiceBoxKlasa;
    public ChoiceBox<Przedmiot> dodawanieOcenChoiceBoxPrzedmiot;
    public ChoiceBox<Klasa> dodawanieOcenChoiceBoxKlasa;
    public ChoiceBox<Przedmiot> dodawanieNieobecnosciChoiceBoxPrzedmiot;
    public ChoiceBox<Klasa> dodawanieNieobecnosciChoiceBoxKlasa;
    public ChoiceBox<Przedmiot> akceptacjaUsprawiedliwienChoiceBoxPrzedmiot;
    public ChoiceBox<Klasa> akceptacjaUsprawiedliwienChoiceBoxKlasa;
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

    private Tab currentTab;

    //    Function run when user logs on
    public void initialize() {
        hideElements();

        currentTab = tabPane.getSelectionModel().getSelectedItem();
        refresh();

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            currentTab = newTab;
            refresh();
        });
    }

    //    Function that load data from database to tableviews
    private void loadData(Tab tab) {
        Session session = SessionController.getSession();

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

            Query query = session.createQuery("SELECT us FROM Usprawiedliwienia us, Klasa k WHERE us.uczen.klasa.nazwaKlasy='" + akceptacjaUsprawiedliwienChoiceBoxKlasa.getValue() + "' and us.uczen.klasa=k");
            ObservableList listaUsprawiedliwien = FXCollections.observableArrayList(query.list());

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

//        session.close();
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
        }
    }
}
