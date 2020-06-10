/**
 * Package mapping przechowuje obiekty, ktore generuje Hibernate na podstawie naszej bazy danych
 */

package mapping;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * Klasa Nieobecnosci, przechowuje informacje o zgloszonych nieobecnościach przez nauczycieli.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-05-11
 */

@Entity
public class Nieobecnosci {
    /**
     * Zmienna przechowująca id naszej klasy.
     */
    private int nieobecnoscId;
    /**
     * Zmienna przechowująca informacje o obecności(nieobecny,nieusprawiedliwiony,usprawiedliwiony)
     */
    private String wartosc;
    /**
     * Zmienna przechowująca informacje o dacie nieobecności.
     */
    private Date data;
    /**
     * Zmienna przechowująca treść usprawiedliwienia.
     */
    private String trescUsprawiedliwienia;
    /**
     * Zmienna przechowująca klucz główny klasy Uczniowie.
     */
    private Uczniowie uczniowieByUczenId;
    /**
     * Zmienna przechowująca klucz główny klasy Przedmioty.
     */
    private Przedmioty przedmiotyByPrzedmiotId;

    @Id
    @Column(name = "nieobecnosc_id", nullable = false)
    public int getNieobecnoscId() {
        return nieobecnoscId;
    }

    public void setNieobecnoscId(int nieobecnoscId) {
        this.nieobecnoscId = nieobecnoscId;
    }

    @Basic
    @Column(name = "wartosc", nullable = false, length = 100)
    public String getWartosc() {
        return wartosc;
    }

    public void setWartosc(String wartosc) {
        this.wartosc = wartosc;
    }

    @Basic
    @Column(name = "data", nullable = false)
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Basic
    @Column(name = "tresc_usprawiedliwienia", nullable = true, length = 100)
    public String getTrescUsprawiedliwienia() {
        return trescUsprawiedliwienia;
    }

    public void setTrescUsprawiedliwienia(String trescUsprawiedliwienia) {
        this.trescUsprawiedliwienia = trescUsprawiedliwienia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nieobecnosci that = (Nieobecnosci) o;
        return nieobecnoscId == that.nieobecnoscId &&
                Objects.equals(wartosc, that.wartosc) &&
                Objects.equals(data, that.data) &&
                Objects.equals(trescUsprawiedliwienia, that.trescUsprawiedliwienia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nieobecnoscId, wartosc, data, trescUsprawiedliwienia);
    }

    @ManyToOne
    @JoinColumn(name = "uczen_id", referencedColumnName = "uczen_id", nullable = false)
    public Uczniowie getUczniowieByUczenId() {
        return uczniowieByUczenId;
    }

    public void setUczniowieByUczenId(Uczniowie uczniowieByUczenId) {
        this.uczniowieByUczenId = uczniowieByUczenId;
    }

    @ManyToOne
    @JoinColumn(name = "przedmiot_id", referencedColumnName = "przedmiot_id", nullable = false)
    public Przedmioty getPrzedmiotyByPrzedmiotId() {
        return przedmiotyByPrzedmiotId;
    }

    public void setPrzedmiotyByPrzedmiotId(Przedmioty przedmiotyByPrzedmiotId) {
        this.przedmiotyByPrzedmiotId = przedmiotyByPrzedmiotId;
    }

    public String getImie() {
        return this.uczniowieByUczenId.getImie();
    }

    public void setImie(String imie) {
        this.uczniowieByUczenId.setImie(imie);
    }

    public String getNazwisko() {
        return this.uczniowieByUczenId.getNazwisko();
    }

    public void setNazwisko(String nazwisko) {
        this.uczniowieByUczenId.setNazwisko(nazwisko);
    }
}
