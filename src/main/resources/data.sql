CREATE TABLE RACING_GAME
(
    id          INT         NOT NULL AUTO_INCREMENT,
    trial_count INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE CAR
(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    position INT NOT NULL,
    is_win SMALLINT NOT NULL ,
    racing_game_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (racing_game_id) REFERENCES RACING_GAME (id)
)
