drop table if exists PLAYER_RESULT;
drop table if exists PLAY_RESULT;

CREATE TABLE if not exists PLAY_RESULT (
    id          INT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    trial_count INT         NOT NULL,
    played_time  DATETIME   NOT NULL default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE if not exists PLAYER_RESULT (
     id          INT         NOT NULL AUTO_INCREMENT,
     play_result_id INT      NOT NULL,
     name     VARCHAR(50)    NOT NULL,
     position INT            NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (play_result_id) REFERENCES PLAY_RESULT(id)
);
