package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Name;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingCars;
import racingcar.domain.TryCount;

@JdbcTest
class RacingCarDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RacingCarGameJdbcDao racingCarDao;

    @BeforeEach
    void setUp() {
        racingCarDao = new RacingCarGameJdbcDao(jdbcTemplate);
    }

    @DisplayName("게임 플레이 저장 시 게임 ID가 반환된다.")
    @Test
    void whenInsertGameThenGetGameId() {
        //given
        final RacingCars racingCars = new RacingCars(
                List.of(new RacingCar(new Name("블랙캣")),
                        new RacingCar(new Name("리오"))
                ));
        final TryCount tryCount = new TryCount(3);
        final String sql = "SELECT COUNT(*) FROM RACING_CAR_PLAYER_LOG";

        //when
        final int gameId = racingCarDao.insertGame(racingCars, tryCount);

        //then
        assertThat(gameId).isEqualTo(1);
    }
}
