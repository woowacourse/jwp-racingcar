CREATE TABLE GAME
(
    id          INT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    trial_count INT         NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE PLAYER
(
    id       INT         NOT NULL AUTO_INCREMENT,
    game_id  INT         NOT NULL,
    name     VARCHAR(10) NOT NULL,
    position INT         NOT NULL,

    FOREIGN KEY (game_id) REFERENCES GAME (id),
    PRIMARY KEY (id)
)
