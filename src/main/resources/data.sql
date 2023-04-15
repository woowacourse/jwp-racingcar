
create TABLE IF NOT EXISTS GAME_RESULT(
    game_id  INT NOT NULL AUTO_INCREMENT,
    winners VARCHAR(255) NOT NULL,
    trial_count INT NOT NULL,
    created_at DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY (game_id)
);

create TABLE IF NOT EXISTS RESULT_CAR(
    car_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    position INT NOT NULL,
    game_id INT NOT NULL,
    PRIMARY KEY (car_id),
    FOREIGN KEY (game_id) REFERENCES RESULT_CAR(car_id) ON update CASCADE
);
