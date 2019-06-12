package be.vdab.proefpakket.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "gemeenten")
public class Gemeente {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NotBlank
    private String naam;
    @Min(1000)
    @Max(9999)
    private short postcode;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public short getPostcode() {
        return postcode;
    }
}
