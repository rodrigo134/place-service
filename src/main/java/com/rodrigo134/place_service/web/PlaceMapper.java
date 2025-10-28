package com.rodrigo134.place_service.web;

import com.rodrigo134.place_service.api.PlaceRequest;
import com.rodrigo134.place_service.api.PlaceResponse;
import com.rodrigo134.place_service.domain.Place;
import org.springframework.util.StringUtils;

public class PlaceMapper {
    public static PlaceResponse toResponse(Place place) {
        return new PlaceResponse(place.name(), place.slug(),
                 place.state(), place.createdAt(), place.updatedAt());
    }
    public static Place updatePlaceFromDTO(PlaceRequest placeRequest, Place place) {
        final String name = StringUtils.hasText(placeRequest.name()) ? placeRequest.name() : place.name();
        final String city = StringUtils.hasText(placeRequest.state()) ? placeRequest.state() : place.state();
        final String state = StringUtils.hasText(placeRequest.state()) ? placeRequest.state() : place.state();
        return new Place(place.id(), name, place.slug(), state, place.createdAt(), place.updatedAt());
    }






}
