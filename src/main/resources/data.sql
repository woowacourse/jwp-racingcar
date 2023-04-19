CREATE TABLE IF NOT EXISTS PLAY_RESULT (
    id          INT         NOT NULL AUTO_INCREMENT,
    trial_count INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS CAR_RESULT (
    play_result_id  INT         NOT NULL,
    name        VARCHAR(5)  NOT NULL,
    position    INT         NOT NULL,
    PRIMARY KEY (play_result_id, name),
    FOREIGN KEY (play_result_id) REFERENCES PLAY_RESULT(id)
);
