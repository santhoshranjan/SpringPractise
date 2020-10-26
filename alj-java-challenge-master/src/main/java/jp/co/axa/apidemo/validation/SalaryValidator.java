package jp.co.axa.apidemo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



/**
 * This Class is for Salary Validator , Restriction of Salary during Save
 * 
 * */
public class SalaryValidator implements ConstraintValidator<Salary, Integer> {

  
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        if(value > 10000000 || value < 8000000){
        	return false;
        }else{
        	return true;
        }

    }
}