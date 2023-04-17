CREATE TABLE GAME (
    id                  INT         NOT NULL AUTO_INCREMENT,
    count               INT         NOT NULL,
    winners             VARCHAR(50) NOT NULL,
    created_at          DATETIME    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE CAR (
    id                  INT         NOT NULL AUTO_INCREMENT,
    name                VARCHAR(50) NOT NULL,
    position            INT         NOT NULL,
    game_id      INT         NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT `car_ibfk_1` FOREIGN KEY (game_id) REFERENCES GAME (id) ON DELETE CASCADE
);