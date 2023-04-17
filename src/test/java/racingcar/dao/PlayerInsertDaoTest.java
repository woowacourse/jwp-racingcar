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
class PlayerInsertDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PlayerInsertDao jdbcPlayerInsertDao;
    private GameInsertDao jdbcGameInsertDao;

    @BeforeEach
    public void setUp() {
        jdbcPlayerInsertDao = new JdbcPlayerInsertDao(jdbcTemplate.getDataSource());
        jdbcGameInsertDao = new JdbcGameInsertDao(jdbcTemplate.getDataSource());
    }

    @Test
    void insertPlayersTest() {
        // given
        int gameId = jdbcGameInsertDao.insertGame("jena", 3);
        List<Car> cars = List.of(new Car("jena", 0), new Car("odo", 2), new Car("pobi", 4));

        // when, then
        Assertions.assertThatNoException().isThrownBy(() -> jdbcPlayerInsertDao.insertPlayers(gameId, cars));
    }
}
