-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
DROP TABLE IF EXISTS PLAYER;

DROP TABLE IF EXISTS WINNER;

DROP TABLE IF EXISTS GAME;

CREATE TABLE GAME
(
    id         INT       NOT NULL AUTO_INCREMENT,
    play_count INT       NOT NULL,
    created_at TIMESTAMP NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER
(
    id       INT         NOT NULL AUTO_INCREMENT,
    game_id  INT         NOT NULL,
    name     VARCHAR(10) NOT NULL,
    position INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES GAME (id)
);

CREATE TABLE WINNER
(
    id      INT         NOT NULL AUTO_INCREMENT,
    game_id INT         NOT NULL,
    name    VARCHAR(10) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES GAME (id)
)
