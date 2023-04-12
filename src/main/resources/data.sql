CREATE TABLE PLAY_RESULT (
    id          INT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    trialCount  INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER (
    id          INT         NOT NULL AUTO_INCREMENT,
    position    INT         NOT NULL,
    game_id     INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id)
    REFERENCES PLAY_RESULT(id) ON DELETE CASCADE
);
