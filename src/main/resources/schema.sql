CREATE TABLE RACE_RESULT
(
    id          INT          NOT NULL AUTO_INCREMENT,
    trial_count INT          NOT NULL,
    winners     VARCHAR(255) NOT NULL,
    created_at  DATETIME     NOT NULL,
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
