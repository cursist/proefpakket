package be.vdab.proefpakket.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OndernemingsnummerValidator implements ConstraintValidator<Ondernemingsnummer, Long> {
   @Override
   public void initialize(Ondernemingsnummer constraint) {}

   @Override
   public boolean isValid(Long nummer, ConstraintValidatorContext context) {
      long deLaatsteTweeCijfers = nummer % 100;
      long deEersteTienCijfers = nummer / 100;
      return deLaatsteTweeCijfers == 97 - deEersteTienCijfers % 97;
   }
}
