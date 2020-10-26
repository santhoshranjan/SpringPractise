package jp.co.axa.apidemo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = SalaryValidator.class)
@Documented
public @interface Salary {

    String message() default "Employee Salary Cannot be less than 8000000 and cannot be more than 100000000";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}