package org.example.javawebkpicourse.domain.journeyPlan;

import java.util.List;
import java.util.UUID;
import lombok.Value;
import lombok.Builder;

@Value
@Builder(toBuilder = true)
public class JourneyPlan {

    String id;
    UUID paymentId;
    List<JourneyPlanEntry> journeyPlanEntries;
    String tourId;
    String travellerName;
    Double totalPrice;

}
