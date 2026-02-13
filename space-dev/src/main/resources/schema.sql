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
