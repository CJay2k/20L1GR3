package controllers;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import static controllers.LoginController.userRole;

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


    //    Function run when user logs on
    public void initialize() {
        hideElements();
    }

//    Function that removes tabs id user has no privileges to see them
    private void hideElements(){
        if(userRole == 0){ // Role Dyrektor
            tabPane.getTabs().remove(tabOceny);
            tabPane.getTabs().remove(tabNieobecnosci);
            tabPane.getTabs().remove(tabUwagi);
            tabPane.getTabs().remove(tabUsprawiedliwienia);
            tabPane.getTabs().remove(tabDodawanieUwag);
            tabPane.getTabs().remove(tabDodajOcene);
            tabPane.getTabs().remove(tabWpisywanieNieobecnosci);
            tabPane.getTabs().remove(tabAkceptacjaUsprawiedliwien);

        }else if(userRole == 1){ // Role Nauczyciel
            tabPane.getTabs().remove(tabOceny);
            tabPane.getTabs().remove(tabNieobecnosci);
            tabPane.getTabs().remove(tabUwagi);
            tabPane.getTabs().remove(tabUsprawiedliwienia);
            tabPane.getTabs().remove(tabDodajUczniow);
            tabPane.getTabs().remove(tabDodajUsunNauczycieli);
            tabPane.getTabs().remove(tabDodajUsunPrzedmiot);
            tabPane.getTabs().remove(tabPrzydzielPrzedmiotDoKlasy);
            tabPane.getTabs().remove(tabPrzydzielNauczycielaDoPrzedmiotu);

        }else if(userRole == 2){ // Role Rodzic
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

        }else if(userRole == 3){ // Role Uczen
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
}
