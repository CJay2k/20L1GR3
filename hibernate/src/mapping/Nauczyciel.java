package mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="nauczyciel")
public class Nauczyciel implements java.io.Serializable {

    private long pesel;
    private String imie;
    private String nazwisko;

    private Autoryzacja autoryzacja;
    private Set klasas = new HashSet(0);
    private Set zajecias = new HashSet(0);

    public Nauczyciel() {
    }

    public Nauczyciel(Autoryzacja autoryzacja) {
        this.autoryzacja = autoryzacja;
    }

    public Nauczyciel(Autoryzacja autoryzacja, String imie, String nazwisko, Set klasas, Set zajecias) {
        this.autoryzacja = autoryzacja;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.klasas = klasas;
        this.zajecias = zajecias;
    }

    public long getPesel() {
        return this.pesel;
    }

    public void setPesel(long pesel) {
        this.pesel = pesel;
    }

    public Autoryzacja getAutoryzacja() {
        return this.autoryzacja;
    }

    public void setAutoryzacja(Autoryzacja autoryzacja) {
        this.autoryzacja = autoryzacja;
    }

    public String getImie() {
        return this.imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return this.nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Set getKlasas() {
        return this.klasas;
    }

    public void setKlasas(Set klasas) {
        this.klasas = klasas;
    }

    public Set getZajecias() {
        return this.zajecias;
    }

    public void setZajecias(Set zajecias) {
        this.zajecias = zajecias;
    }


}


