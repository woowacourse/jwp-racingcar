CREATE TABLE IF NOT EXISTS RACING_GAME
(
    id         INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    winners    VARCHAR(50)     NOT NULL,
    created_at DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    trial      INT             NOT NULL
);
CREATE TABLE IF NOT EXISTS PLAYER
(
    id             INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name           VARCHAR(10)     NOT NULL,
    position       INT             NOT NULL,
    racing_game_id INT             NOT NULL,
    FOREIGN KEY (racing_game_id) REFERENCES RACING_GAME (id)
);
