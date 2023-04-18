CREATE TABLE GAME (
    id          INT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    trial_count INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER (
    id          INT         NOT NULL AUTO_INCREMENT,
    name        varchar(10) NOT NULL,
    position    INT         NOT NULL,
    game_id  INT     NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES GAME (id)
);
