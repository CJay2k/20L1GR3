package mapping;

import java.util.HashSet;
import java.util.Set;

public class RodzajOceny implements java.io.Serializable {

    private String rodzajOceny;
    private Set ocenas = new HashSet(0);

    public RodzajOceny() {
    }

    public RodzajOceny(String rodzajOceny) {
        this.rodzajOceny = rodzajOceny;
    }

    public RodzajOceny(String rodzajOceny, Set ocenas) {
        this.rodzajOceny = rodzajOceny;
        this.ocenas = ocenas;
    }

    public String getRodzajOceny() {
        return this.rodzajOceny;
    }

    public void setRodzajOceny(String rodzajOceny) {
        this.rodzajOceny = rodzajOceny;
    }

    public Set getOcenas() {
        return this.ocenas;
    }

    public void setOcenas(Set ocenas) {
        this.ocenas = ocenas;
    }


}


