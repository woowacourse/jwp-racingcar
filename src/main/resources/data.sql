CREATE TABLE IF NOT EXISTS PLAY_RESULT
(
    id          BIGINT   NOT NULL AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    created_at  DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS CAR
(
    play_result_id BIGINT      NOT NULL,
    name           VARCHAR(10) NOT NULL,
    position       INT         NOT NULL,
    is_winner      BOOLEAN     NOT NULL,
    FOREIGN KEY (play_result_id) REFERENCES PLAY_RESULT (id)
        ON DELETE CASCADE
);
