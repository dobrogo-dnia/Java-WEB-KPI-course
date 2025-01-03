package org.example.javawebkpicourse.dto.journeyPlan;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PlaceJourneyPlanResponseDto {

    String journeyPlanId;

    UUID paymentId;

}
