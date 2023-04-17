-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE GAME
(
    id         INT         NOT NULL AUTO_INCREMENT,
    trial      INT         NOT NULL,
    created_at DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER
(
    id        INT         NOT NULL AUTO_INCREMENT,
    name      VARCHAR(5) NOT NULL,
    position  INT         NOT NULL,
    is_winner BOOL        NOT NULL,
    game_id   INT         NOT NULL,
    FOREIGN KEY (game_id) REFERENCES GAME (id),
    PRIMARY KEY (id)
);
