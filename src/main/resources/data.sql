CREATE TABLE users
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE play_result
(
    id          BIGINT      NOT NULL AUTO_INCREMENT,
    trial_count INT         NOT NULL,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE users_play_result
(
    id               BIGINT NOT NULL AUTO_INCREMENT,
    users_id         BIGINT NOT NULL,
    player_result_id BIGINT NOT NULL,
    position         INT    NOT NULL,
    PRIMARY KEY (id)
);