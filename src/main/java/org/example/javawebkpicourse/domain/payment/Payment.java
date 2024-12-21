package org.example.javawebkpicourse.domain.payment;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Payment {

    String travellerName;
    String tourId;
    UUID paymentAssetId;
    double travellerAmount;

}
