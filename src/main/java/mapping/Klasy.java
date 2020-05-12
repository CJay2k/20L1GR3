package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Klasy {
    private int klasaId;
    private String nazwaKlasy;
    private String rokSzkolny;
    private Collection<KlasyPrzedmioty> klasyPrzedmiotiesByKlasaId;
    private Collection<Uczniowie> uczniowiesByKlasaId;

    @Id
    @Column(name = "klasa_id", nullable = false)
    public int getKlasaId() {
        return klasaId;
    }

    public void setKlasaId(int klasaId) {
        this.klasaId = klasaId;
    }

    @Basic
    @Column(name = "nazwa_klasy", nullable = false, length = 100)
    public String getNazwaKlasy() {
        return nazwaKlasy;
    }

    public void setNazwaKlasy(String nazwaKlasy) {
        this.nazwaKlasy = nazwaKlasy;
    }

    @Basic
    @Column(name = "rok_szkolny", nullable = false, length = 100)
    public String getRokSzkolny() {
        return rokSzkolny;
    }

    public void setRokSzkolny(String rokSzkolny) {
        this.rokSzkolny = rokSzkolny;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klasy klasy = (Klasy) o;
        return klasaId == klasy.klasaId &&
                Objects.equals(nazwaKlasy, klasy.nazwaKlasy) &&
                Objects.equals(rokSzkolny, klasy.rokSzkolny);
    }

    @Override
    public int hashCode() {
        return Objects.hash(klasaId, nazwaKlasy, rokSzkolny);
    }

    @OneToMany(mappedBy = "klasyByKlasaId")
    public Collection<KlasyPrzedmioty> getKlasyPrzedmiotiesByKlasaId() {
        return klasyPrzedmiotiesByKlasaId;
    }

    public void setKlasyPrzedmiotiesByKlasaId(Collection<KlasyPrzedmioty> klasyPrzedmiotiesByKlasaId) {
        this.klasyPrzedmiotiesByKlasaId = klasyPrzedmiotiesByKlasaId;
    }

    @OneToMany(mappedBy = "klasyByKlasaId")
    public Collection<Uczniowie> getUczniowiesByKlasaId() {
        return uczniowiesByKlasaId;
    }

    public void setUczniowiesByKlasaId(Collection<Uczniowie> uczniowiesByKlasaId) {
        this.uczniowiesByKlasaId = uczniowiesByKlasaId;
    }

    @Override
    public String toString() {
        return nazwaKlasy;
    }
}
