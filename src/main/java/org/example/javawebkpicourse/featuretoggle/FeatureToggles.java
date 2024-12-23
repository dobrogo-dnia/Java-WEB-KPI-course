package org.example.javawebkpicourse.featuretoggle;

import lombok.Getter;

@Getter
public enum FeatureToggles {

    TRAVELLER_GREETING("traveller-greeting"),
    GALACTIC_CRUISES("galactic-cruises"),
    PLANETARY_EXPLORATION("planetary-exploration"),
    ALIEN_ENCOUNTERS("alien-encounters"),
    COSMIC_VACATIONS("cosmic-vacations");

    private final String featureName;

    FeatureToggles(String featureName) {
        this.featureName = featureName;
    }

}
