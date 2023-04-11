CREATE TABLE games (
    id          INT         NOT NULL AUTO_INCREMENT,
    created_at  DATETIME    NOT NULL default current_timestamp,
    trial_count INT         NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE cars (
    game_id INT NOT NULL ,
    name VARCHAR(50) NOT NULL ,
    position INT NOT NULL ,
    FOREIGN KEY (game_id) REFERENCES games(id)
);

CREATE TABLE winners (
    game_id INT NOT NULL ,
    name VARCHAR(50) NOT NULL ,
    FOREIGN KEY (game_id) REFERENCES games(id) ,
    FOREIGN KEY (game_id, name) REFERENCES cars(game_id, name)
)

