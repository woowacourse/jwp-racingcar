CREATE TABLE IF NOT EXISTS RACE_RESULT
(
    `race_result_id` BIGINT
(
    20
) UNIQUE NOT NULL AUTO_INCREMENT,
    `trial_count` int NOT NULL,
    `winners` VARCHAR
(
    50
) NOT NULL,
    `created_at` DATETIME NOT NULL default current_timestamp,
    PRIMARY KEY
(
    `race_result_id`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS CAR
(
    `car_id` BIGINT
(
    20
) UNIQUE NOT NULL AUTO_INCREMENT,
    `name` VARCHAR
(
    10
) NOT NULL,
    `position` int NOT NULL,
    `race_result_id` BIGINT
(
    20
),
    PRIMARY KEY
(
    `car_id`
),
    UNIQUE uk_car_name_race_result_id
(
    `name`,
    `race_result_id`
),
    FOREIGN KEY
(
    `race_result_id`
) REFERENCES RACE_RESULT
(
    `race_result_id`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_general_ci;
