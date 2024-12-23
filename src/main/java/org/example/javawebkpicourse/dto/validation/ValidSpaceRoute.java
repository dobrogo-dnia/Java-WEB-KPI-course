package org.example.javawebkpicourse.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.TYPE_USE, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = SpaceRouteValidator.class)
@Documented
public @interface ValidSpaceRoute {

    String SPACE_ROUTE_SHOULD_BE_VALID = "\"Invalid Space Route: The provided route does not conform to the required format. Please ensure that it includes valid coordinates, sector, and planet information. Example: 'Sector 5, Planet Zeta, Quadrant 12'.\"\n";

    String message() default SPACE_ROUTE_SHOULD_BE_VALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
