CREATE TABLE player
(
    user_id BIGINT      NOT NULL AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE game
(
    game_id     BIGINT   NOT NULL AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (game_id)
);

CREATE TABLE player_position
(
    player_position_id BIGINT NOT NULL AUTO_INCREMENT,
    game_id            BIGINT NOT NULL,
    user_id            BIGINT NOT NULL,
    position           INT    NOT NULL,
    PRIMARY KEY (player_position_id)
);

CREATE TABLE game_winner
(
    game_winner_id BIGINT NOT NULL AUTO_INCREMENT,
    game_id        BIGINT NOT NULL,
    user_id        BIGINT NOT NULL,
    PRIMARY KEY (game_winner_id)
);