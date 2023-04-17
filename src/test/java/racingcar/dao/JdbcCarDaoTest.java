package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import racingcar.dao.car.JdbcCarDao;
import racingcar.dao.entity.Car;
import racingcar.dao.entity.Game;
import racingcar.dao.game.JdbcGameDao;
import racingcar.dto.CarDto;

@JdbcTest
class JdbcCarDaoTest {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DataSource dataSource;
    JdbcGameDao jdbcGameDao;
    JdbcCarDao jdbcCarDao;

    @BeforeEach
    void setUp() {
        jdbcGameDao = new JdbcGameDao(dataSource, jdbcTemplate);
        jdbcCarDao = new JdbcCarDao(jdbcTemplate);
    }

    @Test
    @DisplayName("자동차의 이동 결과를 저장한다.")
    void insertCar() {
        List<CarDto> carDtos = List.of(
                new CarDto("폴로", 1),
                new CarDto("이리내", 2)
        );
        long gameId = jdbcGameDao.saveGame(new Game(1));
        jdbcCarDao.insertCar(carDtos, gameId);

        List<Car> carsInfoByGameId = jdbcCarDao.findAllCars();

        assertThat(carsInfoByGameId).hasSize(2);
    }
}
