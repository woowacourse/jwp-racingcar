-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.

CREATE TABLE IF NOT EXISTS PLAY_RESULT
(
    id          INT         UNSIGNED NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(120) NOT NULL,
    trial_count INT         NOT NULL,
    created_at  DATETIME    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS CAR_RESULT
(
    id             INT         NOT NULL AUTO_INCREMENT,
    play_result_id INT         NOT NULL,
    name           VARCHAR(10) NOT NULL,
    position       INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (play_result_id) REFERENCES PLAY_RESULT (id) ON DELETE CASCADE
);
