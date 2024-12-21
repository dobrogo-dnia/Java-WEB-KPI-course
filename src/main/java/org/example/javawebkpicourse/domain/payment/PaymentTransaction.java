package org.example.javawebkpicourse.domain.payment;

import org.example.javawebkpicourse.common.PaymentStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentTransaction {

    UUID id;
    PaymentStatus status;
    String tourId;

}
