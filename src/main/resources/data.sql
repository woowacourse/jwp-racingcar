CREATE TABLE GAME
(
    game_id     INT         UNSIGNED NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    trial_count INT         UNSIGNED NOT NULL,

    PRIMARY KEY (game_id)
);

CREATE TABLE PLAYER
(
    player_id INT         UNSIGNED NOT NULL AUTO_INCREMENT,
    game_id   INT         UNSIGNED NOT NULL,
    name      VARCHAR(10) NOT NULL,
    position  INT         UNSIGNED NOT NULL,

    FOREIGN KEY (game_id) REFERENCES GAME (game_id),
    PRIMARY KEY (player_id)
)
