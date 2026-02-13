package com.example.spacedev.service;

import com.example.spacedev.domain.Reservation;
import com.example.spacedev.domain.SpaceVenue;
import com.example.spacedev.repository.ReservationRepository;
import com.example.spacedev.repository.SpaceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public void createVenue(String name, String location, int capacity, int hourlyPrice) {
        spaceRepository.save(name, location, capacity, hourlyPrice);
    }

    public void reserve(Long venueId, String renterName, LocalDateTime startAt, LocalDateTime endAt, String memo) {
        reservationRepository.save(venueId, renterName, startAt, endAt, memo);
    }

    public List<Reservation> recentReservations() {
        return reservationRepository.findRecent();
    }
}
