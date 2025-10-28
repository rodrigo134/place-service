package com.rodrigo134.place_service.domain;

import com.github.slugify.Slugify;
import com.rodrigo134.place_service.api.PlaceRequest;
import com.rodrigo134.place_service.utils.QueryBuilder;
import com.rodrigo134.place_service.web.PlaceMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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
    public Mono<Place> edit(Long id, PlaceRequest placeRequest) {
        return placeRepository.findById(id)
                .map(place -> PlaceMapper.updatePlaceFromDTO(placeRequest, place))
                .map(place -> place.withSlug(slg.slugify(place.name())))
                .flatMap(placeRepository::save);
    }

    public Mono<Place> get(Long id) {

        return placeRepository.findById(id);
    }
    public Flux<Place> listAll() {
        return placeRepository.findAll();
    }


}
