package racingcar.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import racingcar.entity.Game;
import racingcar.entity.PlayerResult;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class PlayerResultDaoTest {

    @Autowired
    private DataSource dataSource;

    @Test
    @DisplayName("플레이어 결과가 DB에 잘 저장되는지 확인")
    void save() {
        // given
        final GameDao gameDao = new GameDao(dataSource);
        final PlayerResultDao playerResultDao = new PlayerResultDao(dataSource);
        final Game game = gameDao.save(new Game(10, "디투"));
        final PlayerResult playerResult = new PlayerResult("디투", 8, game.getId());

        // when
        final PlayerResult savedPlayerResult = playerResultDao.save(playerResult);

        // then
        assertThat(savedPlayerResult.getName()).isEqualTo(playerResult.getName());
        assertThat(savedPlayerResult.getFinalPosition()).isEqualTo(playerResult.getFinalPosition());
        assertThat(savedPlayerResult.getGameId()).isEqualTo(playerResult.getGameId());
        assertThat(savedPlayerResult.getId()).isNotNull();
    }
}