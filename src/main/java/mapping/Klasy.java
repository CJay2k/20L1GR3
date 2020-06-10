/**
 * Package mapping przechowuje obiekty, ktore generuje Hibernate na podstawie naszej bazy danych
 */
package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Klasa Klasy, przechowuje informacje o klasach jakie znajdują się w szkole.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-05-11
 */

@Entity
public class Klasy {
    /**
     * Zmienna przechowująca id naszej klasy.
     */
    private int klasaId;
    /**
     * Zmienna przechowująca nazwe klasy.
     */
    private String nazwaKlasy;
    /**
     * Zmienna przechowująca informacje o roku szkolnym.
     */
    private String rokSzkolny;
    /**
     * Zmienna zawierająca wszystkie przedmioty, zawierające się w danej klasie.
     */
    private Collection<KlasyPrzedmioty> klasyPrzedmiotiesByKlasaId;
    /**
     * Zmienna zawierająca wszystkich uczniów, należących do danej klasy.
     */
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
