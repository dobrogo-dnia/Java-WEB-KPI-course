package org.example.javawebkpicourse.service.impl;

import org.example.javawebkpicourse.domain.journeyPlan.JourneyPlan;
import org.example.javawebkpicourse.domain.journeyPlan.JourneyPlanContext;
import org.example.javawebkpicourse.domain.journeyPlan.JourneyPlanEntry;
import org.example.javawebkpicourse.domain.payment.PaymentTransaction;
import org.example.javawebkpicourse.service.JourneyPlanService;
import org.example.javawebkpicourse.service.PaymentService;
import org.example.javawebkpicourse.service.exception.PaymentTransactionFailed;
import org.example.javawebkpicourse.service.mapper.PaymentMapper;

import static org.example.javawebkpicourse.common.PaymentStatus.FAILURE;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class JourneyPlanServiceImpl implements JourneyPlanService {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    @Override
    public JourneyPlan placeJourneyPlan(JourneyPlanContext journeyPlanContext) {
        log.info("Placing journey plan for tour with id: {}", journeyPlanContext.getTourId());
        PaymentTransaction paymentTransaction = paymentService.processPayment(paymentMapper.toPayment(journeyPlanContext));
        if (FAILURE.equals(paymentTransaction.getStatus())) {
            throw new PaymentTransactionFailed(paymentTransaction.getId(), journeyPlanContext.getTourId());
        }

        return createJourneyPlanMock(journeyPlanContext.getTourId(),
                journeyPlanContext.getJourneyPlanEntries(),
                journeyPlanContext.getTotalPrice(),
                journeyPlanContext.getTravellerName(),
                paymentTransaction.getId());
    }

    private JourneyPlan createJourneyPlanMock(String tourId, List<JourneyPlanEntry> journeyPlanEntries, Double totalPrice, String travellerName, UUID paymentId) {
        return JourneyPlan.builder()
                .id(UUID.randomUUID().toString())
                .paymentId(paymentId)
                .tourId(tourId)
                .journeyPlanEntries(journeyPlanEntries)
                .totalPrice(totalPrice)
                .travellerName(travellerName)
                .build();

    }
}
