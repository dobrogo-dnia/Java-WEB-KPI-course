package org.example.javawebkpicourse.service;

import org.example.javawebkpicourse.domain.journeyPlan.JourneyPlan;
import org.example.javawebkpicourse.domain.journeyPlan.JourneyPlanContext;

public interface JourneyPlanService {

    JourneyPlan placeJourneyPlan(JourneyPlanContext journeyPlanContext);

}
