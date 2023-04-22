package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@JdbcTest
class RacingCarDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RacingCarDao racingCarDao;
    private RacingGameDao racingGameDao;

    @BeforeEach
    void setUp() {
        racingCarDao = new RacingCarDao(jdbcTemplate);
        racingGameDao = new RacingGameDao(jdbcTemplate);
    }

    @Test
    @DisplayName("car 테이블에 car 객체 저장")
    void saveGame() {
        racingGameDao.saveGame(new GameEntity(1, 10, LocalDateTime.now()));

        Car car = new Car("merry");
        int gameId = 1;

        assertDoesNotThrow(() -> racingCarDao.saveCar(gameId, car));
    }

    @Test
    @DisplayName("저장된 모든 Car 조회")
    void findAll() {
        racingGameDao.saveGame(new GameEntity(2, 10, LocalDateTime.now()));

        int gameId = 2;
        for (int i = 0; i < 2; i++) {
            racingCarDao.saveCar(gameId, new Car(String.valueOf(i), i));
        }

        List<CarEntity> result = racingCarDao.findCarsByGameId(gameId);

        assertEquals(result.size(), 2);
    }

}
