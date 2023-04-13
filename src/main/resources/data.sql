CREATE TABLE PLAY_RESULT
(
    id          BIGINT   NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(255),
    trial_count INT      NOT NULL,
    created_at  DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE CAR
(
    play_result_id BIGINT      NOT NULL,
    name           VARCHAR(10) NOT NULL,
    position       INT         NOT NULL,
    FOREIGN KEY (play_result_id) REFERENCES PLAY_RESULT (id)
);
