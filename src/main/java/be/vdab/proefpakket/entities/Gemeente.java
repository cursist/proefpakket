package be.vdab.proefpakket.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "gemeenten")
public class Gemeente {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String naam;
    private short postcode;

    public String getNaam() {
        return naam;
    }

    public short getPostcode() {
        return postcode;
    }
}
