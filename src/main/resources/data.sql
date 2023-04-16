DROP TABLE racing_car if exists;
DROP TABLE racing_game if exists;

CREATE TABLE racing_game (
    id          INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    trial_count INT         NOT NULL,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp
);

CREATE TABLE racing_car (
    id          INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(5)  NOT NULL,
    position    INT         NOT NULL,
    racing_game_id  INT     NOT NULL,
    FOREIGN KEY (racing_game_id) REFERENCES racing_game(id) ON DELETE CASCADE
);
