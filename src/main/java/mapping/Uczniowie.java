package mapping;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Uczniowie {
    private int uczenId;
    private String imie;
    private String nazwisko;
    private int klasaId;
    private int rodzicId;
    private int kontoId;

    @Id
    @Column(name = "uczen_id", nullable = false)
    public int getUczenId() {
        return uczenId;
    }

    public void setUczenId(int uczenId) {
        this.uczenId = uczenId;
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
    @Column(name = "klasa_id", nullable = false)
    public int getKlasaId() {
        return klasaId;
    }

    public void setKlasaId(int klasaId) {
        this.klasaId = klasaId;
    }

    @Basic
    @Column(name = "rodzic_id", nullable = false)
    public int getRodzicId() {
        return rodzicId;
    }

    public void setRodzicId(int rodzicId) {
        this.rodzicId = rodzicId;
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
        Uczniowie uczniowie = (Uczniowie) o;
        return uczenId == uczniowie.uczenId &&
                klasaId == uczniowie.klasaId &&
                rodzicId == uczniowie.rodzicId &&
                kontoId == uczniowie.kontoId &&
                Objects.equals(imie, uczniowie.imie) &&
                Objects.equals(nazwisko, uczniowie.nazwisko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uczenId, imie, nazwisko, klasaId, rodzicId, kontoId);
    }
}
