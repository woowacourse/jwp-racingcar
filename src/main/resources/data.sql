-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE IF NOT EXISTS car (
    id bigint NOT NULL AUTO_INCREMENT,
    name VARCHAR(8) NOT NULL,
    position int(3) NOT NULL,
    is_winner boolean default false,
    game_id bigint not null,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS game (
    id bigint NOT NULL AUTO_INCREMENT,
    play_count int(3) NOT NULL,
    create_time timestamp NOT NULL,
    PRIMARY KEY (id)
);
