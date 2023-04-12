CREATE TABLE games
(
`gameId`        INT          NOT NULL AUTO_INCREMENT,
`count`         INT          NOT NULL,
`winner`       VARCHAR(100)  NOT NULL,
`timeStamp`    TIMESTAMP     NOT NULL,
PRIMARY KEY (`gameId`)
);
CREATE TABLE cars
(
`name`       VARCHAR(20) NOT NULL,
`position`     INT       NOT NULL,
`gameId`       INT       NOT NULL,
PRIMARY KEY (`name`, `gameId`),
FOREIGN KEY ( `gameId` ) REFERENCES `games` ( `gameId` )
);
