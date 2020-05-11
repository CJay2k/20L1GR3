package mapping;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Oceny {
    private int ocenaId;
    private int uczenId;
    private int przedmiotId;
    private String wartosc;
    private String opis;
    private Date data;

    @Id
    @Column(name = "ocena_id", nullable = false)
    public int getOcenaId() {
        return ocenaId;
    }

    public void setOcenaId(int ocenaId) {
        this.ocenaId = ocenaId;
    }

    @Basic
    @Column(name = "uczen_id", nullable = false)
    public int getUczenId() {
        return uczenId;
    }

    public void setUczenId(int uczenId) {
        this.uczenId = uczenId;
    }

    @Basic
    @Column(name = "przedmiot_id", nullable = false)
    public int getPrzedmiotId() {
        return przedmiotId;
    }

    public void setPrzedmiotId(int przedmiotId) {
        this.przedmiotId = przedmiotId;
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
                uczenId == oceny.uczenId &&
                przedmiotId == oceny.przedmiotId &&
                Objects.equals(wartosc, oceny.wartosc) &&
                Objects.equals(opis, oceny.opis) &&
                Objects.equals(data, oceny.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ocenaId, uczenId, przedmiotId, wartosc, opis, data);
    }
}
