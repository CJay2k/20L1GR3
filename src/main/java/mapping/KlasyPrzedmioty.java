/**
 * Package mapping przechowuje obiekty, ktore generuje Hibernate na podstawie naszej bazy danych
 */

package mapping;

import javax.persistence.*;
import java.util.Objects;


/**
 * Klasa KlasyPrzedmioty, przechowuje informacje o lekcjach przedmiotu odbywających się w danej klasie szkolnej.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-05-11
 */

@Entity
@Table(name = "Klasy_Przedmioty", schema = "edziennik", catalog = "")
public class KlasyPrzedmioty {
    /**
     * Zmienna przechowujaca klucz głóny naszej klasy.
     */
    private int klasyPrzedmiotyId;
    /**
     * Zmienna przechowująca klucz główny klasy Klasy.
     */
    private Klasy klasyByKlasaId;
    /**
     * Zmienna przechowująca klucz główny klasy Przedmioty.
     */
    private Przedmioty przedmiotyByPrzedmiotId;

    @Id
    @Column(name = "klasy_przedmioty_id", nullable = false)
    public int getKlasyPrzedmiotyId() {
        return klasyPrzedmiotyId;
    }

    public void setKlasyPrzedmiotyId(int klasyPrzedmiotyId) {
        this.klasyPrzedmiotyId = klasyPrzedmiotyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KlasyPrzedmioty that = (KlasyPrzedmioty) o;
        return klasyPrzedmiotyId == that.klasyPrzedmiotyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(klasyPrzedmiotyId);
    }

    @ManyToOne
    @JoinColumn(name = "klasa_id", referencedColumnName = "klasa_id", nullable = false)
    public Klasy getKlasyByKlasaId() {
        return klasyByKlasaId;
    }

    public void setKlasyByKlasaId(Klasy klasyByKlasaId) {
        this.klasyByKlasaId = klasyByKlasaId;
    }

    @ManyToOne
    @JoinColumn(name = "przedmiot_id", referencedColumnName = "przedmiot_id", nullable = false)
    public Przedmioty getPrzedmiotyByPrzedmiotId() {
        return przedmiotyByPrzedmiotId;
    }

    public void setPrzedmiotyByPrzedmiotId(Przedmioty przedmiotyByPrzedmiotId) {
        this.przedmiotyByPrzedmiotId = przedmiotyByPrzedmiotId;
    }
}
