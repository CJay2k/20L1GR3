package mapping;

public class SkladKlasy implements java.io.Serializable {

    private Integer id;
    private Klasa klasa;
    private Uczen uczen;

    public SkladKlasy() {
    }

    public SkladKlasy(Klasa klasa, Uczen uczen) {
        this.klasa = klasa;
        this.uczen = uczen;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Klasa getKlasa() {
        return this.klasa;
    }

    public void setKlasa(Klasa klasa) {
        this.klasa = klasa;
    }

    public Uczen getUczen() {
        return this.uczen;
    }

    public void setUczen(Uczen uczen) {
        this.uczen = uczen;
    }


}


