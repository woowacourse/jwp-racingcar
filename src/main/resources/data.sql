CREATE TABLE IF NOT EXISTS PLAY_RECORDS
(
    id          BIGINT   NOT NULL AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    created_at  DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS CARS
(
    play_record_id BIGINT      NOT NULL,
    name           VARCHAR(10) NOT NULL,
    position       INT         NOT NULL,
    FOREIGN KEY (play_record_id) REFERENCES play_records (id)
        ON DELETE CASCADE
);
