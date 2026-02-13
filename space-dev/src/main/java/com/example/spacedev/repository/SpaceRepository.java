package com.example.spacedev.repository;

import com.example.spacedev.domain.SpaceVenue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpaceRepository {
    private final JdbcTemplate jdbcTemplate;

    public SpaceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SpaceVenue> findAll() {
        return jdbcTemplate.query("""
                SELECT id, name, location, region, image_url, capacity, hourly_price
                FROM venue
                ORDER BY id DESC
                """, (rs, rowNum) -> new SpaceVenue(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("location"),
                rs.getString("region"),
                rs.getString("image_url"),
                rs.getInt("capacity"),
                rs.getInt("hourly_price")
        ));
    }

    public void save(String name, String location, String region, String imageUrl, int capacity, int hourlyPrice) {
        jdbcTemplate.update("""
                INSERT INTO venue(name, location, region, image_url, capacity, hourly_price)
                VALUES (?, ?, ?, ?, ?, ?)
                """, name, location, region, imageUrl, capacity, hourlyPrice);
    }
}
