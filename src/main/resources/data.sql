DROP TABLE IF EXISTS GAME;
DROP TABLE IF EXISTS CAR;
DROP TABLE IF EXISTS WINNER;

CREATE TABLE GAME
(
    id          INT      NOT NULL AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    created_at  DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE CAR
(
    id       INT         NOT NULL AUTO_INCREMENT,
    game_id  INT         NOT NULL,
    name     VARCHAR(50) NOT NULL,
    position INT         NOT NULL,
    PRIMARY KEY (id),
    foreign key (game_id) references GAME (id)
);

CREATE TABLE WINNER
(
    id     INT NOT NULL AUTO_INCREMENT,
    car_id INT NOT NULL,
    PRIMARY KEY (id),
    foreign key (car_id) references CAR (id)
);
