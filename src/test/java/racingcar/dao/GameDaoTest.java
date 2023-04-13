package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;
import racingcar.entity.Game;
import racingcar.utils.NumberGenerator;
import racingcar.utils.RandomNumberGenerator;

@JdbcTest
class GameDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GameDao gameDao;

    private NumberGenerator numberGenerator;

    @BeforeEach
    void setUp() {
        gameDao = new GameDao(jdbcTemplate);
        numberGenerator = new RandomNumberGenerator();
    }

    @Test
    void 게임을_저장한다() {
        Game game = Game.of(List.of(new Car("car1", numberGenerator), new Car("car2", numberGenerator)), 10);

        Long id = gameDao.insert(game);

        assertThat(id).isPositive();
    }
}
