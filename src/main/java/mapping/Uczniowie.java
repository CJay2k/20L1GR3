/**
 * Package mapping przechowuje obiekty, ktore generuje Hibernate na podstawie naszej bazy danych
 */
package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Klasa Uczniowie przechowuje informacje o uczniach w naszej szkole.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-05-11
 */

@Entity
public class Uczniowie {
    /**
     * Zmienna przechowująca klucz główny 'id' naszej klasy Rodzice.
     */
    private int uczenId;
    /**
     * Zmienna przechowująca imie danego ucznia.
     */
    private String imie;
    /**
     * Zmienna przechowująca nazwisko danego ucznia.
     */
    private String nazwisko;
    /**
     * Zmienna przechowująca nieobecności jakie posiada dany uczeń.
     */
    private Collection<Nieobecnosci> nieobecnoscisByUczenId;
    /**
     * Zmienna przechowująca oceny danego ucznia.
     */
    private Collection<Oceny> oceniesByUczenId;
    /**
     * Zmienna przechowująca informacje o klasie do jakiej należy uczeń.
     */
    private Klasy klasyByKlasaId;
    /**
     * Zmienna przechowująca informacje o rodzicach danego ucznia.
     */
    private Rodzice rodziceByRodzicId;
    /**
     * Zmienna przechowująca klucz główny klasy Konto.
     */
    private Konta kontaByKontoId;
    /**
     * Zmienna przechowująca informacje o uwagach danego ucznia.
     */
    private Collection<Uwagi> uwagisByUczenId;

    @Id
    @Column(name = "uczen_id", nullable = false)
    public int getUczenId() {
        return uczenId;
    }

    public void setUczenId(int uczenId) {
        this.uczenId = uczenId;
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
        Uczniowie uczniowie = (Uczniowie) o;
        return uczenId == uczniowie.uczenId &&
                Objects.equals(imie, uczniowie.imie) &&
                Objects.equals(nazwisko, uczniowie.nazwisko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uczenId, imie, nazwisko);
    }

    @OneToMany(mappedBy = "uczniowieByUczenId")
    public Collection<Nieobecnosci> getNieobecnoscisByUczenId() {
        return nieobecnoscisByUczenId;
    }

    public void setNieobecnoscisByUczenId(Collection<Nieobecnosci> nieobecnoscisByUczenId) {
        this.nieobecnoscisByUczenId = nieobecnoscisByUczenId;
    }

    @OneToMany(mappedBy = "uczniowieByUczenId")
    public Collection<Oceny> getOceniesByUczenId() {
        return oceniesByUczenId;
    }

    public void setOceniesByUczenId(Collection<Oceny> oceniesByUczenId) {
        this.oceniesByUczenId = oceniesByUczenId;
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
    @JoinColumn(name = "rodzic_id", referencedColumnName = "rodzic_id", nullable = false)
    public Rodzice getRodziceByRodzicId() {
        return rodziceByRodzicId;
    }

    public void setRodziceByRodzicId(Rodzice rodziceByRodzicId) {
        this.rodziceByRodzicId = rodziceByRodzicId;
    }

    @ManyToOne
    @JoinColumn(name = "konto_id", referencedColumnName = "konto_id", nullable = false)
    public Konta getKontaByKontoId() {
        return kontaByKontoId;
    }

    public void setKontaByKontoId(Konta kontaByKontoId) {
        this.kontaByKontoId = kontaByKontoId;
    }

    @OneToMany(mappedBy = "uczniowieByUczenId")
    public Collection<Uwagi> getUwagisByUczenId() {
        return uwagisByUczenId;
    }

    public void setUwagisByUczenId(Collection<Uwagi> uwagisByUczenId) {
        this.uwagisByUczenId = uwagisByUczenId;
    }

    @Override
    public String toString() {
        return this.imie + " " + this.nazwisko;
    }
}
