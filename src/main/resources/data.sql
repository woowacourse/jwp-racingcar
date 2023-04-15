DROP TABLE player IF EXISTS cascade constraints;
DROP TABLE RACE IF EXISTS cascade constraints;
DROP TABLE WINNER IF EXISTS cascade constraints;


CREATE TABLE IF NOT EXISTS RACE(
    id          LONG         NOT NULL AUTO_INCREMENT,
    play_count  INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS PLAYER(
    id          LONG           NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50)   NOT NULL,
    identifier  INT           NOT NULL,
    position    INT           NOT NULL,
    race_id     INT           NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (race_id) REFERENCES RACE(id)
);

CREATE TABLE IF NOT EXISTS WINNER(
    id          LONG         NOT NULL AUTO_INCREMENT,
    race_id     INT         NOT NULL,
    player_id   INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (race_id) REFERENCES RACE(id),
    FOREIGN KEY (player_id) REFERENCES PLAYER(id)
);



