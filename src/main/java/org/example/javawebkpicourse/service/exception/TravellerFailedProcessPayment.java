package org.example.javawebkpicourse.service.exception;

public class TravellerFailedProcessPayment extends RuntimeException {

    private static final String FAILED_TO_PROCESS_PAYMENT_FOR_TOUR_ID_S_AND_TRAVELLER_REFERENCE_S =
            "Failed to process payment for tour id %s and traveller reference %s";

    public TravellerFailedProcessPayment(String tourId, String travellerName) {
        super(String.format(FAILED_TO_PROCESS_PAYMENT_FOR_TOUR_ID_S_AND_TRAVELLER_REFERENCE_S, tourId, travellerName));
    }
}
