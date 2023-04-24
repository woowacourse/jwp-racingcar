CREATE TABLE IF NOT EXISTS racing_game
(
    id          INT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    trial_count INT      NOT NULL,
    created_at  DATETIME NOT NULL default current_timestamp
);

CREATE TABLE IF NOT EXISTS racing_car
(
    id             INT        NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(5) NOT NULL,
    position       INT        NOT NULL,
    racing_game_id INT        NOT NULL,
    is_winner      BOOLEAN    NOT NULL,
    FOREIGN KEY (racing_game_id) REFERENCES racing_game (id) ON DELETE CASCADE
);
