package be.vdab.proefpakket.services;

import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.repositories.BrouwerRepository;
import be.vdab.proefpakket.valueobjects.Ondernemingsnummer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BrouwerService {
    private final BrouwerRepository repository;

    public BrouwerService(BrouwerRepository repository) {
        this.repository = repository;
    }

    public List<Brouwer> findByNaamStartsWith(String begin) {
        return repository.findByNaamStartsWith(begin);
    }

    @Transactional(readOnly = false)
    public void setOndernemingsnummerById(Long id, Ondernemingsnummer ondernemingsnummer) {
        var optionalBrouwer = repository.findById(id);
        if (optionalBrouwer.isPresent()) {
            var brouwer = optionalBrouwer.get();
            brouwer.setOndernemingsnummer(ondernemingsnummer);
        }
    }

}
