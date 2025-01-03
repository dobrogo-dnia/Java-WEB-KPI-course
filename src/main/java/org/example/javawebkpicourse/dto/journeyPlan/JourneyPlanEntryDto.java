package org.example.javawebkpicourse.dto.journeyPlan;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JourneyPlanEntryDto {

    @NotNull(message = "Space Route cannot be blank")
    String spaceRoute;

    @DecimalMin(message = "Number of journey plans must be specified", value = "1")
    int numOfJourneyPlans;

}
