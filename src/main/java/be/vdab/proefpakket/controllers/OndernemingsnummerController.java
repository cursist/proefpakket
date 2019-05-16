package be.vdab.proefpakket.controllers;

import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.services.BrouwerService;
import be.vdab.proefpakket.valueobjects.Ondernemingsnummer;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("ondernemingsnummer")
public class OndernemingsnummerController {
    private final BrouwerService service;

    public OndernemingsnummerController(BrouwerService service) {
        this.service = service;
    }

    @GetMapping("/{optionalBrouwer}")
    ModelAndView form(@PathVariable Optional<Brouwer> optionalBrouwer) {
        var ondernemingsnummer = new Ondernemingsnummer(null);
        return getPaginaMetNummer(optionalBrouwer, ondernemingsnummer);
    }

    @PostMapping("/{optionalBrouwer}/post")
    ModelAndView post(@PathVariable Optional<Brouwer> optionalBrouwer, @Valid Ondernemingsnummer nummer, Errors errors) {
        if (errors.hasErrors()) {
            return getPaginaMetNummer(optionalBrouwer, nummer);
        } else {
            optionalBrouwer.ifPresent(brouwer -> brouwer.setOndernemingsnummer(nummer));
            return new ModelAndView("ondernemingsnummer/" + nummer);
        }
    }

    private ModelAndView getPaginaMetNummer(Optional<Brouwer> optionalBrouwer, Ondernemingsnummer ondernemingsnummer) {
        var modelAndView = new ModelAndView("ondernemingsnummer");
        if (optionalBrouwer.isPresent()) {
            modelAndView
                    .addObject("brouwer", optionalBrouwer.get())
                    .addObject("ondernemingsnummer", ondernemingsnummer);
        }
        return modelAndView;
    }

}
