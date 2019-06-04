package be.vdab.proefpakket.entities;

import be.vdab.proefpakket.valueobjects.Adres;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "bestellingen")
public class Bestelling {
    public interface Stap1 {}
    public interface Stap2 {}

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NotBlank(groups = Stap1.class)
    private String voornaam, familienaam, emailAdres;

    @NotNull(groups = Stap2.class)
    @Embedded
    private Adres adres;

    @ManyToOne
    @NotNull(groups = Stap2.class)
    private Gemeente gemeente;

    @PositiveOrZero(groups = Stap2.class)
    private long brouwerId;

    public String getVoornaam() {
        return voornaam;
    }
    public String getFamilienaam() {
        return familienaam;
    }
    public String getEmailAdres() {
        return emailAdres;
    }
    public Adres getAdres() {
        return adres;
    }
    public Gemeente getGemeente() {
        return gemeente;
    }
    public long getBrouwerId() {
        return brouwerId;
    }
}
