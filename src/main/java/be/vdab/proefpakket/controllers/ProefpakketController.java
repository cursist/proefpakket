package be.vdab.proefpakket.controllers;

import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.entities.Gemeente;
import be.vdab.proefpakket.exceptions.NietGevondenException;
import be.vdab.proefpakket.services.BestellingService;
import be.vdab.proefpakket.services.GemeenteService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("proefpakket")
@SessionAttributes({Bestelling.BESTELLING, Brouwer.BROUWER})
public class ProefpakketController {
    private static final String PAGINA_DEEL_1 = "proefpakket-stap1";
    private static final String PAGINA_DEEL_2 = "proefpakket-stap2";
    private static final String URI_PATH = "/{optionalBrouwer}";

    private final BestellingService service;
    private final GemeenteService gemeenteService;

    public ProefpakketController(BestellingService service, GemeenteService gemeenteService) {
        this.service = service;
        this.gemeenteService = gemeenteService;
    }

    @GetMapping(URI_PATH)
    ModelAndView stap1(@PathVariable Optional<Brouwer> optionalBrouwer) {
        var modelAndView = new ModelAndView(PAGINA_DEEL_1);
        if (optionalBrouwer.isPresent()) {
            var brouwer = optionalBrouwer.get();
            return modelAndView
                    .addObject(brouwer)
                    .addObject(new Bestelling(brouwer));
        } else throw new NietGevondenException();
    }

    @PostMapping(value = URI_PATH, params = "stap2")
    ModelAndView naarStap2(
            @PathVariable Optional<Brouwer> optionalBrouwer,
            @Validated(Bestelling.Stap1.class) Bestelling bestelling,
            Errors errors
    ) {
        if (errors.hasErrors()) {
            return new ModelAndView(PAGINA_DEEL_1);
        } else {
            var alleGemeenten = gemeenteService.findAll();
            return new ModelAndView(PAGINA_DEEL_2)
                    .addObject("gemeenten", alleGemeenten);
        }
    }

    @PostMapping(value = URI_PATH, params = "stap1")
    String stap1opnieuw() {
        return PAGINA_DEEL_1;
    }

    @PostMapping(value= URI_PATH, params = "opslaan")
    String opslaan(
            @Validated(Bestelling.Stap2.class) Bestelling bestelling,
            Errors errors, SessionStatus session
    ) {
        if (errors.hasErrors()) {
            return PAGINA_DEEL_2;
        } else {
            service.create(bestelling);
            session.setComplete();
            return "redirect:/";
        }
    }

    @InitBinder(Bestelling.BESTELLING)
    void initBinder0(DataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @InitBinder("gemeente")
    void initBinder1(DataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @InitBinder(Brouwer.BROUWER)
    void initBinder2(DataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @ExceptionHandler(NietGevondenException.class)
    @ResponseStatus(NOT_FOUND)
    void nietGevonden() {}

}
