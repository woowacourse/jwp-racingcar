-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
-- CREATE TABLE PLAY_RESULT (
--     id          INT         NOT NULL AUTO_INCREMENT,
--     winners     VARCHAR(50) NOT NULL,
--     created_at  DATETIME    NOT NULL default current_timestamp,
--     PRIMARY KEY (id)
-- );

CREATE TABLE RACING_GAME
(
    id         INT      NOT NULL AUTO_INCREMENT,
    count      INT      NOT NULL,
--     winners    VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE RACING_CAR
(
    id             INT         NOT NULL AUTO_INCREMENT,
    name           VARCHAR(50) NOT NULL,
    position       INT         NOT NULL,
    racing_game_id INT         NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT `racing_cars_ibfk_1` FOREIGN KEY (racing_game_id) REFERENCES RACING_GAME (id) ON DELETE CASCADE
);

CREATE TABLE WINNERS
(
    racing_game_id INT NOT NULL,
    racing_car_id  INT NOT NULL,
    CONSTRAINT `winners_ibfk_1` FOREIGN KEY (racing_game_id) REFERENCES RACING_GAME (id) ON DELETE CASCADE,
    CONSTRAINT `winners_ibfk_2` FOREIGN KEY (racing_car_id) REFERENCES RACING_CAR (id) ON DELETE CASCADE
);