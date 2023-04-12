CREATE TABLE PLAY_RESULT (
    id          INT         NOT NULL AUTO_INCREMENT,
    trial_count INT         NOT NULL,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE CAR_RESULT (
    play_result_id  INT         NOT NULL,
    car_name        VARCHAR(5)  NOT NULL,
    car_position    INT         NOT NULL,
    PRIMARY KEY (play_result_id, car_name),
    FOREIGN KEY (play_result_id) REFERENCES PLAY_RESULT(id)
);
