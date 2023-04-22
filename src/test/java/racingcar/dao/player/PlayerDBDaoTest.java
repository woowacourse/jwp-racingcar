package racingcar.dao.player;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.entity.Player;

@JdbcTest
class PlayerDBDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setting() {
        jdbcTemplate.execute("DROP TABLE player IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE player("
            + "player_id SERIAL, "
            + "name VARCHAR(255), "
            + "position INT, "
            + "is_winner BIT, "
            + "game_id INT"
            + ")");

        jdbcTemplate.batchUpdate("INSERT INTO player(name, position, is_winner, game_id) values ('아코', 10, 1, 1)");
    }

    @DisplayName("플레이어들을 저장한다.")
    @Test
    void save_players() {
        // given
        List<Player> players = List.of(new Player("아코", 10, true, 1L));
        PlayerDBDao playerDBDao = new PlayerDBDao(jdbcTemplate);

        // when + then
        playerDBDao.insertPlayer(players);

    }
}