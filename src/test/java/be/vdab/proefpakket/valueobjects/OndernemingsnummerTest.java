package be.vdab.proefpakket.valueobjects;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.Assert.*;

public class OndernemingsnummerTest {
    private Validator validator;

    @Before
    public void setUp() {
        var factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void nummerOk() {
        assertTrue(
                validator.validateValue(
                        Ondernemingsnummer.class,
                        "nummer",
                        426388541L)
                .isEmpty()
        );
    }

    @Test
    public void nummerOk2() {
        assertTrue(
                validator.validateValue(
                        Ondernemingsnummer.class,
                        "nummer",
                        9797L)
                        .isEmpty()
        );
    }

    @Test
    public void nummerNietOk() {
        assertFalse(
                validator.validateValue(
                        Ondernemingsnummer.class,
                        "nummer",
                        426388542L)
                .isEmpty()
        );
    }
}