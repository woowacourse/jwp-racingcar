package racingcar.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.CarDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ResultCarJdbcDaoTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ResultCarDao resultCarDao;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");
        jdbcTemplate.execute("TRUNCATE TABLE GAME_RESULT");
        jdbcTemplate.execute("TRUNCATE TABLE RESULT_CAR");
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
    }

    @Test
    void save_result_car__fail_when_game_referenceKey_not_exist() {
        assertThatThrownBy(() -> resultCarDao.save(50, List.of(new CarDto("aaa", 5))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR]: 존재하지 않는 게임입니다.");
    }

    @Test
    void find_car_when_not_exist() {
        List<CarDto> cars = resultCarDao.findByGameId(77);
        assertThat(cars).isEmpty();
    }
}
