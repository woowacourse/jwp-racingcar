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
        long gameId = jdbcGameDao.saveGame(1);
        jdbcCarDao.insertCar(carDtos, gameId);

        String sql = "SELECT count(*) FROM car WHERE g_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, gameId);

        assertThat(count).isEqualTo(2);
    }
}
