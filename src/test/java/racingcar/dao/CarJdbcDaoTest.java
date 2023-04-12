package racingcar.dao;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.CarDto;

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
        gameId = racingGameDao.save(10);

        CarDto carDto1 = new CarDto("boxster", 10, true);
        CarDto carDto2 = new CarDto("encho", 7, false);

        jdbcTemplate.update("INSERT INTO CAR (name, position, is_win, racing_game_id) VALUES (?, ?, ?, ?)",
                carDto1.getName(), carDto1.getPosition(), carDto1.isWin(), gameId);
        jdbcTemplate.update("INSERT INTO CAR (name, position, is_win, racing_game_id) VALUES (?, ?, ?, ?)",
                carDto2.getName(), carDto2.getPosition(), carDto2.isWin(), gameId);

    }

    @Test
    void insert() {
        CarDto carDto = new CarDto("car", 9, false);
        carDao.save(gameId, carDto);

        List<CarDto> cars = carDao.findByGameId(gameId);

        Assertions.assertThat(cars).hasSize(3);
    }
}
