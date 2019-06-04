package be.vdab.proefpakket.controllers;

import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.repositories.BrouwerRepository;
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
        var ondernemingsnummer = new Ondernemingsnummer(42L);
        return getPaginaMetNummer(optionalBrouwer, ondernemingsnummer);
    }

    @PostMapping("/{optionalBrouwer}/post")
    ModelAndView post(@PathVariable Optional<Brouwer> optionalBrouwer, @Valid Ondernemingsnummer ondernemingsnummer, Errors errors) {
        if (errors.hasErrors()) {
            return getPaginaMetNummer(optionalBrouwer, ondernemingsnummer);
        } else if (optionalBrouwer.isPresent()) {
            var brouwer = optionalBrouwer.get();
            long id = brouwer.getId();
            service.setOndernemingsnummerById(id, ondernemingsnummer);
            return new ModelAndView("redirect:/brouwer/" + id);
        } else {
            return new ModelAndView("redirect:/brouwer/404");
        }
    }

    private ModelAndView getPaginaMetNummer(Optional<Brouwer> optionalBrouwer, Ondernemingsnummer ondernemingsnummer) {
        var modelAndView = new ModelAndView("ondernemingsnummer")
                .addObject(ondernemingsnummer);
        optionalBrouwer.ifPresent(modelAndView::addObject);
        return modelAndView;
    }

}
