package be.vdab.proefpakket.services;

import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.valueobjects.Ondernemingsnummer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(BrouwerService.class)
@Sql("/insertBrouwer.sql")
public class BrouwerServiceTest
        extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired BrouwerService service;

    @Test
    public void findByNaamStartsWith() {
        var lijstVanBrouwersNamen =
                service.findByNaamStartsWith("test")
                       .stream()
                       .map(Brouwer::getNaam)
                       .collect(toList());
        assertTrue(lijstVanBrouwersNamen.contains("testBrouwer"));
    }

    @Test
    public void setOndernemingsnummerById() {
        var brouwerVooraf = getTestBrouwer();
        var nummer = new Ondernemingsnummer(42L);
        assertNull(brouwerVooraf.getOndernemingsnummer());

        var brouwerAchteraf = getTestBrouwer();
        service.setOndernemingsnummerById(brouwerVooraf.getId(), nummer);
        assertEquals(nummer, brouwerAchteraf.getOndernemingsnummer());
    }

    private Brouwer getTestBrouwer() {
        return service.findByNaamStartsWith("testBrouwer")
                .get(0);
    }
}