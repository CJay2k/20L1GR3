package mapping;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Klasy_Przedmioty", schema = "edziennik", catalog = "")
public class KlasyPrzedmioty {
    private int klasyPrzedmiotyId;
    private int klasaId;
    private int przedmiotId;

    @Id
    @Column(name = "klasy_przedmioty_id", nullable = false)
    public int getKlasyPrzedmiotyId() {
        return klasyPrzedmiotyId;
    }

    public void setKlasyPrzedmiotyId(int klasyPrzedmiotyId) {
        this.klasyPrzedmiotyId = klasyPrzedmiotyId;
    }

    @Basic
    @Column(name = "klasa_id", nullable = false)
    public int getKlasaId() {
        return klasaId;
    }

    public void setKlasaId(int klasaId) {
        this.klasaId = klasaId;
    }

    @Basic
    @Column(name = "przedmiot_id", nullable = false)
    public int getPrzedmiotId() {
        return przedmiotId;
    }

    public void setPrzedmiotId(int przedmiotId) {
        this.przedmiotId = przedmiotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KlasyPrzedmioty that = (KlasyPrzedmioty) o;
        return klasyPrzedmiotyId == that.klasyPrzedmiotyId &&
                klasaId == that.klasaId &&
                przedmiotId == that.przedmiotId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(klasyPrzedmiotyId, klasaId, przedmiotId);
    }
}
