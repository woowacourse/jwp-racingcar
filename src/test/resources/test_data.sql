DROP TABLE TEST_PLAYERS_RESULT IF EXISTS;
DROP TABLE TEST_PLAY_RESULT IF EXISTS;
CREATE TABLE TEST_PLAY_RESULT
(
    id         INT         NOT NULL AUTO_INCREMENT,
    winners    VARCHAR(50) NOT NULL,
    play_count INT         NOT NULL,
    created_at DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE TEST_PLAYERS_RESULT
(
    id        INT         NOT NULL AUTO_INCREMENT,
    result_id INT         NOT NULL,
    name      VARCHAR(10) NOT NULL,
    position  INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (result_id) REFERENCES TEST_PLAY_RESULT (id)
);