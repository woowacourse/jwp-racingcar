CREATE TABLE racing_game
(
    id         INT primary key NOT NULL AUTO_INCREMENT,
    winners    VARCHAR(50)     NOT NULL,
    created_at DATETIME        NOT NULL default current_timestamp,
    trial      int             not null
);
create table player
(
    id             int primary key not null auto_increment,
    name           varchar(10)     not null,
    position       int             not null,
    racing_game_id int             not null,
    foreign key (racing_game_id) references racing_game (id)
);
