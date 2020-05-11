package mapping;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Nauczyciele {
    private int nauczycielId;
    private String imie;
    private String nazwisko;
    private int kontoId;

    @Id
    @Column(name = "nauczyciel_id", nullable = false)
    public int getNauczycielId() {
        return nauczycielId;
    }

    public void setNauczycielId(int nauczycielId) {
        this.nauczycielId = nauczycielId;
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

    @Basic
    @Column(name = "konto_id", nullable = false)
    public int getKontoId() {
        return kontoId;
    }

    public void setKontoId(int kontoId) {
        this.kontoId = kontoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nauczyciele that = (Nauczyciele) o;
        return nauczycielId == that.nauczycielId &&
                kontoId == that.kontoId &&
                Objects.equals(imie, that.imie) &&
                Objects.equals(nazwisko, that.nazwisko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nauczycielId, imie, nazwisko, kontoId);
    }
}
