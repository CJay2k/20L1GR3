package mapping;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Konta {
    private int kontoId;
    private String login;
    private String haslo;
    private int rolaId;

    @Id
    @Column(name = "konto_id", nullable = false)
    public int getKontoId() {
        return kontoId;
    }

    public void setKontoId(int kontoId) {
        this.kontoId = kontoId;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 100)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "haslo", nullable = false, length = 100)
    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Basic
    @Column(name = "rola_id", nullable = false)
    public int getRolaId() {
        return rolaId;
    }

    public void setRolaId(int rolaId) {
        this.rolaId = rolaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Konta konta = (Konta) o;
        return kontoId == konta.kontoId &&
                rolaId == konta.rolaId &&
                Objects.equals(login, konta.login) &&
                Objects.equals(haslo, konta.haslo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kontoId, login, haslo, rolaId);
    }
}
