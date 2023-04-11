package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import racingcar.domain.Car;
import racingcar.domain.Game;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameDaoTest {
    private final GameDao gameDao;

    public GameDaoTest(@Autowired JdbcTemplate jdbcTemplate) {
        this.gameDao = new GameDao(jdbcTemplate);
    }

    @DisplayName("selectAll")
    @Test
    void test_() {
        List<Car> cars = List.of(new Car("asdf"));
        Game game = new Game(cars, 2);
        game.playOnceWith(() -> true);
        game.playOnceWith(() -> true);
        gameDao.insertResult(game);

        assertThat(gameDao.selectAll().get(0).getTrialCount()).isEqualTo(2);
    }
}