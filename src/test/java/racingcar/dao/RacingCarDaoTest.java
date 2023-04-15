package racingcar.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import racingcar.domain.Car;

import javax.sql.DataSource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;

@JdbcTest
class RacingCarDaoTest {

    @Autowired
    private DataSource dataSource;
    private RacingCarDao racingCarDao;
    private Long gameId;

    @BeforeEach
    void setUp() {
        final RacingGameDao racingGameDao = new RacingGameDao(dataSource);
        racingCarDao = new RacingCarDao(dataSource);
        gameId = racingGameDao.save("루쿠", 10);
    }

    @DisplayName("자동차 정보를 저장한다.")
    @Test
    void save() {
        assertThatNoException()
                .isThrownBy(() -> racingCarDao.saveAll(gameId, List.of(new Car("다즐"))));
    }
}
