create TABLE if not exists GAME (
    game_id     INT         NOT NULL AUTO_INCREMENT,
    winners     VARCHAR(50) NOT NULL,
    try_count   INT         NOT NULL,
    created_at  DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (game_id)
);

create TABLE if not exists CAR (
    car_id      INT          NOT NULL AUTO_INCREMENT,
    name        VARCHAR(5)   NOT NULL,
    position    INT          NOT NULL DEFAULT 0,
    game_id     INT          NOT NULL,
    PRIMARY KEY (car_id),
    FOREIGN KEY(game_id) REFERENCES GAME (game_id)
);
