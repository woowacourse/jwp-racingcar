package racingcar.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    private JdbcTemplate jdbcTemplate;

    private Long gameId;

    @BeforeEach
    void setUp() {
        RacingGameDao racingGameDao = new RacingGameJdbcDao(jdbcTemplate);
        gameId = racingGameDao.save(List.of("boxster"), 10);

        CarDto carDto1 = CarDto.of("boxster", 10);
        CarDto carDto2 = CarDto.of("encho", 7);

        jdbcTemplate.update("INSERT INTO CAR (name, position, racing_game_id) VALUES (?, ?, ?)",
                carDto1.getName(), carDto1.getPosition(), gameId);
        jdbcTemplate.update("INSERT INTO CAR (name, position, racing_game_id) VALUES (?, ?, ?)",
                carDto2.getName(), carDto2.getPosition(), gameId);
    }

    @Test
    void insert() {
        CarDto carDto = CarDto.of("car", 9);
        carDao.save(gameId, carDto);

        List<CarDto> cars = carDao.findCarsByGameId(gameId);

        Assertions.assertThat(cars).hasSize(3);
    }
}
