package com.rodrigo134.place_service.config;

import com.github.slugify.Slugify;
import com.rodrigo134.place_service.domain.PlaceRepository;
import com.rodrigo134.place_service.domain.PlaceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

@Configuration
@EnableR2dbcAuditing
public class PlaceConfig {


    @Bean
    public Slugify slugify() {
        return Slugify.builder()
                .underscoreSeparator(true)
                .lowerCase(true)
                .build();
    }

    @Bean
    PlaceService placeService(PlaceRepository placeRepository, Slugify slugify) {
        return new PlaceService(placeRepository, slugify);
    }
}


