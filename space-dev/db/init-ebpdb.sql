-- MySQL 초기 세팅 스크립트
CREATE DATABASE IF NOT EXISTS ebpdb
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE ebpdb;

CREATE TABLE IF NOT EXISTS venue (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(200) NOT NULL,
    capacity INT NOT NULL,
    hourly_price INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    venue_id BIGINT NOT NULL,
    renter_name VARCHAR(100) NOT NULL,
    start_at DATETIME NOT NULL,
    end_at DATETIME NOT NULL,
    request_memo VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reservation_venue FOREIGN KEY (venue_id) REFERENCES venue(id)
);

INSERT INTO venue(name, location, capacity, hourly_price)
VALUES ('강남 스튜디오 A', '서울 강남구', 20, 50000),
       ('홍대 연습실 B', '서울 마포구', 12, 30000)
ON DUPLICATE KEY UPDATE name = VALUES(name);
