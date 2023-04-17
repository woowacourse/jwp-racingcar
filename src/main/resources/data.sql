CREATE TABLE game
(
    id         INT AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    date_time   DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE player
(
    id       INT         NOT NULL AUTO_INCREMENT,
    game_id  INT         NOT NULL,
    name     VARCHAR(45) NOT NULL,
    position INT         NOT NULL,
    is_winner TINYINT     NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_player_game_id
        FOREIGN KEY (game_id) REFERENCES game (id)
);
