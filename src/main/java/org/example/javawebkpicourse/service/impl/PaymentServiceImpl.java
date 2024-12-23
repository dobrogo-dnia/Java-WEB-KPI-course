package org.example.javawebkpicourse.service.impl;

import org.example.javawebkpicourse.domain.payment.Payment;
import org.example.javawebkpicourse.domain.payment.PaymentTransaction;
import org.example.javawebkpicourse.dto.payment.PaymentTravellerRequestDto;
import org.example.javawebkpicourse.dto.payment.PaymentTravellerResponseDto;
import org.example.javawebkpicourse.service.PaymentService;
import org.example.javawebkpicourse.service.exception.TravellerFailedProcessPayment;
import org.example.javawebkpicourse.service.mapper.PaymentServiceMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    private final RestClient paymentClient;
    private final PaymentServiceMapper paymentServiceMapper;
    private final String paymentServiceEndpoint;

    public PaymentServiceImpl(@Qualifier("paymentRestClient") RestClient paymentClient, PaymentServiceMapper paymentServiceMapper,
                              @Value("${application.payment-service.payments}") String paymentServiceEndpoint) {
        this.paymentClient = paymentClient;
        this.paymentServiceMapper = paymentServiceMapper;
        this.paymentServiceEndpoint = paymentServiceEndpoint;
    }

    public PaymentTransaction processPayment(Payment payment) {
        log.info("Processing payment for tour with id {} and traveller reference: {}", payment.getTourId(), payment.getTravellerName());
        PaymentTravellerRequestDto paymentTravellerRequestDto = paymentServiceMapper.toPaymentTravellerRequestDto(payment);

        PaymentTravellerResponseDto paymentTravellerResponseDto = paymentClient.post()
                .uri(paymentServiceEndpoint)
                .body(paymentTravellerRequestDto)
                .contentType(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    log.error("Server response failed to fetch payment link. Response Code {}", response.getStatusCode());
                    throw new TravellerFailedProcessPayment(payment.getTourId(), payment.getTravellerName());
                })
                .body(PaymentTravellerResponseDto.class);

        return paymentServiceMapper.toPaymentTransaction(payment.getTourId(), paymentTravellerResponseDto);
    }

}
