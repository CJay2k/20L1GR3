package mapping;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Uwagi {
    private int obecnoscId;
    private int uczenId;
    private int przedmiotId;
    private String wartosc;
    private Date data;

    @Id
    @Column(name = "obecnosc_id", nullable = false)
    public int getObecnoscId() {
        return obecnoscId;
    }

    public void setObecnoscId(int obecnoscId) {
        this.obecnoscId = obecnoscId;
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
        Uwagi uwagi = (Uwagi) o;
        return obecnoscId == uwagi.obecnoscId &&
                uczenId == uwagi.uczenId &&
                przedmiotId == uwagi.przedmiotId &&
                Objects.equals(wartosc, uwagi.wartosc) &&
                Objects.equals(data, uwagi.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(obecnoscId, uczenId, przedmiotId, wartosc, data);
    }
}
