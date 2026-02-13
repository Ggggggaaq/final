package com.example.spacedev.domain;

public record SpaceVenue(Long id,
                         String name,
                         String location,
                         String region,
                         String imageUrl,
                         int capacity,
                         int hourlyPrice) {
}
