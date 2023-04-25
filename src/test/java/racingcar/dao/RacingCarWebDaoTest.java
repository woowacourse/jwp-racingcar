package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dao.web.RacingCarWebDao;
import racingcar.dao.web.RacingGameWebDao;
import racingcar.domain.Car;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@JdbcTest
class RacingCarWebDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RacingCarWebDao racingCarWebDao;
    private RacingGameWebDao racingGameWebDao;

    @BeforeEach
    void setUp() {
        racingCarWebDao = new RacingCarWebDao(jdbcTemplate);
        racingGameWebDao = new RacingGameWebDao(jdbcTemplate);
    }

    @Test
    @DisplayName("car 테이블에 car 객체 저장")
    void saveGame() {
        int savedGameId = racingGameWebDao.saveGame(new GameEntity(1, 10, LocalDateTime.now()));

        Car car = new Car("merry");

        assertDoesNotThrow(() -> racingCarWebDao.saveCar(new CarEntity(1, car.getName(), 10, savedGameId)));
    }

    @Test
    @DisplayName("저장된 모든 Car 조회")
    void findAll() {
        int savedGameId = racingGameWebDao.saveGame(new GameEntity(2, 10, LocalDateTime.now()));

        for (int i = 1; i < 3; i++) {
            racingCarWebDao.saveCar(new CarEntity(i, Integer.toString(i), 1, savedGameId));
        }

        List<CarEntity> result = racingCarWebDao.findCarsByGameId(savedGameId);

        assertEquals(2, result.size());
    }

}
