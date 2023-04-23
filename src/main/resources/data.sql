-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
drop table if exists player_result;
drop table if exists game;

create table game
(
    id          bigint auto_increment
        primary key,
    trial_count int             not null,
    winners     varchar(100)    not null,
    created_at  datetime        not null default current_timestamp
);

create table player_result
(
    id             bigint auto_increment
        primary key,
    name           varchar(10) not null,
    final_position int         not null,
    game_id        bigint      not null,
    constraint player_result_game_id_fk
        foreign key (game_id) references game (id)
);
