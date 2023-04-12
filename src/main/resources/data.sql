-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
-- CREATE TABLE PLAY_RESULT (
--     id          INT         NOT NULL AUTO_INCREMENT,
--     winners     VARCHAR(50) NOT NULL,
--     created_at  DATETIME    NOT NULL default current_timestamp,
--     PRIMARY KEY (id)
-- );

CREATE TABLE ROOM (
    id          INT         NOT NULL AUTO_INCREMENT,
    count       INT         NOT NULL,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE RACING_CARS (
    id          INT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    position    INT         NOT NULL,
    room_id     INT         NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT `racing_cars_ibfk_1` FOREIGN KEY (room_id) REFERENCES ROOM (id) ON DELETE CASCADE
);