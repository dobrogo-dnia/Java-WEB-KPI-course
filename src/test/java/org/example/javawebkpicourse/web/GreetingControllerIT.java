package org.example.javawebkpicourse.web;

import org.example.javawebkpicourse.AbstractIt;
import org.example.javawebkpicourse.featuretoggle.FeatureToggleExtension;
import org.example.javawebkpicourse.featuretoggle.FeatureToggles;
import org.example.javawebkpicourse.featuretoggle.annotation.DisabledFeatureToggle;
import org.example.javawebkpicourse.featuretoggle.annotation.EnabledFeatureToggle;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@DisplayName("Greeting Controller IT")
@ExtendWith(FeatureToggleExtension.class)
class GreetingControllerIT extends AbstractIt {

    private static final String DOBBY = "dobby";
    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    @DisabledFeatureToggle(FeatureToggles.TRAVELLER_GREETING)
    void shouldGet404FeatureDisabled() {
        mockMvc.perform(get("/api/v1/greetings/{name}", DOBBY)).andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    @EnabledFeatureToggle(FeatureToggles.TRAVELLER_GREETING)
    void shouldGet200() {
        mockMvc.perform(get("/api/v1/greetings/{name}", DOBBY)).andExpect(status().isOk());
    }

}
