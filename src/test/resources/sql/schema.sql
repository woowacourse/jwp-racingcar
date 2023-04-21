CREATE TABLE RACE_RESULT
(
    id          BIGINT  NOT NULL AUTO_INCREMENT,
    trial_count INT          NOT NULL,
    winners     VARCHAR(255) NOT NULL,
    created_at  DATETIME     NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE CAR
(
    id             BIGINT  NOT NULL AUTO_INCREMENT,
    name           VARCHAR(50) NOT NULL,
    position       INT         NOT NULL,
    race_result_id BIGINT      NOT NULL,
    created_at     DATETIME    NOT NULL,
    PRIMARY KEY (id)
);
