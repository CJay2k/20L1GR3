package mapping;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Oceny {
    private int ocenaId;
    private String wartosc;
    private String opis;
    private Date data;
    private Uczniowie uczniowieByUczenId;
    private Przedmioty przedmiotyByPrzedmiotId;

    @Id
    @Column(name = "ocena_id", nullable = false)
    public int getOcenaId() {
        return ocenaId;
    }

    public void setOcenaId(int ocenaId) {
        this.ocenaId = ocenaId;
    }

    @Basic
    @Column(name = "wartosc", nullable = false, length = 2)
    public String getWartosc() {
        return wartosc;
    }

    public void setWartosc(String wartosc) {
        this.wartosc = wartosc;
    }

    @Basic
    @Column(name = "opis", nullable = false, length = 100)
    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
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
        Oceny oceny = (Oceny) o;
        return ocenaId == oceny.ocenaId &&
                Objects.equals(wartosc, oceny.wartosc) &&
                Objects.equals(opis, oceny.opis) &&
                Objects.equals(data, oceny.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ocenaId, wartosc, opis, data);
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
