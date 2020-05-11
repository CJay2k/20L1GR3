package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Nauczyciele {
    private int nauczycielId;
    private String imie;
    private String nazwisko;
    private Konta kontaByKontoId;
    private Collection<Przedmioty> przedmiotiesByNauczycielId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nauczyciele that = (Nauczyciele) o;
        return nauczycielId == that.nauczycielId &&
                Objects.equals(imie, that.imie) &&
                Objects.equals(nazwisko, that.nazwisko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nauczycielId, imie, nazwisko);
    }

    @ManyToOne
    @JoinColumn(name = "konto_id", referencedColumnName = "konto_id", nullable = false)
    public Konta getKontaByKontoId() {
        return kontaByKontoId;
    }

    public void setKontaByKontoId(Konta kontaByKontoId) {
        this.kontaByKontoId = kontaByKontoId;
    }

    @OneToMany(mappedBy = "nauczycieleByNauczycielId")
    public Collection<Przedmioty> getPrzedmiotiesByNauczycielId() {
        return przedmiotiesByNauczycielId;
    }

    public void setPrzedmiotiesByNauczycielId(Collection<Przedmioty> przedmiotiesByNauczycielId) {
        this.przedmiotiesByNauczycielId = przedmiotiesByNauczycielId;
    }
}
