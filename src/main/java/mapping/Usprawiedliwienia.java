package mapping;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Entity
public class Usprawiedliwienia implements Serializable {
    private long id;
    private Date data;
    private Time godzina;
    private String tresc;
    private Uczen uczen;
    private Obecnosc obecnosc;

    public Usprawiedliwienia() {
    }

    public Usprawiedliwienia(Date data, Time godzina, String tresc, Uczen uczen, Obecnosc obecnosc) {
        this.data = data;
        this.godzina = godzina;
        this.tresc = tresc;
        this.uczen = uczen;
        this.obecnosc = obecnosc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getGodzina() {
        return godzina;
    }

    public void setGodzina(Time godzina) {
        this.godzina = godzina;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Uczen getUczen() {
        return uczen;
    }

    public void setUczen(Uczen uczen) {
        this.uczen = uczen;
    }

    public Obecnosc getObecnosc() {
        return obecnosc;
    }

    public void setObecnosc(Obecnosc obecnosc) {
        this.obecnosc = obecnosc;
    }

    public String getNazwisko() {
        return uczen.getNazwisko();
    }

    public void setNazwisko(String nazwisko) {
        this.uczen.setNazwisko(nazwisko);
    }

    public String getImie() {
        return uczen.getImie();
    }

    public void setImie(String imie) {
        this.uczen.setImie(imie);
    }

    public String getNazwaPrzedmiotu() {
        return this.obecnosc.getPrzedmiot().getNazwaPrzedmiotu();
    }

    public void setNazwaPrzedmiotu(String nazwaPrzedmiotu) {
        this.obecnosc.getPrzedmiot().setNazwaPrzedmiotu(nazwaPrzedmiotu);
    }

}
