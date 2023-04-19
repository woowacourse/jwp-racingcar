drop table if exists CAR;
drop table if exists RACING_GAME;

CREATE TABLE if not exists RACING_GAME (
    id          INT         NOT NULL AUTO_INCREMENT,
    trial_count INT         NOT NULL,
    played_time  DATETIME   NOT NULL default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE if not exists CAR (
     id          INT         NOT NULL AUTO_INCREMENT,
     game_id  INT            NOT NULL,
     name     VARCHAR(50)    NOT NULL,
     position INT            NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (game_id) REFERENCES RACING_GAME(id)
);
