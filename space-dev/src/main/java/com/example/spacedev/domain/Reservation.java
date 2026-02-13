package com.example.spacedev.domain;

import java.time.LocalDateTime;

public record Reservation(Long id, Long venueId, String renterName, LocalDateTime startAt, LocalDateTime endAt,
                          String requestMemo) {
}
