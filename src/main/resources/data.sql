-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE PLAY_RESULT (
    id          BIGINT         NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE PLAYER (
    id              BIGINT NOT NULL AUTO_INCREMENT,
    play_result_id  BIGINT NOT NULL,
    winner          BOOL NOT NULL,
    name            VARCHAR(5) NOT NULL,
    position        INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (play_result_id) REFERENCES PLAY_RESULT (id)
);
