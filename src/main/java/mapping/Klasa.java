package mapping;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Klasa implements java.io.Serializable {

    private String nazwaKlasy;
    private Nauczyciel nauczyciel;
    private Date rokSzkolny;
    private Set zajecias = new HashSet(0);
    private Set skladKlasies = new HashSet(0);
    private Set uczens = new HashSet(0);

    public Klasa() {
    }

    public Klasa(String nazwaKlasy) {
        this.nazwaKlasy = nazwaKlasy;
    }

    public Klasa(String nazwaKlasy, Nauczyciel nauczyciel, Date rokSzkolny, Set zajecias, Set skladKlasies, Set uczens) {
        this.nazwaKlasy = nazwaKlasy;
        this.nauczyciel = nauczyciel;
        this.rokSzkolny = rokSzkolny;
        this.zajecias = zajecias;
        this.skladKlasies = skladKlasies;
        this.uczens = uczens;
    }

    public String getNazwaKlasy() {
        return this.nazwaKlasy;
    }

    public void setNazwaKlasy(String nazwaKlasy) {
        this.nazwaKlasy = nazwaKlasy;
    }

    public Nauczyciel getNauczyciel() {
        return this.nauczyciel;
    }

    public void setNauczyciel(Nauczyciel nauczyciel) {
        this.nauczyciel = nauczyciel;
    }

    public Date getRokSzkolny() {
        return this.rokSzkolny;
    }

    public void setRokSzkolny(Date rokSzkolny) {
        this.rokSzkolny = rokSzkolny;
    }

    public Set getZajecias() {
        return this.zajecias;
    }

    public void setZajecias(Set zajecias) {
        this.zajecias = zajecias;
    }

    public Set getSkladKlasies() {
        return this.skladKlasies;
    }

    public void setSkladKlasies(Set skladKlasies) {
        this.skladKlasies = skladKlasies;
    }

    public Set getUczens() {
        return this.uczens;
    }

    public void setUczens(Set uczens) {
        this.uczens = uczens;
    }

    @Override
    public String toString() {
        return nazwaKlasy;
    }
}


