CREATE TABLE GAME_RESULT (
    id          INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    trial_count INT         NOT NULL,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp
);

CREATE TABLE PLAYER_RESULT (
    id          INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    position    INT         NOT NULL,
    FOREIGN KEY (game_result_id) REFERENCES GAME_RESULT (`id`)
);
