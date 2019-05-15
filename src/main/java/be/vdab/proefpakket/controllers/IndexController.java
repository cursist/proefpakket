package be.vdab.proefpakket.controllers;

import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.services.BrouwerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {
    private final static char[] ALFABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final BrouwerService service;

    public IndexController(BrouwerService service) {
        this.service = service;
    }

    @GetMapping
    ModelAndView index() {
        return new ModelAndView("index")
                .addObject("alfabet", ALFABET);
    }

    @GetMapping("letter/{letter}")
    ModelAndView brouwersPerLetter(@PathVariable String letter) {
        return index()
                .addObject("brouwers", service.findByNaamStartsWith(letter));
    }

    @GetMapping("brouwer/{optionalBrouwer}")
    ModelAndView brouwer(@PathVariable Optional<Brouwer> optionalBrouwer) {
        var modelAndView = new ModelAndView("brouwer");
        if (optionalBrouwer.isPresent()) {
            modelAndView.addObject("brouwer", optionalBrouwer.get());
        }
        return modelAndView;
    }
}
