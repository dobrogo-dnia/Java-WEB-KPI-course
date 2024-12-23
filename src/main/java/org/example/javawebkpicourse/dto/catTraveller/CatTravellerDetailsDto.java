package org.example.javawebkpicourse.dto.catTraveller;

import org.example.javawebkpicourse.dto.validation.ExtendedValidation;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
@GroupSequence({CatTravellerDetailsDto.class, ExtendedValidation.class})
public class CatTravellerDetailsDto {

    @NotBlank(message = "Name is required")
    @Size(max = 80, message = "Name cannot exceed 80 characters")
    String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    String email;

    @NotBlank(message = "Breed is required")
    @Size(max = 50, message = "Breed cannot exceed 50 characters")
    String breed;

    @NotBlank(message = "Home Planet is required")
    @Size(max = 50, message = "Your home planet cannot exceed 50 characters. If the name of the planet is longer, please write the intergalactic abbreviation")
    String homePlanet;

    List<String> favouriteRoute;

}
