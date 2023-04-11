-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE GAME
(
    id          INT      NOT NULL AUTO_INCREMENT,
    trial_count INT      NOT NULL,
    created_at  DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER
(
    id          INT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(50)      NOT NULL,
    position  DATETIME NOT NULL default current_timestamp,
    is_win BOOLEAN NOT NULL,
    game_id INT NOT NULL,
    PRIMARY KEY (id)
);
