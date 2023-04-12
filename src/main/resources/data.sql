CREATE TABLE GAME
(
    id          BIGINT   NOT NULL AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    created_at  DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name varchar(10) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE PARTICIPATES
(
    game_id   BIGINT  NOT NULL,
    player_id BIGINT  NOT NULL,
    position  INT     NOT NULL,
    is_winner BOOLEAN NOT NULL default false,
    PRIMARY KEY (game_id, player_id),
    FOREIGN KEY (game_id) references GAME (id),
    FOREIGN KEY (player_id) references PLAYER (id)
);
