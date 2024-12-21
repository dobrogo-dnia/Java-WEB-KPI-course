package org.example.javawebkpicourse.dto.journeyPlan;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PlaceJourneyPlanRequestDto {

    @NotNull(message = "Journey entries cannot be null")
    List<JourneyPlanEntryDto> journeyPlanEntries;

    @NotNull(message = "Total price cannot be null")
    @Min(value = 0, message = "Total price must be non-negative")
    Double totalPrice;

}
