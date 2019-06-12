package be.vdab.proefpakket.entities;

import be.vdab.proefpakket.valueobjects.Ondernemingsnummer;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "brouwers")
public class Brouwer {
    public final static String BROUWER = "brouwer";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String naam, straat, huisNr;

    @Embedded
    private Ondernemingsnummer ondernemingsnummer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "gemeenteId")
    private Gemeente gemeente;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public Gemeente getGemeente() {
        return gemeente;
    }

    public Ondernemingsnummer getOndernemingsnummer() {
        return ondernemingsnummer;
    }

    public void setOndernemingsnummer(Ondernemingsnummer ondernemingsnummer) {
        this.ondernemingsnummer = ondernemingsnummer;
    }
}
