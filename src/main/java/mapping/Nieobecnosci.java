package mapping;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Nieobecnosci {
    private int nieobecnoscId;
    private int uczenId;
    private int przedmiotId;
    private String wartosc;
    private Date data;

    @Id
    @Column(name = "nieobecnosc_id", nullable = false)
    public int getNieobecnoscId() {
        return nieobecnoscId;
    }

    public void setNieobecnoscId(int nieobecnoscId) {
        this.nieobecnoscId = nieobecnoscId;
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
        Nieobecnosci that = (Nieobecnosci) o;
        return nieobecnoscId == that.nieobecnoscId &&
                uczenId == that.uczenId &&
                przedmiotId == that.przedmiotId &&
                Objects.equals(wartosc, that.wartosc) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nieobecnoscId, uczenId, przedmiotId, wartosc, data);
    }
}
