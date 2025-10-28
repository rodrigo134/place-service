package com.rodrigo134.place_service.domain;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public record Place( @Id Long id,
    String name,
    String slug,
    String state,
    @CreatedDate LocalDateTime createdAt,
    @LastModifiedDate LocalDateTime updatedAt){



    public Place withSlug(String slug) {
        return new Place(id, name, slug, state, createdAt, updatedAt);
    }
}
