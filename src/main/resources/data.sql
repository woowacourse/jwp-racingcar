-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE PLAY_RESULT (
    id          INT         NOT NULL AUTO_INCREMENT,
    count       INT         NOT NULL ,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER_INFO(
    id          INT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(6)  NOT NULL ,
    position    INT         NOT NULL ,
    play_result_id   INT         NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (play_result_id)
    REFERENCES PLAY_RESULT(id)
);
