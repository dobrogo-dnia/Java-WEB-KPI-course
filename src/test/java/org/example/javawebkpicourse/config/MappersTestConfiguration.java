package org.example.javawebkpicourse.config;

import org.example.javawebkpicourse.service.mapper.PaymentMapper;
import org.example.javawebkpicourse.service.mapper.PaymentServiceMapper;
import org.example.javawebkpicourse.web.mapper.JourneyPlanDtoMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MappersTestConfiguration {

    @Bean
    public PaymentMapper paymentMapper() {
        return Mappers.getMapper(PaymentMapper.class);
    }

    @Bean
    public PaymentServiceMapper paymentServiceMapper(){
        return Mappers.getMapper(PaymentServiceMapper.class);
    }

    @Bean
    public JourneyPlanDtoMapper journeyPlanDtoMapper() {
        return Mappers.getMapper(JourneyPlanDtoMapper.class);
    }

}
