package be.vdab.proefpakket.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Ondernemingsnummer {
    @be.vdab.proefpakket.constraints.Ondernemingsnummer
    @Column(name = "ondernemingsNr")
    private Long nummer;

    public Ondernemingsnummer(Long nummer) {
        this.nummer = nummer;
    }

    protected Ondernemingsnummer() {}

    public Long getNummer() {
        return nummer;
    }

    public void setNummer(Long nummer) {
        this.nummer = nummer;
    }

    @Override
    public String toString() {
        return nummer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ondernemingsnummer that = (Ondernemingsnummer) o;
        return nummer.equals(that.nummer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nummer);
    }
}
