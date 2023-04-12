-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
DROP TABLE racing_game IF EXISTS;
CREATE TABLE racing_game(
    id BIGINT NOT NULL AUTO_INCREMENT,
    trial_count INT,
    play_time DATETIME,
    PRIMARY KEY (id)
);

DROP TABLE car_record IF EXISTS;
CREATE TABLE car_record(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    position INT,
    is_winner TINYINT,
    game_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES racing_game(id)
);
