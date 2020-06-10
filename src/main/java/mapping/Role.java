/**
 * Package mapping przechowuje obiekty, ktore generuje Hibernate na podstawie naszej bazy danych
 */
package mapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Klasa Rodzce, przechowuje informacje o rodzicach danego ucznia.
 *
 * @author Adrian Hrycaj, Jakub Gałuszka, Paweł Kolano, Mateusz Jedziniak, Aleksander Jewuła
 * @version 1.0
 * @since   2020-05-11
 */
@Entity
public class Role {
    /**
     * Zmienna przechowująca klucz główny 'id' naszej klasy Role.
     */
    private int rolaId;
    /**
     * Zmienna przechowująca nazwe Roli.
     */
    private String nazwaRoli;
    /**
     * Zmienna przechowująca konta o danej roli np. nauczycieli.
     */
    private Collection<Konta> kontasByRolaId;

    @Id
    @Column(name = "rola_id", nullable = false)
    public int getRolaId() {
        return rolaId;
    }

    public void setRolaId(int rolaId) {
        this.rolaId = rolaId;
    }

    @Basic
    @Column(name = "nazwa_roli", nullable = false, length = 100)
    public String getNazwaRoli() {
        return nazwaRoli;
    }

    public void setNazwaRoli(String nazwaRoli) {
        this.nazwaRoli = nazwaRoli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return rolaId == role.rolaId &&
                Objects.equals(nazwaRoli, role.nazwaRoli);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolaId, nazwaRoli);
    }

    @OneToMany(mappedBy = "roleByRolaId")
    public Collection<Konta> getKontasByRolaId() {
        return kontasByRolaId;
    }

    public void setKontasByRolaId(Collection<Konta> kontasByRolaId) {
        this.kontasByRolaId = kontasByRolaId;
    }
}
