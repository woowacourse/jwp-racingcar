CREATE TABLE games
(
`game_id`      INT           NOT NULL AUTO_INCREMENT,
`count`        INT           NOT NULL,
`winner`       VARCHAR(100)  NOT NULL,
`timeStamp`    TIMESTAMP     NOT NULL,
PRIMARY KEY (`game_id`)
);
CREATE TABLE cars
(
`name`       VARCHAR(20) NOT NULL,
`position`     INT       NOT NULL,
`game_id`       INT       NOT NULL,
PRIMARY KEY (`name`, `game_id`),
FOREIGN KEY ( `game_id` ) REFERENCES `games` ( `game_id` )
);
