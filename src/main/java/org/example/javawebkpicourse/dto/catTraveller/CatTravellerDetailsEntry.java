package org.example.javawebkpicourse.dto.catTraveller;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class CatTravellerDetailsEntry {

    Long id;
    String name;
    String email;
    String breed;
    String homePlanet;
    List<String> favouriteRoutes;

}
