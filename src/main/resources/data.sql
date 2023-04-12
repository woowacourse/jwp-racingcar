CREATE TABLE IF NOT EXISTS GAME
(
    game_id     INT       NOT NULL AUTO_INCREMENT,
    created_at  TIMESTAMP NOT NULL,
    trial_count INT       NOT NULL,
    PRIMARY KEY (game_id)
);

CREATE TABLE IF NOT EXISTS CAR
(
    car_id   INT         NOT NULL AUTO_INCREMENT,
    name     VARCHAR(10) NOT NULL,
    position INT         NOT NULL,
    game_id  INT         NOT NULL,
    PRIMARY KEY (car_id),
    FOREIGN KEY (game_id) REFERENCES GAME (game_id)
);

CREATE TABLE IF NOT EXISTS WINNER
(
    winner_id INT NOT NULL AUTO_INCREMENT,
    game_id   INT NOT NULL,
    car_id    INT NOT NULL,
    PRIMARY KEY (winner_id),
    FOREIGN KEY (car_id) REFERENCES CAR (car_id),
    FOREIGN KEY (game_id) REFERENCES GAME (game_id)
);
