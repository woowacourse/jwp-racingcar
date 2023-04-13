CREATE TABLE `game` (
                        `id` long PRIMARY KEY NOT NULL AUTO_INCREMENT,
                        `trial_count` int NOT NULL,
                        `date` datetime NOT NULL
);

CREATE TABLE `car` (
                       `id` long PRIMARY KEY NOT NULL AUTO_INCREMENT,
                       `game_id` long NOT NULL,
                       `name` Varchar(40) NOT NULL,
                       `position` int NOT NULL
);

CREATE TABLE `winner` (
                          `id` long PRIMARY KEY NOT NULL AUTO_INCREMENT,
                          `game_id` long NOT NULL,
                          `winner` VARCHAR(40) NOT NULL
);

ALTER TABLE `car` ADD FOREIGN KEY (`game_id`) REFERENCES `game` (`id`);

ALTER TABLE `winner` ADD FOREIGN KEY (`game_id`) REFERENCES `game` (`id`);
