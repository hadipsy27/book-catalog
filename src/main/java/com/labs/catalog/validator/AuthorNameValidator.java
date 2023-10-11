package com.labs.catalog.validator;

import com.labs.catalog.validator.annotation.ValidAuthorName;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class AuthorNameValidator implements ConstraintValidator<ValidAuthorName, String> {
    @Override
    public boolean isValid(String authorName, ConstraintValidatorContext constraintValidatorContext) {
        return !authorName.equalsIgnoreCase("Hadi");
    }
}
