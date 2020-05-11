package mapping;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Rodzice {
    private int rodzicId;
    private String imieOjca;
    private String nazwiskoOjca;
    private String imieMatki;
    private String nazwiskoMatki;
    private int kontoId;

    @Id
    @Column(name = "rodzic_id", nullable = false)
    public int getRodzicId() {
        return rodzicId;
    }

    public void setRodzicId(int rodzicId) {
        this.rodzicId = rodzicId;
    }

    @Basic
    @Column(name = "imie_ojca", nullable = true, length = 100)
    public String getImieOjca() {
        return imieOjca;
    }

    public void setImieOjca(String imieOjca) {
        this.imieOjca = imieOjca;
    }

    @Basic
    @Column(name = "nazwisko_ojca", nullable = true, length = 100)
    public String getNazwiskoOjca() {
        return nazwiskoOjca;
    }

    public void setNazwiskoOjca(String nazwiskoOjca) {
        this.nazwiskoOjca = nazwiskoOjca;
    }

    @Basic
    @Column(name = "imie_matki", nullable = true, length = 100)
    public String getImieMatki() {
        return imieMatki;
    }

    public void setImieMatki(String imieMatki) {
        this.imieMatki = imieMatki;
    }

    @Basic
    @Column(name = "nazwisko_matki", nullable = true, length = 100)
    public String getNazwiskoMatki() {
        return nazwiskoMatki;
    }

    public void setNazwiskoMatki(String nazwiskoMatki) {
        this.nazwiskoMatki = nazwiskoMatki;
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
        Rodzice rodzice = (Rodzice) o;
        return rodzicId == rodzice.rodzicId &&
                kontoId == rodzice.kontoId &&
                Objects.equals(imieOjca, rodzice.imieOjca) &&
                Objects.equals(nazwiskoOjca, rodzice.nazwiskoOjca) &&
                Objects.equals(imieMatki, rodzice.imieMatki) &&
                Objects.equals(nazwiskoMatki, rodzice.nazwiskoMatki);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rodzicId, imieOjca, nazwiskoOjca, imieMatki, nazwiskoMatki, kontoId);
    }
}
