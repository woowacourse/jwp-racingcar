package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.CarDto;

@JdbcTest
class CarJdbcDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private CarDao carDao;
    private Long gameId;

    @BeforeEach
    void setUp() {
        carDao = new CarJdbcDao(jdbcTemplate);
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
    @DisplayName("car를 여러 개 저장한다")
    void insert() {
        List<CarDto> carDtos = List.of(new CarDto("gumak", 9, false), new CarDto("benz", 10, true));

        carDao.saveAll(gameId, carDtos);

        Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM CAR", Integer.class);

        assertThat(count).isEqualTo(4);
    }
}
