package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;

@SpringBootTest
class RacingCarDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Long gameId;

    @BeforeEach
    void setUp() {
        final RacingGameDao racingGameDao = new RacingGameDao(jdbcTemplate);
        gameId = racingGameDao.save("루쿠", 10);
    }

    @DisplayName("자동차 정보를 저장한다.")
    @Test
    void save() {
        final RacingCarDao racingCarDao = new RacingCarDao(jdbcTemplate);

        assertThat(racingCarDao.save(gameId, new Car("다즐"))).isNotNull();
    }

}
