package org.example.javawebkpicourse.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SpaceRoute {

    INTERGALACTIC("Intergalactic Route"),
    PLANETARY("Planetary Route"),
    STELLAR("Stellar Route"),
    WORMHOLE("Wormhole Route");

    private final String displayName;

    public static SpaceRoute fromDisplayName(String name) {
        for (SpaceRoute route : values()) {
            if (route.displayName.equalsIgnoreCase(name)) {
                return route;
            }
        }
        throw new IllegalArgumentException(String.format("No enum constant with display name: %s", name));
    }
}
