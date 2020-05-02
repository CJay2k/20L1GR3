package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mapping.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

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
    public TableColumn akceptacjaUsprawiedliwienColumnData;
    public TableColumn akceptacjaUsprawiedliwienColumnGodzina;
    public ChoiceBox<Klasa> listaUczniowChoiceBoxKlasa;


    //    Function run when user logs on
    public void initialize() {
        hideElements();

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if(newTab.equals(tabListaUczniow)) {
                loadData(tabListaUczniow);
            }else if(newTab.equals(tabDodawanieUwag)) {
                loadData(tabDodawanieUwag);
            }else if(newTab.equals(tabDodajOcene)) {
                loadData(tabDodajOcene);
            }else if(newTab.equals(tabWpisywanieNieobecnosci)) {
                loadData(tabWpisywanieNieobecnosci);
            }else if(newTab.equals(tabAkceptacjaUsprawiedliwien)) {
                loadData(tabAkceptacjaUsprawiedliwien);
            }
        });
    }

    private void loadData(Tab tab){
        Session session = SessionController.getSession();

        if(tab.equals(tabListaUczniow)){
//            Query query1 = session.createQuery("SELECT k FROM Klasa k");
//            ObservableList listaKlas = FXCollections.observableArrayList(query1.list());
//
//            listaUczniowChoiceBoxKlasa.setItems(listaKlas);
//            listaUczniowChoiceBoxKlasa.getSelectionModel().select(0);
//
//            Query query2 = session.createQuery("SELECT u FROM Uczen u, Klasa k WHERE k.nazwaKlasy='" + listaUczniowChoiceBoxKlasa.getSelectionModel().getSelectedItem() +"'");
//            ObservableList<Uczen> listaUczniow = FXCollections.observableArrayList(query2.list());
//            System.out.println(listaUczniowChoiceBoxKlasa.getSelectionModel().getSelectedItem().toString());
//
//            listaUczniowColumnKlasa.setCellValueFactory(new PropertyValueFactory<Uczen, String>("nazwaKlasy"));
//            listaUczniowColumnNazwisko.setCellValueFactory(new PropertyValueFactory<Uczen, String>("nazwisko"));
//            listaUczniowColumnImie.setCellValueFactory(new PropertyValueFactory<Uczen, String>("imie"));
//
//            listaUczniowTableView.setItems(listaUczniow);
        }else if(tab.equals(tabDodawanieUwag)) {
            Query query = session.createQuery("SELECT u FROM Uczen u");
            ObservableList<Uczen> listaUczniow = FXCollections.observableArrayList(query.list());

            dodawanieUwagColumnNazwisko.setCellValueFactory(new PropertyValueFactory<Uczen, String>("nazwisko"));
            dodawanieUwagColumnImie.setCellValueFactory(new PropertyValueFactory<Uczen, String>("imie"));

            dodawanieUwagTableView.setItems(listaUczniow);
        }else if(tab.equals(tabDodajOcene)) {
            Query query = session.createQuery("SELECT u FROM Uczen u");
            ObservableList<Uczen> listaUczniow = FXCollections.observableArrayList(query.list());

            dodawanieOcenColumnNazwisko.setCellValueFactory(new PropertyValueFactory<Uczen, String>("nazwisko"));
            dodawanieOcenColumnImie.setCellValueFactory(new PropertyValueFactory<Uczen, String>("imie"));

            dodawanieOcenTableView.setItems(listaUczniow);
        }else if(tab.equals(tabWpisywanieNieobecnosci)) {
            Query query = session.createQuery("SELECT u FROM Uczen u");
            ObservableList<Uczen> listaUczniow = FXCollections.observableArrayList(query.list());

            wpisywanieNieobecnosciColumnNazwisko.setCellValueFactory(new PropertyValueFactory<Uczen, String>("nazwisko"));
            wpisywanieNieobecnosciColumnImie.setCellValueFactory(new PropertyValueFactory<Uczen, String>("imie"));

            wpisywanieNieobecnosciTableView.setItems(listaUczniow);
        }else if(tab.equals(tabAkceptacjaUsprawiedliwien)) {
            Query query = session.createQuery("SELECT u FROM Uczen u");
            ObservableList<Uczen> listaUczniow = FXCollections.observableArrayList(query.list());

            akceptacjaUsprawiedliwienColumnNazwisko.setCellValueFactory(new PropertyValueFactory<Uczen, String>("nazwisko"));
            akceptacjaUsprawiedliwienColumnImie.setCellValueFactory(new PropertyValueFactory<Uczen, String>("imie"));

            akceptacjaUsprawiedliwienTableView.setItems(listaUczniow);
        }
    }

//    Function that removes tabs id user has no privileges to see them
    private void hideElements(){
        if(loggedUserRole == 0){ // Role Dyrektor
            tabPane.getTabs().remove(tabOceny);
            tabPane.getTabs().remove(tabNieobecnosci);
            tabPane.getTabs().remove(tabUwagi);
            tabPane.getTabs().remove(tabUsprawiedliwienia);
            tabPane.getTabs().remove(tabDodawanieUwag);
            tabPane.getTabs().remove(tabDodajOcene);
            tabPane.getTabs().remove(tabWpisywanieNieobecnosci);
            tabPane.getTabs().remove(tabAkceptacjaUsprawiedliwien);

        }else if(loggedUserRole == 1){ // Role Nauczyciel
            tabPane.getTabs().remove(tabOceny);
            tabPane.getTabs().remove(tabNieobecnosci);
            tabPane.getTabs().remove(tabUwagi);
            tabPane.getTabs().remove(tabUsprawiedliwienia);
            tabPane.getTabs().remove(tabDodajUczniow);
            tabPane.getTabs().remove(tabDodajUsunNauczycieli);
            tabPane.getTabs().remove(tabDodajUsunPrzedmiot);
            tabPane.getTabs().remove(tabPrzydzielPrzedmiotDoKlasy);
            tabPane.getTabs().remove(tabPrzydzielNauczycielaDoPrzedmiotu);

        }else if(loggedUserRole == 2){ // Role Rodzic
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

        }else if(loggedUserRole == 3){ // Role Uczen
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

    @FXML
    private void refreshTab(){

    }
}
