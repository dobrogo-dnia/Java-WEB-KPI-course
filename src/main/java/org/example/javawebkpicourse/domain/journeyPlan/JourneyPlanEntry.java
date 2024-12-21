package org.example.javawebkpicourse.domain.journeyPlan;

import org.example.javawebkpicourse.common.SpaceRoute;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class JourneyPlanEntry {

    SpaceRoute spaceRoute;
    int travellerAmount;

}
