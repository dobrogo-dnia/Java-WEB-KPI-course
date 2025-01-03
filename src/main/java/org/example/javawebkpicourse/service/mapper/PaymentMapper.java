package org.example.javawebkpicourse.service.mapper;

import org.example.javawebkpicourse.domain.journeyPlan.JourneyPlanContext;
import org.example.javawebkpicourse.domain.payment.Payment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "travellerName", source = "travellerName")
    @Mapping(target = "tourId", source = "tourId")
    @Mapping(target = "travellerAmount", source = "totalPrice")
    @Mapping(target = "paymentAssetId", ignore = true)
    Payment toPayment(JourneyPlanContext journeyPlanContext);

}
