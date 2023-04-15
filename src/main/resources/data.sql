DROP TABLE if exists GAME;
DROP TABLE if exists PLAYER;

CREATE TABLE GAME
(
    id          BIGINT   NOT NULL AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    time        DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER
(
    id        BIGINT      NOT NULL AUTO_INCREMENT,
    name      VARCHAR(10) NOT NULL,
    position  INT         NOT NULL,
    game_id   BIGINT      NOT NULL,
    is_winner BOOLEAN     NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) references GAME (id)
);
