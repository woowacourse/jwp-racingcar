CREATE TABLE GAME (
    game_id     INT         NOT NULL AUTO_INCREMENT,
    created_at  DATETIME    NOT NULL default current_timestamp,
    trial_count INT         NOT NULL,
    PRIMARY KEY (game_id)
);

CREATE TABLE CAR (
    car_id      INT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(10) NOT NULL,
    game_id     INT         NOT NULL,
    PRIMARY KEY (car_id),
    FOREIGN KEY (game_id) REFERENCES GAME (game_id)
);

CREATE TABLE RESULT (
    result_id   INT         NOT NULL AUTO_INCREMENT,
    car_id      INT         NOT NULL,
    position    INT         NOT NULL,
    PRIMARY KEY (result_id),
    FOREIGN KEY (car_id) REFERENCES CAR (car_id)
);

CREATE TABLE WINNER (
    winner_id   INT         NOT NULL AUTO_INCREMENT,
    game_id     INT         NOT NULL,
    car_id      INT         NOT NULL,
    PRIMARY KEY (winner_id),
    FOREIGN KEY (car_id) REFERENCES CAR (car_id),
    FOREIGN KEY (game_id) REFERENCES GAME (game_id)
);
