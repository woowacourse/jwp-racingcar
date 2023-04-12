-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
DROP TABLE PLAY_RESULT IF EXISTS;
CREATE TABLE PLAY_RESULT (
    id          INT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE racing_game(
    id BIGINT NOT NULL  AUTO_INCREMENT,
    trial_count INT,
    play_time DATETIME,
    PRIMARY KEY (id)
);
