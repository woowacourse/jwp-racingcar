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
    game_id         INT          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES PLAY_RESULT (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
