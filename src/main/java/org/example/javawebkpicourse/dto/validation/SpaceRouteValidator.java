package org.example.javawebkpicourse.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;

@Component
public class SpaceRouteValidator implements ConstraintValidator<ValidSpaceRoute, String> {

    private static final String ROUTE_NAME_PATTERN = "^Sector \\d+, Planet [A-Za-z]+, Quadrant \\d+$";

    private static final Pattern pattern = Pattern.compile(ROUTE_NAME_PATTERN);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && pattern.matcher(value).matches();

    }
}
