package be.vdab.proefpakket.services;

import be.vdab.proefpakket.entities.Gemeente;
import be.vdab.proefpakket.repositories.GemeenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GemeenteService {
    private final GemeenteRepository repository;

    public GemeenteService(GemeenteRepository repository) {
        this.repository = repository;
    }

    public List<Gemeente> findAll() {
        return repository.findAll();
    }
}
