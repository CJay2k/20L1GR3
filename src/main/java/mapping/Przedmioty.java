package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Przedmioty {
    private int przedmiotId;
    private String nazwaPrzedmiotu;
    private Collection<KlasyPrzedmioty> klasyPrzedmiotiesByPrzedmiotId;
    private Collection<Nieobecnosci> nieobecnoscisByPrzedmiotId;
    private Collection<Oceny> oceniesByPrzedmiotId;
    private Nauczyciele nauczycieleByNauczycielId;
    private Collection<Uwagi> uwagisByPrzedmiotId;

    @Id
    @Column(name = "przedmiot_id", nullable = false)
    public int getPrzedmiotId() {
        return przedmiotId;
    }

    public void setPrzedmiotId(int przedmiotId) {
        this.przedmiotId = przedmiotId;
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
                Objects.equals(nazwaPrzedmiotu, that.nazwaPrzedmiotu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(przedmiotId, nazwaPrzedmiotu);
    }

    @OneToMany(mappedBy = "przedmiotyByPrzedmiotId")
    public Collection<KlasyPrzedmioty> getKlasyPrzedmiotiesByPrzedmiotId() {
        return klasyPrzedmiotiesByPrzedmiotId;
    }

    public void setKlasyPrzedmiotiesByPrzedmiotId(Collection<KlasyPrzedmioty> klasyPrzedmiotiesByPrzedmiotId) {
        this.klasyPrzedmiotiesByPrzedmiotId = klasyPrzedmiotiesByPrzedmiotId;
    }

    @OneToMany(mappedBy = "przedmiotyByPrzedmiotId")
    public Collection<Nieobecnosci> getNieobecnoscisByPrzedmiotId() {
        return nieobecnoscisByPrzedmiotId;
    }

    public void setNieobecnoscisByPrzedmiotId(Collection<Nieobecnosci> nieobecnoscisByPrzedmiotId) {
        this.nieobecnoscisByPrzedmiotId = nieobecnoscisByPrzedmiotId;
    }

    @OneToMany(mappedBy = "przedmiotyByPrzedmiotId")
    public Collection<Oceny> getOceniesByPrzedmiotId() {
        return oceniesByPrzedmiotId;
    }

    public void setOceniesByPrzedmiotId(Collection<Oceny> oceniesByPrzedmiotId) {
        this.oceniesByPrzedmiotId = oceniesByPrzedmiotId;
    }

    @ManyToOne
    @JoinColumn(name = "nauczyciel_id", referencedColumnName = "nauczyciel_id", nullable = false)
    public Nauczyciele getNauczycieleByNauczycielId() {
        return nauczycieleByNauczycielId;
    }

    public void setNauczycieleByNauczycielId(Nauczyciele nauczycieleByNauczycielId) {
        this.nauczycieleByNauczycielId = nauczycieleByNauczycielId;
    }

    @OneToMany(mappedBy = "przedmiotyByPrzedmiotId")
    public Collection<Uwagi> getUwagisByPrzedmiotId() {
        return uwagisByPrzedmiotId;
    }

    public void setUwagisByPrzedmiotId(Collection<Uwagi> uwagisByPrzedmiotId) {
        this.uwagisByPrzedmiotId = uwagisByPrzedmiotId;
    }
}
