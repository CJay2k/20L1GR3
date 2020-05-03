package mapping;

import java.sql.Time;
import java.util.Date;

public class Obecnosc implements java.io.Serializable {

    private Long id;
    private Przedmiot przedmiot;
    private Uczen uczen;
    private Date data;
    private String wartosc;
    private Time godzina;

    public Obecnosc() {
    }


    public Obecnosc(Przedmiot przedmiot, Uczen uczen, Date data, Time godzina, String wartosc) {
        this.przedmiot = przedmiot;
        this.uczen = uczen;
        this.data = data;
        this.wartosc = wartosc;
        this.godzina = godzina;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Przedmiot getPrzedmiot() {
        return this.przedmiot;
    }

    public void setPrzedmiot(Przedmiot przedmiot) {
        this.przedmiot = przedmiot;
    }

    public Uczen getUczen() {
        return this.uczen;
    }

    public void setUczen(Uczen uczen) {
        this.uczen = uczen;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getWartosc() {
        return this.wartosc;
    }

    public void setWartosc(String wartosc) {
        this.wartosc = wartosc;
    }

    public Time getGodzina() {
        return godzina;
    }

    public void setGodzina(Time godzina) {
        this.godzina = godzina;
    }

    public String getNazwaPrzedmiotu() {
        return this.przedmiot.getNazwaPrzedmiotu();
    }

    public void setNazwaPrzedmiotu(String nazwaPrzedmiotu) {
        this.przedmiot.setNazwaPrzedmiotu(nazwaPrzedmiotu);
    }


}


