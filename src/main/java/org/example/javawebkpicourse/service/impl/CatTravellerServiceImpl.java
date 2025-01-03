package org.example.javawebkpicourse.service.impl;

import org.example.javawebkpicourse.common.AvailableRoute;
import org.example.javawebkpicourse.domain.CatTravellerDetails;
import org.example.javawebkpicourse.service.CatTravellerService;
import org.example.javawebkpicourse.service.exception.CatTravellerNotFoundException;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CatTravellerServiceImpl implements CatTravellerService {

    private final List<CatTravellerDetails> catTravellerDetails = buildAllCatTravellerDetailsMock();

    @Override
    public List<CatTravellerDetails> getAllCatTravellerDetails() {
        return catTravellerDetails;
    }

    @Override
    public CatTravellerDetails getCatTravellerDetailsById(Long travellerId) {
        return Optional.of(catTravellerDetails.stream()
                        .filter(details -> details.getId().equals(travellerId)).findFirst())
                .get()
                .orElseThrow(() -> {
                    log.info("Cat Traveller with id {} not found in mock", travellerId);
                    return new CatTravellerNotFoundException(travellerId);
                });
    }

    private List<CatTravellerDetails> buildAllCatTravellerDetailsMock() {
        return List.of(
                CatTravellerDetails.builder()
                        .id(1L)
                        .name("Zara Xanthea")
                        .email("zara.xanthea@starfleet.com")
                        .breed("Xenonian")
                        .homePlanet("Xenthara Prime")
                        .favouriteRoute(List.of(AvailableRoute.SECTOR_3_PLANET_GARMONIKA64_QUADRANT_3))
                        .build(),
                CatTravellerDetails.builder()
                        .id(2L)
                        .name("Grunkor Stalwart")
                        .email("grunko4@spacemail.com")
                        .breed("Hulkorian")
                        .homePlanet("Hulkora III")
                        .favouriteRoute(List.of(AvailableRoute.SECTOR_12_PLANET_DUNA_QUADRANT_8, AvailableRoute.SECTOR_1_PLANET_QUANTUM_QUADRANT_121))
                        .build(),
                CatTravellerDetails.builder()
                        .id(3L)
                        .name("Lyra Solis")
                        .email("lyra.solis@starfleet.com")
                        .breed("Xenonian")
                        .homePlanet("Xenthara Prime")
                        .favouriteRoute(List.of(AvailableRoute.SECTOR_1_PLANET_QUANTUM_QUADRANT_121))
                        .build(),
                CatTravellerDetails.builder()
                        .id(4L)
                        .name("M-9 Alpha")
                        .email("m932654alpha@meta.xmo")
                        .breed("Devianpod")
                        .homePlanet("DevaXM-9")
                        .favouriteRoute(List.of(AvailableRoute.SECTOR_2_PLANET_DINA_QUADRANT_15, AvailableRoute.SECTOR_12_PLANET_DUNA_QUADRANT_8))
                        .build(),
                CatTravellerDetails.builder()
                        .id(5L)
                        .name("Liza")
                        .email("liza2024@gmail.com")
                        .breed("Maine Coon")
                        .homePlanet("Earth")
                        .favouriteRoute(List.of(AvailableRoute.SECTOR_3_PLANET_GARMONIKA64_QUADRANT_3, AvailableRoute.SECTOR_2_PLANET_TILDA6_QUADRANT_15, AvailableRoute.SECTOR_1_PLANET_ZETA_QUADRANT_121))
                        .build());

    }
}
