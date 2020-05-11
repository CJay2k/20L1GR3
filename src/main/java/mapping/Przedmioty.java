package mapping;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Przedmioty {
    private int przedmiotId;
    private int nauczycielId;
    private String nazwaPrzedmiotu;

    @Id
    @Column(name = "przedmiot_id", nullable = false)
    public int getPrzedmiotId() {
        return przedmiotId;
    }

    public void setPrzedmiotId(int przedmiotId) {
        this.przedmiotId = przedmiotId;
    }

    @Basic
    @Column(name = "nauczyciel_id", nullable = false)
    public int getNauczycielId() {
        return nauczycielId;
    }

    public void setNauczycielId(int nauczycielId) {
        this.nauczycielId = nauczycielId;
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
                nauczycielId == that.nauczycielId &&
                Objects.equals(nazwaPrzedmiotu, that.nazwaPrzedmiotu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(przedmiotId, nauczycielId, nazwaPrzedmiotu);
    }
}
