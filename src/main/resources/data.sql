create table game
(
    game_number bigint auto_increment,
    created_at  datetime default current_timestamp,
    trial_count Integer,
    PRIMARY KEY (game_number)
);

create table game_log
(
    game_number     bigint,
    player_name     varchar(20),
    result_position integer,
    FOREIGN KEY (game_number) references game (game_number)
);

create table winners
(
    game_number bigint,
    winner      varchar(20),
    FOREIGN KEY (game_number) references game (game_number)
);
