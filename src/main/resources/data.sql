CREATE TABLE games
(
    `id`        INT          NOT NULL AUTO_INCREMENT,
    `count`     INT          NOT NULL,
    `winners`   VARCHAR(100) NOT NULL,
    `play_time` TIMESTAMP    NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE cars
(
    `name`     VARCHAR(20) NOT NULL,
    `position` INT         NOT NULL,
    `game_id`  INT         NOT NULL,
    PRIMARY KEY (`name`, `game_id`),
    FOREIGN KEY (`game_id`) REFERENCES `games` (`id`)
);
