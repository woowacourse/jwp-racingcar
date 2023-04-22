package racingcar.dao.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dao.entity.Game;
import racingcar.domain.Cars;
import racingcar.domain.Count;
import racingcar.domain.RacingCarGame;
import racingcar.dto.GamePlayerJoinDto;

@JdbcTest
class RacingCarGameDBDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setting() {
        jdbcTemplate.execute("DROP TABLE player IF EXISTS");
        jdbcTemplate.execute("DROP TABLE game IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE game("
            + "game_id SERIAL, "
            + "play_count INT "
            + ")");
        jdbcTemplate.execute("CREATE TABLE player("
            + "player_id SERIAL, "
            + "name VARCHAR(255), "
            + "position INT, "
            + "is_winner BIT, "
            + "game_id INT"
            + ")");

        jdbcTemplate.batchUpdate("INSERT INTO game(play_count) values (10)");
        jdbcTemplate.batchUpdate("INSERT INTO player(name, position, is_winner, game_id) values ('아코', 10, 1, 1)");
    }

    @DisplayName("game 레코드가 저장되면 game_id를 반환한다.")
    @Test
    void return_game_id_when_game_record_save() {
        // given
        List<String> carNames = List.of("아코", "마코", "파즈");
        RacingCarGame racingCarGame = new RacingCarGame(Cars.of(carNames), Count.of(10));
        Game game = Game.of(racingCarGame);
        RacingCarGameDBDao racingCarGameDBDao = new RacingCarGameDBDao(jdbcTemplate);

        // when
        Long gameId = racingCarGameDBDao.insertGameWithKeyHolder(game);

        // then
        assertThat(gameId).isEqualTo(2L);
    }

    @DisplayName("게임의 모든 결과를 가져온다.")
    @Test
    void return_game_result_All() {
        // given
        RacingCarGameDBDao racingCarGameDBDao = new RacingCarGameDBDao(jdbcTemplate);

        // when
        List<GamePlayerJoinDto> result = racingCarGameDBDao.findAll();

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getPlayerName()).isEqualTo("아코");
        assertThat(result.get(0).getPosition()).isEqualTo(10);
        assertThat(result.get(0).isWinner()).isTrue();
        assertThat(result.get(0).getGameId()).isEqualTo(1);
    }
}