package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.TryCount;
import racingcar.entity.CarEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CarDaoTest {

    @Autowired
    private CarDao carDao;

    @Autowired
    private GameResultDao gameResultDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void save() {
        Long gameResultId = gameResultDao.insert(new TryCount(3));
        Assertions.assertThat(1L).isEqualTo(gameResultId);

        CarEntity carEntity = new CarEntity("헙크", 3, true, 1L);
        Long carId = carDao.save(carEntity);
        Assertions.assertThat(1L).isEqualTo(carId);
    }
}
