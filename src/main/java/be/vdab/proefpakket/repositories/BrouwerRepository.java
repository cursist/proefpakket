package be.vdab.proefpakket.repositories;

import be.vdab.proefpakket.entities.Brouwer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrouwerRepository extends JpaRepository<Brouwer, Long> {
    List<Brouwer> findByNaamStartsWith(String begin);
}
