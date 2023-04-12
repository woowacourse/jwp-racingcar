-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
-- TODO: 인메모리 DB를 사용할 때, 테이블이 어디에 저장되는가 (어플리케이션을 종료해도 데이터가 남아있는 이유)
DROP TABLE car_record IF EXISTS;
DROP TABLE racing_game IF EXISTS;

CREATE TABLE racing_game(
    id BIGINT NOT NULL AUTO_INCREMENT,
    trial_count INT,
    play_time DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE car_record(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    position INT,
    is_winner TINYINT,
    game_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_id) REFERENCES racing_game(id)
);
