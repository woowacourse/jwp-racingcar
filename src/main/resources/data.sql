CREATE TABLE users
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE game
(
    id          BIGINT   NOT NULL AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    created_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE game_users_position
(
    id       BIGINT NOT NULL AUTO_INCREMENT,
    game_id  BIGINT NOT NULL,
    users_id BIGINT NOT NULL,
    position INT    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE game_win_users
(
    id       BIGINT NOT NULL AUTO_INCREMENT,
    game_id  BIGINT NOT NULL,
    users_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);