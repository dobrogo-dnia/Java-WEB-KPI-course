package org.example.javawebkpicourse.dto.journeyPlan;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JourneyPlanEntryDto {

    @NotNull(message = "Space Route cannot be blank")
    String spaceRoute;

    @NotNull(message = "Number of suitcases must be specified")
    int numOfJourneyPlans;

}
