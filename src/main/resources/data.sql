CREATE TABLE GAME
(
    id          INT      NOT NULL AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    created_at  DATETIME NOT NULL default current_timestamp,

    PRIMARY KEY (id)
);

CREATE TABLE PLAYER
(
    id        INT         NOT NULL AUTO_INCREMENT,
    game_id   INT         NOT NULL,
    name      VARCHAR(10) NOT NULL,
    position  INT         NOT NULL,
    is_winner BOOLEAN     NOT NULL,

    FOREIGN KEY (game_id) REFERENCES GAME (id),
    PRIMARY KEY (id)
)
