CREATE TABLE GAME
(
    id          BIGINT   NOT NULL AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    time        DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER
(
    id       BIGINT      NOT NULL AUTO_INCREMENT,
    name     VARCHAR(10) NOT NULL,
    position INT         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE WINNER
(
    id        BIGINT NOT NULL AUTO_INCREMENT,
    game_id   BIGINT NOT NULL,
    player_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) references GAME (id),
    FOREIGN KEY (player_id) references PLAYER (id)
);
