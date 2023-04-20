CREATE TABLE IF NOT EXISTS PLAY_RESULT
(
    id          BIGINT   NOT NULL AUTO_INCREMENT PRIMARY KEY,
    winners     VARCHAR(255),
    trial_count INT      NOT NULL,
    created_at  DATETIME NOT NULL default current_timestamp
);

CREATE TABLE IF NOT EXISTS CAR
(
    id             BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    play_result_id BIGINT      NOT NULL,
    name           VARCHAR(10) NOT NULL,
    position       INT         NOT NULL,
    FOREIGN KEY (play_result_id) REFERENCES PLAY_RESULT (id)
);
