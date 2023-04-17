-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE GAME
(
    id         INT      NOT NULL AUTO_INCREMENT,
    trial      INT      NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE CAR
(
    id       INT          NOT NULL AUTO_INCREMENT,
    name     VARCHAR(255) NOT NULL,
    position INT          NOT NULL,
    winner   BOOL         NOT NULL,
    game_id  INT          NOT NULL,
    FOREIGN KEY (game_id) REFERENCES GAME (id),
    PRIMARY KEY (id)
)
