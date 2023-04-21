CREATE TABLE GAME (
    id          INT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    trial INT         NOT NULL,
    played_time  DATETIME   NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE CAR (
     id          INT         NOT NULL AUTO_INCREMENT,
     game_id INT      NOT NULL,
     name     VARCHAR(50)    NOT NULL,
     position INT            NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (game_id) REFERENCES GAME(id)
);
