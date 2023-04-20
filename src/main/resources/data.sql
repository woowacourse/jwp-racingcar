DROP TABLE IF EXISTS CAR;
DROP TABLE IF EXISTS WINNER;
DROP TABLE IF EXISTS RACING_GAME;

CREATE TABLE RACING_GAME
(
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    created_at  DATETIME    NOT NULL default current_timestamp,
    trial_count INT         NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE CAR
(
    id       BIGINT         NOT NULL AUTO_INCREMENT,
    name     VARCHAR(50) NOT NULL,
    position INT         NOT NULL,
    game_id  INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES `RACING_GAME` (id)
);

CREATE TABLE WINNER
(
    id   BIGINT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    game_id  INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES `RACING_GAME` (id)
)
