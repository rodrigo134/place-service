package com.rodrigo134.place_service.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;



public interface PlaceRepository extends ReactiveCrudRepository<Place, Long> {
}
