package com.rodrigo134.place_service.web;


import com.rodrigo134.place_service.api.PlaceRequest;
import com.rodrigo134.place_service.api.PlaceResponse;
import com.rodrigo134.place_service.domain.Place;
import com.rodrigo134.place_service.domain.PlaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    public ResponseEntity<Mono<PlaceResponse>> create(@RequestBody PlaceRequest placeRequest) {
        var placeResponse = placeService.create(placeRequest).map(PlaceMapper::toResponse);

        return ResponseEntity.status(HttpStatus.CREATED).body(placeResponse);
    }
    @PatchMapping("{id}")
    public Mono<PlaceResponse> edit(@PathVariable("id") Long id, @RequestBody PlaceRequest request) {
        return placeService.edit(id, request).map(PlaceMapper::toResponse);
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<PlaceResponse>> get(@PathVariable("id") Long id) {
        return placeService.get(id)
                .map(place -> ResponseEntity.ok(PlaceMapper.toResponse(place)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<PlaceResponse> listAll() {
        return placeService.listAll().map(PlaceMapper::toResponse);
    }


}
