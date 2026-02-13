package com.example.spacedev.repository;

import com.example.spacedev.domain.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationRepository {
    private final JdbcTemplate jdbcTemplate;

    public ReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Long venueId, String renterName, LocalDateTime startAt, LocalDateTime endAt, String requestMemo) {
        jdbcTemplate.update("""
                INSERT INTO reservation(venue_id, renter_name, start_at, end_at, request_memo)
                VALUES (?, ?, ?, ?, ?)
                """, venueId, renterName, Timestamp.valueOf(startAt), Timestamp.valueOf(endAt), requestMemo);
    }

    public List<Reservation> findRecent() {
        return jdbcTemplate.query("""
                SELECT id, venue_id, renter_name, start_at, end_at, request_memo
                FROM reservation
                ORDER BY id DESC
                LIMIT 20
                """, (rs, rowNum) -> new Reservation(
                rs.getLong("id"),
                rs.getLong("venue_id"),
                rs.getString("renter_name"),
                rs.getTimestamp("start_at").toLocalDateTime(),
                rs.getTimestamp("end_at").toLocalDateTime(),
                rs.getString("request_memo")
        ));
    }
}
