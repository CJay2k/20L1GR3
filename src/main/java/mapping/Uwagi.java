/**
 * Package mapping przechowuje obiekty, ktore generuje Hibernate na podstawie naszej bazy danych
 */
package mapping;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * Klasa Uwagi przechowuje informacje o uwagach jakie dostali poszczególni uczniowie.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-05-11
 */

@Entity
public class Uwagi {
    /**
     * Zmienna przechowująca klucz główny 'id' naszej klasy Uwagi.
     */
    private int obecnoscId;
    /**
     * Zmienna przechowująca informacje odnoszące się do danej uwagi.
     */
    private String wartosc;
    /**
     * Zmienna przechowująca datę, kiedy uczeń otrzymał daną uwagę.
     */
    private Date data;
    /**
     * Zmienna przechowująca klucz główny 'id' klasy Uczniowie.
     */
    private Uczniowie uczniowieByUczenId;
    /**
     * Zmienna przechowująca klucz główny 'id' naszej klasy Przedmioty.
     */
    private Przedmioty przedmiotyByPrzedmiotId;

    @Id
    @Column(name = "obecnosc_id", nullable = false)
    public int getObecnoscId() {
        return obecnoscId;
    }

    public void setObecnoscId(int obecnoscId) {
        this.obecnoscId = obecnoscId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uwagi uwagi = (Uwagi) o;
        return obecnoscId == uwagi.obecnoscId &&
                Objects.equals(wartosc, uwagi.wartosc) &&
                Objects.equals(data, uwagi.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(obecnoscId, wartosc, data);
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
}
