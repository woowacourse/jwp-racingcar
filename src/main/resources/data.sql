-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.

CREATE TABLE GAME_RESULT
(
    id          INT         UNSIGNED NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(120) NOT NULL,
    trial_count INT         NOT NULL,
    created_at  DATETIME    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE CAR_RESULT
(
    id             INT         UNSIGNED NOT NULL AUTO_INCREMENT,
    game_result_id INT         NOT NULL,
    name           VARCHAR(10) NOT NULL,
    position       INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_result_id) REFERENCES GAME_RESULT (id) ON DELETE CASCADE
);
