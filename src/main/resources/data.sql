CREATE TABLE IF NOT EXISTS Game (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    trial_count INT          NOT NULL,
    created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Player (
    id        BIGINT      NOT NULL AUTO_INCREMENT,
    game_id   BIGINT      NOT NULL,
    name      VARCHAR(50) NOT NULL,
    position  INT         NOT NULL,
    is_winner TINYINT     NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT game_player_id
        FOREIGN KEY (game_id)
            REFERENCES Game (id)
);
