package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Konta {
    private int kontoId;
    private String login;
    private String haslo;
    private Role roleByRolaId;
    private Collection<Nauczyciele> nauczycielesByKontoId;
    private Collection<Rodzice> rodzicesByKontoId;
    private Collection<Uczniowie> uczniowiesByKontoId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Konta konta = (Konta) o;
        return kontoId == konta.kontoId &&
                Objects.equals(login, konta.login) &&
                Objects.equals(haslo, konta.haslo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kontoId, login, haslo);
    }

    @ManyToOne
    @JoinColumn(name = "rola_id", referencedColumnName = "rola_id", nullable = false)
    public Role getRoleByRolaId() {
        return roleByRolaId;
    }

    public void setRoleByRolaId(Role roleByRolaId) {
        this.roleByRolaId = roleByRolaId;
    }

    @OneToMany(mappedBy = "kontaByKontoId")
    public Collection<Nauczyciele> getNauczycielesByKontoId() {
        return nauczycielesByKontoId;
    }

    public void setNauczycielesByKontoId(Collection<Nauczyciele> nauczycielesByKontoId) {
        this.nauczycielesByKontoId = nauczycielesByKontoId;
    }

    @OneToMany(mappedBy = "kontaByKontoId")
    public Collection<Rodzice> getRodzicesByKontoId() {
        return rodzicesByKontoId;
    }

    public void setRodzicesByKontoId(Collection<Rodzice> rodzicesByKontoId) {
        this.rodzicesByKontoId = rodzicesByKontoId;
    }

    @OneToMany(mappedBy = "kontaByKontoId")
    public Collection<Uczniowie> getUczniowiesByKontoId() {
        return uczniowiesByKontoId;
    }

    public void setUczniowiesByKontoId(Collection<Uczniowie> uczniowiesByKontoId) {
        this.uczniowiesByKontoId = uczniowiesByKontoId;
    }
}
