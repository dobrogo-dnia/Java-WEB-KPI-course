package org.example.javawebkpicourse.service.exception;

import java.util.UUID;

public class PaymentTransactionFailed extends RuntimeException {

    public static final String PAYMENT_TRANSACTION_WITH_ID_S_FOR_TOUR_ID_S_FAILED = "Payment transaction with id %s, for tour id %s FAILED :(";

    public PaymentTransactionFailed(UUID id, String tourId) {
        super(String.format(PAYMENT_TRANSACTION_WITH_ID_S_FOR_TOUR_ID_S_FAILED, id, tourId));
    }

}
