CREATE TABLE racing
(
    `id`         INT      NOT NULL AUTO_INCREMENT,
    `trialCount` INT      NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE car_info
(
    `id`        INT        NOT NULL AUTO_INCREMENT,
    `racing_id` INT        NOT NULL,
    `name`      VARCHAR(5) NOT NULL,
    `position`  INT        NOT NULL,
    `is_winner` BOOLEAN    NOT NULL,
    FOREIGN KEY (racing_id) REFERENCES `racing` (id),
    PRIMARY KEY (id)
);
