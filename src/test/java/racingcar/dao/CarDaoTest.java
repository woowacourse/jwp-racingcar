package racingcar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import racingcar.domain.Car;
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

        Car car = new Car(1L, "헙크", 3, true);
        Long carId = carDao.save(car);
        assertThat(1L).isEqualTo(carId);
    }
}
