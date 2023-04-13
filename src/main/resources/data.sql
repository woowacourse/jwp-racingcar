CREATE TABLE IF NOT EXISTS GAME (
    id          INT         NOT NULL AUTO_INCREMENT,
    trial_count  INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS CAR (
    id          INT         NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    position    INT         NOT NULL,
    is_winner    TINYINT(1) NOT NULL DEFAULT 0,
    game_id     INT         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id)
    REFERENCES GAME(id) ON DELETE CASCADE
);
