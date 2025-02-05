package org.example.javawebkpicourse.domain;

import org.example.javawebkpicourse.common.AvailableRoute;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CatTravellerDetails {

    Long id;
    String name;
    String email;
    String breed;
    String homePlanet;
    List<AvailableRoute> favouriteRoute;

}
