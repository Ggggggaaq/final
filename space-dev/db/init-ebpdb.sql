-- MySQL 초기 세팅 스크립트
CREATE DATABASE IF NOT EXISTS ebpdb
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE ebpdb;

CREATE TABLE IF NOT EXISTS venue (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(200) NOT NULL,
    region VARCHAR(50) NOT NULL DEFAULT '기타',
    image_url VARCHAR(500) NOT NULL DEFAULT 'https://images.unsplash.com/photo-1497366216548-37526070297c?auto=format&fit=crop&w=1200&q=80',
    capacity INT NOT NULL,
    hourly_price INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE venue
    ADD COLUMN IF NOT EXISTS region VARCHAR(50) NOT NULL DEFAULT '기타';

ALTER TABLE venue
    ADD COLUMN IF NOT EXISTS image_url VARCHAR(500) NOT NULL DEFAULT 'https://images.unsplash.com/photo-1497366216548-37526070297c?auto=format&fit=crop&w=1200&q=80';

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

INSERT INTO venue(name, location, region, image_url, capacity, hourly_price)
VALUES ('강남 스튜디오 A', '서울 강남구', '서울', 'https://images.unsplash.com/photo-1497366754035-f200968a6e72?auto=format&fit=crop&w=1200&q=80', 20, 50000),
       ('홍대 연습실 B', '서울 마포구', '서울', 'https://images.unsplash.com/photo-1604014237800-1c9102c219da?auto=format&fit=crop&w=1200&q=80', 12, 30000),
       ('해운대 세미나룸', '부산 해운대구', '부산', 'https://images.unsplash.com/photo-1497215842964-222b430dc094?auto=format&fit=crop&w=1200&q=80', 30, 65000),
       ('수성 컨퍼런스홀', '대구 수성구', '대구', 'https://images.unsplash.com/photo-1505373877841-8d25f7d46678?auto=format&fit=crop&w=1200&q=80', 50, 90000);
