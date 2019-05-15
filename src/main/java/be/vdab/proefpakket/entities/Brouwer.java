package be.vdab.proefpakket.entities;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "brouwers")
public class Brouwer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String naam, straat, huisNr;

    @ManyToOne
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
}
