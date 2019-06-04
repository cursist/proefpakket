package be.vdab.proefpakket.valueobjects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Adres {
    @NotBlank
    private String straat, huisNr;
}
