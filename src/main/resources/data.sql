CREATE TABLE `game` (
                        `id` long PRIMARY KEY NOT NULL AUTO_INCREMENT,
                        `trialCount` int NOT NULL,
                        `date` date NOT NULL
);

CREATE TABLE `car` (
                       `id` long PRIMARY KEY NOT NULL AUTO_INCREMENT,
                       `g_id` long NOT NULL,
                       `name` Varchar(40) NOT NULL,
                       `position` int NOT NULL
);

CREATE TABLE `winner` (
                          `id` long PRIMARY KEY NOT NULL AUTO_INCREMENT,
                          `g_id` long NOT NULL,
                          `c_id` long NOT NULL
);

ALTER TABLE `car` ADD FOREIGN KEY (`g_id`) REFERENCES `game` (`id`);

ALTER TABLE `winner` ADD FOREIGN KEY (`g_id`) REFERENCES `game` (`id`);

ALTER TABLE `winner` ADD FOREIGN KEY (`c_id`) REFERENCES `car` (`id`);
