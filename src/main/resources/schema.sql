CREATE TABLE PLAY_RESULT
(
    id          INT         NOT NULL AUTO_INCREMENT,
    trial_count INT         NOT NULL,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE CAR
(
    id             INT         NOT NULL AUTO_INCREMENT,
    name           VARCHAR(50) NOT NULL,
    position       INT         NOT NULL,
    play_result_id INT         NOT NULL,
    PRIMARY KEY (id)
);
