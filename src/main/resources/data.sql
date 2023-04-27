-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.

CREATE TABLE game_result
(
    id              INT         NOT NULL        AUTO_INCREMENT,
    try_count       INT         NOT NULL,
    played_at       DATETIME    NOT NULL default current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE car
(
    id                      INT                 NOT NULL        AUTO_INCREMENT,
    player_name             VARCHAR(255)        NOT NULL,
    final_position          INT                 NOT NULL,
    is_winner               BOOL                NOT NULL,
    game_result_id          INT                 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (game_result_id) REFERENCES game_result (id)
);
