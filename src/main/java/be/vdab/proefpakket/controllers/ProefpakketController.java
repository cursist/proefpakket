package be.vdab.proefpakket.controllers;

import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.services.BestellingService;
import be.vdab.proefpakket.services.GemeenteService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("proefpakket")
@SessionAttributes("bestelling")
public class ProefpakketController {
    private final BestellingService service;
    private final GemeenteService gemeenteService;

    public ProefpakketController(BestellingService service, GemeenteService gemeenteService) {
        this.service = service;
        this.gemeenteService = gemeenteService;
    }

    @GetMapping("/{optionalBrouwer}")
    ModelAndView stap1(@PathVariable Optional<Brouwer> optionalBrouwer) {
        return getStap1(optionalBrouwer)
                .addObject(new Bestelling());
    }

    @PostMapping(value = "/{optionalBrouwer}", params = "stap2")
    ModelAndView stap2(@PathVariable Optional<Brouwer> optionalBrouwer, @Valid Bestelling bestelling, Errors errors) {
        if (errors.hasErrors()) {
            return getStap1(optionalBrouwer);
        } else {
            var alleGemeenten = gemeenteService.findAll()
                    .stream()
                    .map(gemeente -> gemeente.getNaam() + ' ' + gemeente.getPostcode())
                    .collect(Collectors.toList());
            return new ModelAndView("proefpakket-stap2")
                    .addObject("gemeenten", alleGemeenten);
        }
    }

    @PostMapping(value = "/{optionalBrouwer}", params = "stap1")
    String stap1opnieuw() {
        return "proefpakket-stap1";
    }

    @PostMapping(value="/{optionalBrouwer}", params = "opslaan")
    String opslaan(@Validated(Bestelling.Stap1.class) Bestelling bestelling, Errors errors, SessionStatus session) {
        if (errors.hasErrors()) {
            return "proefpakket-stap2";
        } else {
            service.create(bestelling);
            session.setComplete();
            return "redirect:/";
        }
    }

    private ModelAndView getStap1(Optional<Brouwer> optionalBrouwer) {
        var modelAndView = new ModelAndView("proefpakket-stap1");
        optionalBrouwer.ifPresent(modelAndView::addObject);
        return modelAndView;
    }

    @InitBinder("bestelling")
    void initBinder0(DataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @InitBinder("gemeente")
    void initBinder1(DataBinder binder) {
        binder.initDirectFieldAccess();
    }

}
