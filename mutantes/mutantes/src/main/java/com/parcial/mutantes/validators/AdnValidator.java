package com.parcial.mutantes.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdnValidator implements ConstraintValidator<ValidAdn, String[]> {
    private static final String VALID_CHARACTERS= "AGTC";

    @Override
    public void initialize(ValidAdn constrainAnnotaion){

    }

    @Override
    public boolean isValid(String[] adn, ConstraintValidatorContext constraintValidatorContext){
        if (adn== null){
            return false;
        }
    int n=adn.length;
        if (n==0){
            return false;

        }
        for (String sequence: adn){
            if (sequence==null || sequence.length()!= n){
                return false;

            }
            for (char c: sequence.toCharArray()){
                if(VALID_CHARACTERS.indexOf(c)==-1){
                    return false;
                }
            }
        }
        return true;
}

}
