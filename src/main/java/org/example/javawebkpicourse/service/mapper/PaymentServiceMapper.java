package org.example.javawebkpicourse.service.mapper;

import org.example.javawebkpicourse.domain.payment.Payment;
import org.example.javawebkpicourse.domain.payment.PaymentTransaction;
import org.example.javawebkpicourse.dto.payment.PaymentTravellerRequestDto;
import org.example.javawebkpicourse.dto.payment.PaymentTravellerResponseDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentServiceMapper {

    @Mapping(target = "travellerName", source = "travellerName")
    @Mapping(target = "paymentAssetId", source = "paymentAssetId")
    @Mapping(target = "travellerAmount", source = "travellerAmount")
    PaymentTravellerRequestDto toPaymentTravellerRequestDto(Payment payment);

    @Mapping(target = "id", source = "paymentTravellerResponseDto.uuid")
    @Mapping(target = "status", source = "paymentTravellerResponseDto.status")
    @Mapping(target = "tourId", source = "tourId")
    PaymentTransaction toPaymentTransaction(String tourId, PaymentTravellerResponseDto paymentTravellerResponseDto);

}
