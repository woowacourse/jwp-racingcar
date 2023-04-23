package racingcar.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import racingcar.entity.Game;

import javax.sql.DataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class GameDaoTest {

    @Autowired
    private DataSource dataSource;

    private GameDao gameDao;

    @BeforeEach
    void setUp() {
        gameDao = new GameDao(dataSource);
    }

    @Test
    @DisplayName("게임 결과가 DB에 잘 저장되는지 확인")
    void save() {
        // given
        final Game game = new Game(10, "ditoo");

        // when
        final Game savedGame = gameDao.save(game);

        // then
        assertThat(savedGame.getTrialCount()).isEqualTo(game.getTrialCount());
        assertThat(savedGame.getWinners()).isEqualTo(game.getWinners());
        assertThat(savedGame.getId()).isNotNull();
    }

    @Test
    @DisplayName("전체 게임 조회 테스트")
    void findAll() {
        // given
        final Game game1 = new Game(10, "ditoo");
        gameDao.save(game1);
        final Game game2 = new Game(5, "leo");
        gameDao.save(game2);

        // when
        final List<Game> allGames = gameDao.findAll();

        // then
        assertThat(allGames).hasSize(2);
        assertThat(allGames.get(0).getWinners()).isEqualTo("ditoo");
        assertThat(allGames.get(1).getWinners()).isEqualTo("leo");
    }
}