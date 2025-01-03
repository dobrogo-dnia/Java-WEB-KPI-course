package org.example.javawebkpicourse.web.mapper;

import org.example.javawebkpicourse.common.SpaceRoute;
import org.example.javawebkpicourse.domain.journeyPlan.JourneyPlan;
import org.example.javawebkpicourse.domain.journeyPlan.JourneyPlanContext;
import org.example.javawebkpicourse.domain.journeyPlan.JourneyPlanEntry;
import org.example.javawebkpicourse.dto.journeyPlan.JourneyPlanEntryDto;
import org.example.javawebkpicourse.dto.journeyPlan.PlaceJourneyPlanRequestDto;
import org.example.javawebkpicourse.dto.journeyPlan.PlaceJourneyPlanResponseDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JourneyPlanDtoMapper {

    @Mapping(target = "tourId", source = "tourId")
    @Mapping(target = "totalPrice", source = "journeyPlanDto.totalPrice")
    @Mapping(target = "travellerName", source = "travellerName")
    @Mapping(target = "journeyPlanEntries", source = "journeyPlanDto.journeyPlanEntries")
    JourneyPlanContext toJourneyPlanContext(String tourId, String travellerName, PlaceJourneyPlanRequestDto journeyPlanDto);

    @Mapping(target = "spaceRoute", source = "spaceRoute")
    @Mapping(target = "travellerAmount", source = "numOfJourneyPlans")
    JourneyPlanEntry toJourneyPlanEntry(JourneyPlanEntryDto journeyPlanEntryDto);

    default SpaceRoute toSpaceRoute(String spaceRoute) {
        return SpaceRoute.fromDisplayName(spaceRoute);
    }

    @Mapping(target = "journeyPlanId", source = "id")
    @Mapping(target = "paymentId", source = "paymentId")
    PlaceJourneyPlanResponseDto toPlaceJourneyPlanResponseDto(JourneyPlan journeyPlan);

}
