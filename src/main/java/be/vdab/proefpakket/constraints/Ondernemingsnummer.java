package be.vdab.proefpakket.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({FIELD, TYPE, ANNOTATION_TYPE})
@Constraint(validatedBy = OndernemingsnummerValidator.class)
public @interface Ondernemingsnummer {
    String message() default "{be.vdab.proefpakket.constraints.Ondernemingsnummer.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
