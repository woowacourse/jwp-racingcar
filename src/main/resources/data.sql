CREATE TABLE game
(
    id          int PRIMARY KEY AUTO_INCREMENT,
    trial_count int                                 NOT NULL,
    game_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE record
(
    player_name varchar(30),
    game_id     int,
    position    int     NOT NULL,
    is_winner   boolean NOT NULL,
    PRIMARY KEY (player_name, game_id),
    FOREIGN KEY (game_id) REFERENCES game (id)
);
