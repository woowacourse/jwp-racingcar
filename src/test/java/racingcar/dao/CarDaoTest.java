package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.domain.Car;

@SpringBootTest
class CarDaoTest {

    @Autowired
    private CarDao carDao;
    @Autowired
    private PlayResultDao playResultDao;

    @Test
    void insert() {
        long id = playResultDao.insertAndReturnId(5, "브리");
        List<Car> cars = List.of(
                new Car("도이", 1),
                new Car("연어", 3),
                new Car("브리", 4)
        );

        carDao.insert(id, cars);

        assertThat(carDao.find(id))
                .containsExactlyInAnyOrderElementsOf(cars);
    }
}
