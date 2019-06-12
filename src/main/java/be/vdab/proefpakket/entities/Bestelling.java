package be.vdab.proefpakket.entities;

import be.vdab.proefpakket.valueobjects.Adres;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "bestellingen")
public class Bestelling {
    public final static String BESTELLING = "bestelling";
    public interface Stap1 {}
    public interface Stap2 {}

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private LocalDate datum = LocalDate.now();

    @NotBlank(groups = Stap1.class)
    private String voornaam, familienaam, emailAdres;

    @NotNull(groups = Stap2.class)
    @Embedded
    private Adres adres;

    @ManyToOne
    @JoinColumn(name = "gemeenteId")
    @NotNull(groups = Stap2.class)
    private Gemeente gemeente;

    @OneToOne
    @JoinColumn(name = "brouwerId")
    @NotNull(groups = Stap1.class)
    private Brouwer brouwer;

    protected Bestelling() {}
    public Bestelling(Brouwer brouwer) {
        this.brouwer = brouwer;
    }

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
    public Brouwer getBrouwer() {
        return brouwer;
    }
}
