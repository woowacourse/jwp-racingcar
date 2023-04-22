-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE RACING_GAME
(
    id          INT      NOT NULL AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    date        DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE RACING_CAR
(
    id       INT         NOT NULL AUTO_INCREMENT,
    game_id  INT         NOT NULL,
    name     VARCHAR(50) NOT NULL,
    position INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES RACING_GAME (id)
);

CREATE TABLE RACING_WINNER
(
    id      INT NOT NULL AUTO_INCREMENT,
    game_id INT NOT NULL,
    car_id  INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES RACING_GAME (id),
    FOREIGN KEY (car_id) REFERENCES RACING_CAR (id)
)
