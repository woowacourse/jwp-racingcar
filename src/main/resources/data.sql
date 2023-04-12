-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE PLAY_RESULT
(
    id          INT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    trial_count INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE CAR_RESULT
(
    id             INT         NOT NULL AUTO_INCREMENT,
    play_result_id INT         NOT NULL,
    name           VARCHAR(50) NOT NULL,
    position       INT         NOT NULL,
    PRIMARY KEY (id)
);
