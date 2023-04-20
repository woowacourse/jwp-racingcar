package racingcar.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class H2RacingCarDaoTest {
    private H2RacingCarDao h2RacingCarDao;
    private H2GameDao h2PlayResultDao;
    private Long gameId;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setup() {
        jdbcTemplate.update("DELETE FROM racing_car");

        h2PlayResultDao = new H2GameDao(jdbcTemplate);
        h2RacingCarDao = new H2RacingCarDao(jdbcTemplate);

        gameId = h2PlayResultDao.insertWithKeyHolder(10, List.of("tori", "hong"));
    }

    @DisplayName("자동차 이름과 포지션을 저장하는 기능 테스트")
    @Test
    void Should_Success_When_InsertRacingCar() {
        assertDoesNotThrow(() -> h2RacingCarDao.insert(gameId, "tori", 9));
    }
}
