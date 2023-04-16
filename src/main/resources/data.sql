CREATE TABLE RACING_GAME
(
    id         INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    count      INT                NOT NULL,
    created_at DATETIME           NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE CAR
(
    id             INT         NOT NULL AUTO_INCREMENT,
    racing_game_id INT         NOT NULL,
    name           VARCHAR(50) NOT NULL,
    position       INT         NOT NULL,
    is_win         BOOLEAN     NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (racing_game_id) REFERENCES RACING_GAME (id)
);
