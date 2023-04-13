CREATE TABLE IF NOT EXISTS racing_game(
    id BIGINT NOT NULL AUTO_INCREMENT,
    trial_count INT,
    play_time DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS car_record(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    position INT,
    is_winner TINYINT,
    game_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES racing_game(id)
);
