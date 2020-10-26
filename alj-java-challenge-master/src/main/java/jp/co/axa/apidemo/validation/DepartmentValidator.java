package jp.co.axa.apidemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

/**
 * This Class is for Department Validator , Restriction of Department during Save
 * 
 * */
public class DepartmentValidator implements ConstraintValidator<Department, String> {

    List<String> departments = Arrays.asList("IT", "COE", "ADMIN", "SALES");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return departments.contains(value);
    }
}