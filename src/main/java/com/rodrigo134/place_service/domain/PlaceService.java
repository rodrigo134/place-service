package com.rodrigo134.place_service.domain;

import com.github.slugify.Slugify;
import com.rodrigo134.place_service.api.PlaceRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


public class PlaceService {

    private final PlaceRepository placeRepository;
    private final Slugify slg;

    public PlaceService(PlaceRepository placeRepository,Slugify slg) {
        this.placeRepository = placeRepository;
        this.slg = slg.builder().build();;
    }

    public Mono<Place> create(PlaceRequest placeRequest) {
        var place = new Place(
                null,
                placeRequest.name(),
                slg.slugify(placeRequest.name()),
                placeRequest.state(),
                placeRequest.createdAt(),
                placeRequest.updatedAt()
        );
        return placeRepository.save(place);

    }

}
