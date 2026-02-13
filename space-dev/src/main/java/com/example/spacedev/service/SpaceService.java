package com.example.spacedev.service;

import com.example.spacedev.domain.Reservation;
import com.example.spacedev.domain.SpaceVenue;
import com.example.spacedev.repository.ReservationRepository;
import com.example.spacedev.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SpaceService {
    private final SpaceRepository spaceRepository;
    private final ReservationRepository reservationRepository;

    public SpaceService(SpaceRepository spaceRepository, ReservationRepository reservationRepository) {
        this.spaceRepository = spaceRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<SpaceVenue> venues() {
        return spaceRepository.findAll();
    }

    public void createVenue(String name, String location, String region, String imageUrl, int capacity, int hourlyPrice) {
        spaceRepository.save(name, location, region, imageUrl, capacity, hourlyPrice);
    }

    public void reserve(Long venueId, String renterName, LocalDateTime startAt, LocalDateTime endAt, String memo) {
        reservationRepository.save(venueId, renterName, startAt, endAt, memo);
    }

    public List<Reservation> recentReservations() {
        return reservationRepository.findRecent();
    }

    public int venueCount() {
        return venues().size();
    }

    public int reservationCount() {
        return recentReservations().size();
    }

    public int averageHourlyPrice() {
        return (int) venues().stream()
                .mapToInt(SpaceVenue::hourlyPrice)
                .average()
                .orElse(0);
    }

    public Optional<SpaceVenue> highestCapacityVenue() {
        return venues().stream().max(Comparator.comparingInt(SpaceVenue::capacity));
    }
}
