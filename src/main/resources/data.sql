-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
drop table if exists game;

create table if not exists game
(
    game_id Integer auto_increment primary key,
    created_at  datetime default current_timestamp,
    trial_count Integer
);

drop table if exists game_log;
create table if not exists game_log
(
    game_id     Integer,
    player_name     varchar(20),
    result_position integer
);

drop table if exists winners;
create table if not exists winners
(
    game_id integer,
    winner      varchar(20)
);
