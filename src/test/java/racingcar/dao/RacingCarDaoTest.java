package racingcar.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.entity.CarEntity;
import racingcar.entity.GameEntity;

import java.util.List;

@JdbcTest
class RacingCarDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RacingCarDao racingCarDao;
    private RacingGameDao racingGameDao;

    @BeforeEach
    void setUp() {
        racingGameDao = new RacingGameDao(jdbcTemplate);
        racingCarDao = new RacingCarDao(jdbcTemplate);
    }

    @Test
    void saveGame() {
        int id = racingGameDao.save(new GameEntity.Builder().count(1).winners("매튜").build());
        CarEntity carEntity = generateCarEntity(id);

        Assertions.assertDoesNotThrow(() -> racingCarDao.save(carEntity));
    }

    @Test
    void findAll() {
        int id = racingGameDao.save(new GameEntity.Builder().count(1).winners("매튜").build());
        for (int i = 0; i < 2; i++) {
            racingCarDao.save(generateCarEntity(id));
        }

        List<CarEntity> resultCar = racingCarDao.findByGameId(id);

        Assertions.assertEquals(resultCar.size(), 2);
    }

    private CarEntity generateCarEntity(int gameId) {
        return new CarEntity.Builder()
                        .name("매튜")
                        .position(4)
                        .gameId(gameId)
                        .build();
    }

}
