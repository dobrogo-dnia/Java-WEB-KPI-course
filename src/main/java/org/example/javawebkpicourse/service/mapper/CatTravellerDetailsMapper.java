package org.example.javawebkpicourse.service.mapper;

import org.example.javawebkpicourse.common.AvailableRoute;
import org.example.javawebkpicourse.domain.CatTravellerDetails;
import org.example.javawebkpicourse.dto.catTraveller.CatTravellerDetailsDto;
import org.example.javawebkpicourse.dto.catTraveller.CatTravellerDetailsEntry;
import org.example.javawebkpicourse.dto.catTraveller.CatTravellerDetailsListDto;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CatTravellerDetailsMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "breed", source = "breed")
    @Mapping(target = "homePlanet", source = "homePlanet")
    @Mapping(target = "favouriteRoute", source = "favouriteRoute", qualifiedByName = "toFavouriteRouteString")
    CatTravellerDetailsDto toCatTravellerDetailsDto(CatTravellerDetails customerDetails);

    default CatTravellerDetailsListDto toCatTravellerDetailsListDto(List<CatTravellerDetails> catTravellerDetails) {
        return CatTravellerDetailsListDto.builder().catTravellerDetailsEntries(toCatTravellerDetailsEntry(catTravellerDetails)).build();
    }

    List<CatTravellerDetailsEntry> toCatTravellerDetailsEntry(List<CatTravellerDetails> catTravellerDetails);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "breed", source = "breed")
    @Mapping(target = "homePlanet", source = "homePlanet")
    @Mapping(target = "favouriteRoute", source = "favouriteRoute", qualifiedByName = "toFavouriteRouteString")
    CatTravellerDetailsEntry toCatTravellerDetailsEntry(CatTravellerDetails catTravellerDetails);

    @Named("toFavouriteRouteString")
    default List<String> toFavouriteRouteString(List<AvailableRoute> favouriteRoute) {
        return favouriteRoute.stream().map(route -> route.name().toLowerCase()).toList();
    }


}
