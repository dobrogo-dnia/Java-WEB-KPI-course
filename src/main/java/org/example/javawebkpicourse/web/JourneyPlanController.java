package org.example.javawebkpicourse.web;

import org.example.javawebkpicourse.domain.journeyPlan.JourneyPlanContext;
import org.example.javawebkpicourse.dto.journeyPlan.PlaceJourneyPlanRequestDto;
import org.example.javawebkpicourse.dto.journeyPlan.PlaceJourneyPlanResponseDto;
import org.example.javawebkpicourse.service.JourneyPlanService;
import org.example.javawebkpicourse.web.mapper.JourneyPlanDtoMapper;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
@RequestMapping("/api/v1/{travellerName}/journeyPlans")
@RequiredArgsConstructor
public class JourneyPlanController {

    private final JourneyPlanService journeyPlanService;
    private final JourneyPlanDtoMapper journeyPlanDtoMapper;

    @PostMapping("/{tourId}")
    public ResponseEntity<PlaceJourneyPlanResponseDto> placeJourneyPlan(
            @PathVariable("travellerName") String travellerName,
            @PathVariable("tourId") String tourId,
            @RequestBody @Valid PlaceJourneyPlanRequestDto placeJourneyPlanDto) {
        log.info("Placing the journey plan for tour with id : {}", tourId);
        JourneyPlanContext context = journeyPlanDtoMapper.toJourneyPlanContext(tourId, travellerName, placeJourneyPlanDto);
        return ResponseEntity.ok(journeyPlanDtoMapper.toPlaceJourneyPlanResponseDto(journeyPlanService.placeJourneyPlan(context)));

    }
}
