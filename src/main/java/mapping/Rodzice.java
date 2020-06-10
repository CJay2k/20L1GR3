/**
 * Package mapping przechowuje obiekty, ktore generuje Hibernate na podstawie naszej bazy danych
 */
package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Klasa Rodzce, przechowuje informacje o rodzicach danego ucznia.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-05-11
 */

@Entity
public class Rodzice {
    /**
     * Zmienna przechowująca klucz główny 'id' naszej klasy Rodzice.
     */
    private int rodzicId;
    /**
     * Zmienna przechowująca informacje o imieniu ojca.
     */
    private String imieOjca;
    /**
     * Zmienna przechowująca informacje o nazwisku ojca.
     */
    private String nazwiskoOjca;
    /**
     * Zmienna przechowująca informacje o imieniu matki.
     */
    private String imieMatki;
    /**
     * Zmienna przechowująca informacje o nazwisku matki.
     */
    private String nazwiskoMatki;
    /**
     * Zmienna przechowująca klucz główny 'id' klasy Konto.
     */
    private Konta kontaByKontoId;
    /**
     * Zmienna przechowująca informacje o uczniach danych rodziców.
     */
    private Collection<Uczniowie> uczniowiesByRodzicId;

    @Id
    @Column(name = "rodzic_id", nullable = false)
    public int getRodzicId() {
        return rodzicId;
    }

    public void setRodzicId(int rodzicId) {
        this.rodzicId = rodzicId;
    }

    @Basic
    @Column(name = "imie_ojca", nullable = true, length = 100)
    public String getImieOjca() {
        return imieOjca;
    }

    public void setImieOjca(String imieOjca) {
        this.imieOjca = imieOjca;
    }

    @Basic
    @Column(name = "nazwisko_ojca", nullable = true, length = 100)
    public String getNazwiskoOjca() {
        return nazwiskoOjca;
    }

    public void setNazwiskoOjca(String nazwiskoOjca) {
        this.nazwiskoOjca = nazwiskoOjca;
    }

    @Basic
    @Column(name = "imie_matki", nullable = true, length = 100)
    public String getImieMatki() {
        return imieMatki;
    }

    public void setImieMatki(String imieMatki) {
        this.imieMatki = imieMatki;
    }

    @Basic
    @Column(name = "nazwisko_matki", nullable = true, length = 100)
    public String getNazwiskoMatki() {
        return nazwiskoMatki;
    }

    public void setNazwiskoMatki(String nazwiskoMatki) {
        this.nazwiskoMatki = nazwiskoMatki;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rodzice rodzice = (Rodzice) o;
        return rodzicId == rodzice.rodzicId &&
                Objects.equals(imieOjca, rodzice.imieOjca) &&
                Objects.equals(nazwiskoOjca, rodzice.nazwiskoOjca) &&
                Objects.equals(imieMatki, rodzice.imieMatki) &&
                Objects.equals(nazwiskoMatki, rodzice.nazwiskoMatki);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rodzicId, imieOjca, nazwiskoOjca, imieMatki, nazwiskoMatki);
    }

    @ManyToOne
    @JoinColumn(name = "konto_id", referencedColumnName = "konto_id", nullable = false)
    public Konta getKontaByKontoId() {
        return kontaByKontoId;
    }

    public void setKontaByKontoId(Konta kontaByKontoId) {
        this.kontaByKontoId = kontaByKontoId;
    }

    @OneToMany(mappedBy = "rodziceByRodzicId")
    public Collection<Uczniowie> getUczniowiesByRodzicId() {
        return uczniowiesByRodzicId;
    }

    public void setUczniowiesByRodzicId(Collection<Uczniowie> uczniowiesByRodzicId) {
        this.uczniowiesByRodzicId = uczniowiesByRodzicId;
    }
}
