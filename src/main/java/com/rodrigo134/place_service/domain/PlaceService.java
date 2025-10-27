package com.rodrigo134.place_service.domain;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Mono<Place> create(Place place) {
        return placeRepository.save(place);

    }

}
