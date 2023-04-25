CREATE TABLE IF NOT EXISTS GAME(
    id          LONG         NOT NULL AUTO_INCREMENT,
    count  INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS CAR(
    id          LONG           NOT NULL AUTO_INCREMENT,
    game_id     INT           NOT NULL,
    name        VARCHAR(50)   NOT NULL,
    position    INT           NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES GAME(id)
);

CREATE TABLE IF NOT EXISTS WINNER(
    id          LONG         NOT NULL AUTO_INCREMENT,
    game_id     INT         NOT NULL,
    car_id      INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES GAME(id),
    FOREIGN KEY (car_id) REFERENCES CAR(id)
);



