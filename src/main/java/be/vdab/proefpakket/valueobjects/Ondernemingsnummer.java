package be.vdab.proefpakket.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
}
