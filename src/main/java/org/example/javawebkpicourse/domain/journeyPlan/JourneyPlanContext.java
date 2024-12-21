package org.example.javawebkpicourse.domain.journeyPlan;

import org.example.javawebkpicourse.common.SpaceRoute;
import org.example.javawebkpicourse.dto.journeyPlan.JourneyPlanEntryDto;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JourneyPlanContext {

    String tourId;
    String travellerName;
    List<JourneyPlanEntry> entries;
    Double totalPrice;

}
