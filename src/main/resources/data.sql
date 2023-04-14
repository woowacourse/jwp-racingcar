DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS player;

CREATE TABLE game
(
    id         INT AUTO_INCREMENT,
    trialCount INT      NOT NULL,
    dateTime   DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE player
(
    id       INT         NOT NULL AUTO_INCREMENT,
    game_id  INT         NOT NULL,
    name     VARCHAR(45) NOT NULL,
    position INT         NOT NULL,
    isWinner TINYINT     NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_player_game_id
        FOREIGN KEY (game_id)
            REFERENCES game (id)
);
