/**
 * Package mapping przechowuje obiekty, ktore generuje Hibernate na podstawie naszej bazy danych
 */

package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Klasa Nauczyciele, przechowuje informacje o nauczycielach w szkole.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-05-11
 */

@Entity
public class Nauczyciele {
    /**
     * Zmienna przechowująca id naszej klasy Nauczyciele.
     */
    private int nauczycielId;
    /**
     * Zmienna przechowująca imie nauczyciela.
     */
    private String imie;
    /**
     * Zmienna przechowująca nazwisko nauczyciela.
     */
    private String nazwisko;
    /**
     * Zmienna przechowująca klucz główny klasy Konta.
     */
    private Konta kontaByKontoId;
    /**
     * Zmienna przechowująca przedmioty pasując do danego nauczyciela.
     */
    private Collection<Przedmioty> przedmiotiesByNauczycielId;

    @Id
    @Column(name = "nauczyciel_id", nullable = false)
    public int getNauczycielId() {
        return nauczycielId;
    }

    public void setNauczycielId(int nauczycielId) {
        this.nauczycielId = nauczycielId;
    }

    @Basic
    @Column(name = "imie", nullable = false, length = 100)
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "nazwisko", nullable = false, length = 100)
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nauczyciele that = (Nauczyciele) o;
        return nauczycielId == that.nauczycielId &&
                Objects.equals(imie, that.imie) &&
                Objects.equals(nazwisko, that.nazwisko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nauczycielId, imie, nazwisko);
    }

    @ManyToOne
    @JoinColumn(name = "konto_id", referencedColumnName = "konto_id", nullable = false)
    public Konta getKontaByKontoId() {
        return kontaByKontoId;
    }

    public void setKontaByKontoId(Konta kontaByKontoId) {
        this.kontaByKontoId = kontaByKontoId;
    }

    @OneToMany(mappedBy = "nauczycieleByNauczycielId")
    public Collection<Przedmioty> getPrzedmiotiesByNauczycielId() {
        return przedmiotiesByNauczycielId;
    }

    public void setPrzedmiotiesByNauczycielId(Collection<Przedmioty> przedmiotiesByNauczycielId) {
        this.przedmiotiesByNauczycielId = przedmiotiesByNauczycielId;
    }

    @Override
    public String toString() {
        return this.imie + " " + this.nazwisko;
    }
}
