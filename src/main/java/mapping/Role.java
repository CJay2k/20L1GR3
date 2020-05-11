package mapping;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Role {
    private int rolaId;
    private int nazwaRoli;

    @Id
    @Column(name = "rola_id", nullable = false)
    public int getRolaId() {
        return rolaId;
    }

    public void setRolaId(int rolaId) {
        this.rolaId = rolaId;
    }

    @Basic
    @Column(name = "nazwa_roli", nullable = false)
    public int getNazwaRoli() {
        return nazwaRoli;
    }

    public void setNazwaRoli(int nazwaRoli) {
        this.nazwaRoli = nazwaRoli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return rolaId == role.rolaId &&
                nazwaRoli == role.nazwaRoli;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolaId, nazwaRoli);
    }
}
