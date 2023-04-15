package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import racingcar.dto.CarDto;

@SpringBootTest
class CarDaoTest {

    @Autowired
    private CarDao carDao;
    @Autowired
    private PlayResultDao playResultDao;

    @Test
    void insert() {
        long id = playResultDao.insertAndReturnId(5);
        List<CarDto> cars = List.of(
                CarDto.of("도이", 1, false),
                CarDto.of("연어", 3, false),
                CarDto.of("브리", 4, true)
        );

        carDao.insert(id, cars);

        assertThat(carDao.find(id))
                .containsExactlyInAnyOrderElementsOf(cars);
    }
}
