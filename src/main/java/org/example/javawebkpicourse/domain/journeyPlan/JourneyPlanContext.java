package org.example.javawebkpicourse.domain.journeyPlan;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JourneyPlanContext {

    String tourId;
    String travellerName;
    List<JourneyPlanEntry> journeyPlanEntries;
    Double totalPrice;

}
