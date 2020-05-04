package mapping;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class Ocena implements Serializable {

    private Integer id;
    private Przedmiot przedmiot;
    private RodzajOceny rodzajOceny;
    private Uczen uczen;
    private String stopien;
    private Date data;
    private String opis;

    public Ocena() {
    }


    public Ocena(Uczen uczen) {
        this.uczen = uczen;
    }

    public Ocena(Przedmiot przedmiot, Uczen uczen, String stopien, Date data, String opis) {
        this.przedmiot = przedmiot;
        this.uczen = uczen;
        this.stopien = stopien;
        this.data = data;
        this.opis = opis;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Przedmiot getPrzedmiot() {
        return this.przedmiot;
    }

    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
    }

    public RodzajOceny getRodzajOceny() {
        return this.rodzajOceny;
    }

    public void setRodzajOceny(RodzajOceny rodzajOceny) {
        this.rodzajOceny = rodzajOceny;
    }

    public Uczen getUczen() {
        return this.uczen;
    }

    public void setUczen(Uczen uczen) {
        this.uczen = uczen;
    }

    public String getStopien() {
        return this.stopien;
    }

    public void setStopien(String stopien) {
        this.stopien = stopien;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNazwaPrzedmiotu() {
        return przedmiot.getNazwaPrzedmiotu();
    }

    public void setNazwaPrzedmiotu(String nazwaPrzedmiotu) {
        this.przedmiot.setNazwaPrzedmiotu(nazwaPrzedmiotu);
    }


}


