package be.vdab.proefpakket.services;

import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.repositories.BestellingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BestellingService {
    private final BestellingRepository repository;

    public BestellingService(BestellingRepository repository) {
        this.repository = repository;
    }

    public void create(Bestelling bestelling) {
        repository.save(bestelling);
    }
}
