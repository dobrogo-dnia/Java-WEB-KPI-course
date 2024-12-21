package org.example.javawebkpicourse.dto.payment;

import org.example.javawebkpicourse.common.PaymentStatus;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class PaymentTravellerResponseDto {

    UUID uuid;
    PaymentStatus status;
    String travellerName;

}
