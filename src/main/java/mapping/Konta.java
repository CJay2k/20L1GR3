/**
 * Package mapping przechowuje obiekty, ktore generuje Hibernate na podstawie naszej bazy danych
 */

package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Klasa Konta, przechowuje informacje o danym użytkowniku naszej aplikacji.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-05-11
 */

@Entity
public class Konta {
    /**
     * Zmienna przechowujaca klucz główny naszej Konta.
     */
    private int kontoId;
    /**
     * Zmienna przechowujaca login naszego użytkownika.
     */
    private String login;
    /**
     * Zmienna przechowujaca hasło naszego użytkownika.
     */
    private String haslo;
    /**
     * Zmienna przechowujaca klucz główny klasy Role.
     */
    private Role roleByRolaId;
    /**
     * Zmienna przechowujaca wszystkie konta nauczycieli.
     */
    private Collection<Nauczyciele> nauczycielesByKontoId;
    /**
     Zmienna przechowujaca wszystkie konta rodziców.
     */
    private Collection<Rodzice> rodzicesByKontoId;
    /**
     * Zmienna przechowujaca wszystkie konta uczniów.
     */
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
