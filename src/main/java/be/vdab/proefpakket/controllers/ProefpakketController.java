package be.vdab.proefpakket.controllers;

import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.services.BestellingService;
import be.vdab.proefpakket.services.BrouwerService;
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
@RequestMapping("proefpakket")
public class ProefpakketController {
    private final BestellingService service;

    public ProefpakketController(BestellingService service) {
        this.service = service;
    }

    @GetMapping("/{optionalBrouwer}")
    ModelAndView stap1(@PathVariable Optional<Brouwer> optionalBrouwer) {
        return getStap1MetBestelling(optionalBrouwer, new Bestelling());
    }

    @PostMapping(value = "/{optionalBrouwer}", params = "stap2")
    ModelAndView stap2(@PathVariable Optional<Brouwer> optionalBrouwer, @Valid Bestelling bestelling, Errors errors) {
        if (errors.hasErrors()) {
            return getStap1MetBestelling(optionalBrouwer, bestelling);
        } else {
            return new ModelAndView("proefpakket-stap2");
        }
    }

    private ModelAndView getStap1MetBestelling(Optional<Brouwer> optionalBrouwer, Bestelling bestelling) {
        var modelAndView = new ModelAndView("proefpakket-stap1")
                .addObject(bestelling);
        optionalBrouwer.ifPresent(modelAndView::addObject);
        return modelAndView;
    }

}
