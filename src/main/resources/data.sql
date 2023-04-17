CREATE TABLE PLAY_RESULT
(
    id         INT         NOT NULL AUTO_INCREMENT,
    trialCount INT         NOT NULL,
    winners    VARCHAR(50) NOT NULL,
    created_at DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE RACING_CAR
(
    id              INT          NOT NULL AUTO_INCREMENT,
    player_name     VARCHAR(255) NOT NULL,
    player_position INT          NOT NULL,
    play_result_id  INT          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (play_result_id) REFERENCES PLAY_RESULT (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
