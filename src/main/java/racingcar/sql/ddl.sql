CREATE TABLE player (
    name varchar(30) PRIMARY KEY
);

CREATE TABLE game (
    id int PRIMARY KEY AUTO_INCREMENT,
    trial_count int NOT NULL,
    game_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE record (
    game_id int,
    position int NOT NULL,
    is_winner boolean NOT NULL,
    player_name varchar(30),
    PRIMARY KEY (game_id, player_name),
    FOREIGN KEY (game_id) REFERENCES game (id),
    FOREIGN KEY (player_name) REFERENCES player (name)
);
