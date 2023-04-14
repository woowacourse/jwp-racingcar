-- TODO: 기능 구현에 필요한 내용을 추가하거나 수정하세요.
CREATE TABLE game (
  game_id INT NOT NULL AUTO_INCREMENT,
  play_count INT NOT NULL,
  winners VARCHAR(255) NOT NULL,
  created_at  DATETIME NOT NULL default current_timestamp,
  PRIMARY KEY (game_id)
);

CREATE TABLE player (
  player_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  position INT NOT NULL,
  game_id INT NOT NULL,
  PRIMARY KEY (player_id),
  CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES game(game_id)
);
