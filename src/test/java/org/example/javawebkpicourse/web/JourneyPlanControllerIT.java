package org.example.javawebkpicourse.web;

import org.example.javawebkpicourse.AbstractIt;
import org.example.javawebkpicourse.common.PaymentStatus;
import org.example.javawebkpicourse.common.SpaceRoute;
import org.example.javawebkpicourse.dto.journeyPlan.JourneyPlanEntryDto;
import org.example.javawebkpicourse.dto.journeyPlan.PlaceJourneyPlanRequestDto;
import org.example.javawebkpicourse.dto.payment.PaymentTravellerResponseDto;
import org.example.javawebkpicourse.service.JourneyPlanService;
import org.example.javawebkpicourse.service.PaymentService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.test.web.servlet.MockMvc;

import static org.example.javawebkpicourse.service.exception.PaymentTransactionFailed.PAYMENT_TRANSACTION_WITH_ID_S_FOR_TOUR_ID_S_FAILED;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.mockito.Mockito.reset;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DisplayName("Journey Plan Controller IT")
@Tag("journeyPlan-service")
class JourneyPlanControllerIT extends AbstractIt {

    private static final String TOUR_ID_4321 = "tourId4321";
    private final PlaceJourneyPlanRequestDto PLACE_JOURNEY_PLAN_REQUEST_DTO = buildPlaceJourneyPlanRequestDto();
    private final String TRAVELLER_NAME = "travellerName";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private PaymentService paymentService;

    @SpyBean
    private JourneyPlanService journeyPlanService;

    @BeforeEach
    void setUp() {
        reset(paymentService, journeyPlanService);
    }

    @Test
    @SneakyThrows
    void shouldPlaceOrder() {
        UUID transaction = UUID.randomUUID();

        stubFor(WireMock.post("/payment-service/v1/payments")
                .willReturn(aResponse().withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsBytes(PaymentTravellerResponseDto.builder()
                                .uuid(transaction)
                                .travellerName(TRAVELLER_NAME)
                                .status(PaymentStatus.SUCCESS)
                                .build()))));

        mockMvc.perform(post("/api/v1/{travellerName}/journeyPlans/{tourId}", TRAVELLER_NAME, "tourId4321").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PLACE_JOURNEY_PLAN_REQUEST_DTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.journeyPlanId").exists())
                .andExpect(jsonPath("$.paymentId").value(transaction.toString()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123e4567-e89b-12d3-a456-426614174000", "223e4567-e89b-12d3-a456-426614174001"})
    @SneakyThrows
    void shouldPlaceOrder(UUID transaction) {

        stubFor(WireMock.post("/payment-service/v1/payments")
                .willReturn(aResponse().withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsBytes(PaymentTravellerResponseDto.builder()
                                .uuid(transaction)
                                .travellerName(TRAVELLER_NAME)
                                .status(PaymentStatus.SUCCESS)
                                .build()))));

        mockMvc.perform(post("/api/v1/{travellerName}/journeyPlans/{tourId}", TRAVELLER_NAME, "tourId4321").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PLACE_JOURNEY_PLAN_REQUEST_DTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tourId").exists())
                .andExpect(jsonPath("$.paymentId").value(transaction.toString()));
    }


    @Test
    @SneakyThrows
    void shouldThrowPaymentTransactionFailedException() {
        UUID transaction = UUID.randomUUID();
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(BAD_REQUEST, String.format(PAYMENT_TRANSACTION_WITH_ID_S_FOR_TOUR_ID_S_FAILED, transaction, TOUR_ID_4321));

        problemDetail.setType(URI.create("payment-refused"));
        problemDetail.setTitle("Payment Transaction Failed to process");
        stubFor(WireMock.post("/payment-service/v1/payments")
                .willReturn(aResponse().withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsBytes(PaymentTravellerResponseDto.builder()
                                .uuid(transaction)
                                .travellerName(TRAVELLER_NAME)
                                .status(PaymentStatus.FAILURE)
                                .build()))));

        mockMvc.perform(post("/api/v1/{travellerName}/journeyPlans/{tourId}", TRAVELLER_NAME, TOUR_ID_4321).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(PLACE_JOURNEY_PLAN_REQUEST_DTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(problemDetail)));

    }

    private static PlaceJourneyPlanRequestDto buildPlaceJourneyPlanRequestDto() {
        return PlaceJourneyPlanRequestDto.builder().totalPrice(10.0).journeyPlanEntries(List.of(buildJourneyPlanEntryDto())).build();
    }

    private static JourneyPlanEntryDto buildJourneyPlanEntryDto() {
        return JourneyPlanEntryDto.builder().spaceRoute(SpaceRoute.INTERGALACTIC.getDisplayName()).numOfJourneyPlans(1).build();
    }

}
