-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS car;

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
    name     VARCHAR(50) NOT NULL,
    position INT    NOT NULL,
    is_win   TINYINT     NOT NULL,
    game_id  INT         NOT NULL,
    PRIMARY KEY (id)
);
