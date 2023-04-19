--create TABLE GAME (
--    game_id     INT         NOT NULL AUTO_INCREMENT,
--    winners     VARCHAR(50) NOT NULL,
--    try_count   INT         NOT NULL,
--    created_at  DATETIME    NOT NULL,
--    PRIMARY KEY (game_id)
--);
--
--create TABLE CAR (
--    car_id      INT          NOT NULL AUTO_INCREMENT,
--    name        VARCHAR(5)   NOT NULL,
--    position    INT          NOT NULL DEFAULT 0,
--    game_id     INT          NOT NULL,
--    PRIMARY KEY (car_id),
--    FOREIGN KEY(game_id) REFERENCES GAME (game_id)
--);

CREATE TABLE GAME (
    game_id     INT         NOT NULL AUTO_INCREMENT,
    try_count   INT         NOT NULL,
    created_at  DATETIME    NOT NULL,
    PRIMARY KEY (game_id)
);

CREATE TABLE CAR (
    car_id      INT          NOT NULL AUTO_INCREMENT,
    name        VARCHAR(5)   NOT NULL,
    position    INT          NOT NULL,
    game_id     INT          NOT NULL,
    PRIMARY KEY (car_id),
    FOREIGN KEY(game_id) REFERENCES GAME (game_id)
);

CREATE TABLE WINNER (
    winner_id   INT     NOT NULL AUTO_INCREMENT,
    car_id      INT     NOT NULL,
    game_id     INT     NOT NULL
)