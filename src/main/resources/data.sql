-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.

create table game
(
    game_number Integer auto_increment primary key,
    created_at  datetime default current_timestamp,
    trial_count Integer
);

create table game_log
(
    game_number     Integer,
    player_name     varchar(20),
    result_position integer
);

create table winners
(
    game_number integer,
    winner      varchar(20)
);
