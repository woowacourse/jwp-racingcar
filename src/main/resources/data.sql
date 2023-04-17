CREATE TABLE IF NOT EXISTS game
(
    id          int PRIMARY KEY AUTO_INCREMENT      NOT NULL,
    trial_count int                                 NOT NULL,
    game_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS record
(
    player_name         varchar(30) NOT NULL,
    game_id   int         NOT NULL,
    position  int         NOT NULL,
    is_winner boolean     NOT NULL,
    PRIMARY KEY (player_name, game_id),
    FOREIGN KEY (game_id) REFERENCES game (id)
);
--
-- CREATE TABLE game
-- (
--     id          int PRIMARY KEY AUTO_INCREMENT      NOT NULL,
--     trial_count int                                 NOT NULL,
--     game_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
-- );
--
-- CREATE TABLE record
-- (
--     player_name         varchar(30) NOT NULL,
--     game_id   int         NOT NULL,
--     position  int         NOT NULL,
--     is_winner boolean     NOT NULL,
--     PRIMARY KEY (player_name, game_id),
--     FOREIGN KEY (game_id) REFERENCES game (id)
-- );
