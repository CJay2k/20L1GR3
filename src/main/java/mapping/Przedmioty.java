/**
 * Package mapping przechowuje obiekty, ktore generuje Hibernate na podstawie naszej bazy danych
 */
package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Klasa Przedmioty, przechowuje informacje o przedmiotach wykładanych w szkole.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-05-11
 */

@Entity
public class Przedmioty {
    /**
     * Zmienna przechowująca klucz główny 'id' naszej klasy Przedmioty.
     */
    private int przedmiotId;
    /**
     * Zmienna przechowująca nazwe przedmiotu.
     */
    private String nazwaPrzedmiotu;
    /**
     * Zmienna przechowująca informacje o klasie, w której wykładany jest dany przedmiot.
     */
    private Collection<KlasyPrzedmioty> klasyPrzedmiotiesByPrzedmiotId;
    /**
     * Zmienna przechowująca informacje o nieobecnościach na danych przedmiocie.
     */
    private Collection<Nieobecnosci> nieobecnoscisByPrzedmiotId;
    /**
     * Zmienna przechowująca informacje o ocenach z danego przedmiotu.
     */
    private Collection<Oceny> oceniesByPrzedmiotId;
    /**
     * Zmienna przechowująca informacje o nauczycielu wykładacy dane przedmioty.
     */
    private Nauczyciele nauczycieleByNauczycielId;
    /**
     * Zmienna przechowująca uwagi otrzymane na danym przedmiocie.
     */
    private Collection<Uwagi> uwagisByPrzedmiotId;

    @Id
    @Column(name = "przedmiot_id", nullable = false)
    public int getPrzedmiotId() {
        return przedmiotId;
    }

    public void setPrzedmiotId(int przedmiotId) {
        this.przedmiotId = przedmiotId;
    }

    @Basic
    @Column(name = "nazwa_przedmiotu", nullable = false, length = 100)
    public String getNazwaPrzedmiotu() {
        return nazwaPrzedmiotu;
    }

    public void setNazwaPrzedmiotu(String nazwaPrzedmiotu) {
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Przedmioty that = (Przedmioty) o;
        return przedmiotId == that.przedmiotId &&
                Objects.equals(nazwaPrzedmiotu, that.nazwaPrzedmiotu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(przedmiotId, nazwaPrzedmiotu);
    }

    @OneToMany(mappedBy = "przedmiotyByPrzedmiotId")
    public Collection<KlasyPrzedmioty> getKlasyPrzedmiotiesByPrzedmiotId() {
        return klasyPrzedmiotiesByPrzedmiotId;
    }

    public void setKlasyPrzedmiotiesByPrzedmiotId(Collection<KlasyPrzedmioty> klasyPrzedmiotiesByPrzedmiotId) {
        this.klasyPrzedmiotiesByPrzedmiotId = klasyPrzedmiotiesByPrzedmiotId;
    }

    @OneToMany(mappedBy = "przedmiotyByPrzedmiotId")
    public Collection<Nieobecnosci> getNieobecnoscisByPrzedmiotId() {
        return nieobecnoscisByPrzedmiotId;
    }

    public void setNieobecnoscisByPrzedmiotId(Collection<Nieobecnosci> nieobecnoscisByPrzedmiotId) {
        this.nieobecnoscisByPrzedmiotId = nieobecnoscisByPrzedmiotId;
    }

    @OneToMany(mappedBy = "przedmiotyByPrzedmiotId")
    public Collection<Oceny> getOceniesByPrzedmiotId() {
        return oceniesByPrzedmiotId;
    }

    public void setOceniesByPrzedmiotId(Collection<Oceny> oceniesByPrzedmiotId) {
        this.oceniesByPrzedmiotId = oceniesByPrzedmiotId;
    }

    @ManyToOne
    @JoinColumn(name = "nauczyciel_id", referencedColumnName = "nauczyciel_id", nullable = false)
    public Nauczyciele getNauczycieleByNauczycielId() {
        return nauczycieleByNauczycielId;
    }

    public void setNauczycieleByNauczycielId(Nauczyciele nauczycieleByNauczycielId) {
        this.nauczycieleByNauczycielId = nauczycieleByNauczycielId;
    }

    @OneToMany(mappedBy = "przedmiotyByPrzedmiotId")
    public Collection<Uwagi> getUwagisByPrzedmiotId() {
        return uwagisByPrzedmiotId;
    }

    public void setUwagisByPrzedmiotId(Collection<Uwagi> uwagisByPrzedmiotId) {
        this.uwagisByPrzedmiotId = uwagisByPrzedmiotId;
    }

    @Override
    public String toString() {
        return nazwaPrzedmiotu;
    }
}
