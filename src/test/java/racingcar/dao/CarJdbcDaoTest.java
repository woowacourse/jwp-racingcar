package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.CarDto;

import java.util.List;

@SpringBootTest
@Transactional
class CarJdbcDaoTest {

    @Autowired
    private CarDao carDao;

    @Autowired
    private RacingGameDao racingGameDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void saveAllTest() {
        Long gameId = racingGameDao.save("car3", 20);
        CarDto carDto1 = CarDto.of("car1", 9);
        CarDto carDto2 = CarDto.of("car2", 10);
        CarDto carDto3 = CarDto.of("car3", 11);

        carDao.saveAll(gameId, List.of(carDto1, carDto2, carDto3));

        List<CarDto> cars = carDao.findCarsByGameId(gameId);

        Assertions.assertThat(cars).hasSize(3);
    }
}
