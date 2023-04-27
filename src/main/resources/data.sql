CREATE TABLE player
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE game
(
    id          BIGINT   NOT NULL AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    created_at  DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE car
(
    id        BIGINT NOT NULL AUTO_INCREMENT,
    game_id   BIGINT NOT NULL,
    player_id BIGINT NOT NULL,
    position  INT    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE winner
(
    id        BIGINT NOT NULL AUTO_INCREMENT,
    game_id   BIGINT NOT NULL,
    player_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);
