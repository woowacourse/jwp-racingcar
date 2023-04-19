package racingcar.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import racingcar.entity.Game;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class GameDaoTest {

    @Autowired
    private DataSource dataSource;

    @Test
    @DisplayName("게임 결과가 DB에 잘 저장되는지 확인")
    void save() {
        // given
        final GameDao gameDao = new GameDao(dataSource);
        final Game game = new Game(10, "ditoo");

        // when
        final Game savedGame = gameDao.save(game);

        // then
        assertThat(savedGame.getTrialCount()).isEqualTo(game.getTrialCount());
        assertThat(savedGame.getWinners()).isEqualTo(game.getWinners());
        assertThat(savedGame.getId()).isNotNull();
    }
}