package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;

import java.util.List;

@JdbcTest
class PlayerDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PlayerDao jdbcPlayerDao;
    private GameDao jdbcGameDao;

    @BeforeEach
    public void setUp() {
        jdbcPlayerDao = new JdbcPlayerDao(jdbcTemplate);
        jdbcGameDao = new JdbcGameDao(jdbcTemplate);
    }

    @Test
    void insertPlayersTest() {
        // given
        int gameId = jdbcGameDao.insert("jena", 3);
        List<Car> cars = List.of(new Car("jena", 0), new Car("odo", 2), new Car("pobi", 4));

        // when, then
        Assertions.assertThatNoException().isThrownBy(() -> jdbcPlayerDao.insert(gameId, cars));
    }
}
