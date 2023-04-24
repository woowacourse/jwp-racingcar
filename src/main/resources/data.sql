DROP TABLE CAR IF EXISTS;
DROP TABLE WINNER IF EXISTS;
DROP TABLE GAME IF EXISTS;

CREATE TABLE GAME
(
    id          BIGINT   NOT NULL AUTO_INCREMENT,
    created_at  DATETIME NOT NULL default current_timestamp,
    trial_count INT      NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE CAR
(
    id       BIGINT      NOT NULL AUTO_INCREMENT,
    name     VARCHAR(50) NOT NULL,
    position INT         NOT NULL,
    game_id  BIGINT      NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE WINNER
(
    id      BIGINT      NOT NULL AUTO_INCREMENT,
    name    VARCHAR(50) NOT NULL,
    game_id BIGINT      NOT NULL,
    PRIMARY KEY (id)
)
