-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
-- CREATE TABLE PLAY_RESULT (
--     id          INT         NOT NULL AUTO_INCREMENT,
--     winners     VARCHAR(50) NOT NULL,
--     created_at  DATETIME    NOT NULL default current_timestamp,
--     PRIMARY KEY (id)
-- );

CREATE TABLE game (
                      id INT AUTO_INCREMENT,
                      trialCount INT NOT NULL,
                      dateTime DATETIME NOT NULL,
                      PRIMARY KEY (id)
);

CREATE TABLE player (
                        id INT NOT NULL AUTO_INCREMENT,
                        game_id INT NOT NULL,
                        name VARCHAR(45) NOT NULL,
                        position INT NOT NULL,
                        isWinner TINYINT NOT NULL,
                        PRIMARY KEY (id),
                        CONSTRAINT fk_player_game_id
                            FOREIGN KEY (game_id)
                                REFERENCES game(id)
);
