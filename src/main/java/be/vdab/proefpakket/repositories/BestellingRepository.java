package be.vdab.proefpakket.repositories;

import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.entities.Brouwer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BestellingRepository extends JpaRepository<Bestelling, Long> {
}
