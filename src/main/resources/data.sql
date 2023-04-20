-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE IF NOT EXISTS cars
(
    car_id   bigint     NOT NULL AUTO_INCREMENT,
    car_name VARCHAR(8) NOT NULL,
    step     int        NOT NULL,
    winner   boolean default false,
    game_id  bigint     not null,
    PRIMARY KEY (car_id)
);

CREATE TABLE IF NOT EXISTS games
(
    game_id     bigint    NOT NULL AUTO_INCREMENT,
    count       int       NOT NULL,
    create_time timestamp NOT NULL,
    PRIMARY KEY (game_id)
);
