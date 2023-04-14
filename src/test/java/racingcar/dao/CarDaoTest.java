package racingcar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import racingcar.entity.CarEntity;
import racingcar.entity.GameResultEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CarDaoTest {

    @Autowired
    private CarDao carDao;

    @Autowired
    private GameResultDao gameResultDao;

    @Test
    @Rollback
    void save() {
        Long gameResultId = gameResultDao.save(new GameResultEntity(3));
        assertThat(1L).isEqualTo(gameResultId);

        CarEntity carEntity = new CarEntity("헙크", 3, true, 1L);
        Long carId = carDao.save(carEntity);
        assertThat(1L).isEqualTo(carId);
    }
}
