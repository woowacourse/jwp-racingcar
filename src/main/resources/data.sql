CREATE TABLE RACE (
    id          INT         NOT NULL AUTO_INCREMENT,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER (
    id          INT           NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50)   NOT NULL,
    position    INT           NOT NULL,
    race_id     INT           NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (race_id) REFERENCES RACE(id)
);

CREATE TABLE WINNER (
    id          INT         NOT NULL AUTO_INCREMENT,
    race_id     INT         NOT NULL,
    player_id   INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (race_id) REFERENCES RACE(id),
    FOREIGN KEY (player_id) REFERENCES PLAYER(id)
);



