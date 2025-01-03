package org.example.javawebkpicourse.dto.catTraveller;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class CatTravellerDetailsListDto {

    List<CatTravellerDetailsEntry> catTravellerDetailsEntries;

}
