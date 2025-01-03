package org.example.javawebkpicourse.service;

import org.example.javawebkpicourse.common.PaymentStatus;
import org.example.javawebkpicourse.common.SpaceRoute;
import org.example.javawebkpicourse.config.MappersTestConfiguration;
import org.example.javawebkpicourse.domain.journeyPlan.JourneyPlan;
import org.example.javawebkpicourse.domain.journeyPlan.JourneyPlanContext;
import org.example.javawebkpicourse.domain.journeyPlan.JourneyPlanEntry;
import org.example.javawebkpicourse.domain.payment.Payment;
import org.example.javawebkpicourse.domain.payment.PaymentTransaction;
import org.example.javawebkpicourse.service.exception.PaymentTransactionFailed;
import org.example.javawebkpicourse.service.impl.JourneyPlanServiceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {JourneyPlanServiceImpl.class})
@Import(MappersTestConfiguration.class)
@DisplayName("Journey Plan Service Tests")
@TestMethodOrder(OrderAnnotation.class)
class JourneyPlanServiceTest {

    private static final JourneyPlanEntry DEFAULT_JOURNEY_PLAN_ENTRY = buildJourneyPlanEntry();
    private static final JourneyPlanContext JOURNEY_PLAN_CONTEXT = buildJourneyPlanContext("tourId");
    private static final String TOUR_ID = "tourId";
    private static final String TRAVELLER_NAME = "travellerName";
    private static final double TOTAL_PRICE = 105.0;

    @MockBean
    private PaymentService paymentService;

    @Captor
    private ArgumentCaptor<Payment> paymentArgumentCaptor;

    @Autowired
    private JourneyPlanService journeyPlanService;

    private static Stream<JourneyPlanContext> provideJourneyPlanContexts() {
        return Stream.of(
                buildJourneyPlanContext("tourTest"),
                buildJourneyPlanContext("tourSuperTest")
        );
    }

    @ParameterizedTest
    @MethodSource("provideJourneyPlanContexts")
    @Order(1)
    void shouldPlaceJourneyPlan(JourneyPlanContext journeyPlanContext) {

        when(paymentService.processPayment(paymentArgumentCaptor.capture())).thenAnswer(inv -> buildPaymentTransaction(((Payment) inv.getArgument(0)).getTourId(),
                PaymentStatus.SUCCESS));


        JourneyPlan result = journeyPlanService.placeJourneyPlan(journeyPlanContext);

        verify(paymentService, times(1)).processPayment(any());
        assertThatNoException().isThrownBy(() -> journeyPlanService.placeJourneyPlan(journeyPlanContext));

        assertEquals(journeyPlanContext.getTourId(), result.getTourId());
        assertEquals(TRAVELLER_NAME, result.getTravellerName());
        assertEquals(TOTAL_PRICE, result.getTotalPrice());
        assertEquals(TOTAL_PRICE, result.getTotalPrice());
        assertEquals(DEFAULT_JOURNEY_PLAN_ENTRY, result.getJourneyPlanEntries());

    }

    @Test
    void shouldThrowExceptionPaymentTransactionFailed(){

        when(paymentService.processPayment(paymentArgumentCaptor.capture())).thenAnswer(inv -> buildPaymentTransaction(((Payment) inv.getArgument(0)).getTourId(),
                PaymentStatus.FAILURE));

        assertThrows(PaymentTransactionFailed.class, () -> journeyPlanService.placeJourneyPlan(JOURNEY_PLAN_CONTEXT));
    }

    private static JourneyPlanContext buildJourneyPlanContext(String tourId) {
        return JourneyPlanContext.builder()
                .tourId(tourId)
                .travellerName(TRAVELLER_NAME)
                .totalPrice(TOTAL_PRICE)
                .journeyPlanEntries(List.of(DEFAULT_JOURNEY_PLAN_ENTRY))
                .build();
    }

    private static JourneyPlanEntry buildJourneyPlanEntry() {
        return JourneyPlanEntry.builder().travellerAmount(1).spaceRoute(SpaceRoute.INTERGALACTIC).build();
    }

    private PaymentTransaction buildPaymentTransaction(String tourId, PaymentStatus paymentStatus) {
        return PaymentTransaction.builder().id(UUID.randomUUID()).tourId(tourId).status(paymentStatus).build();
    }

}
