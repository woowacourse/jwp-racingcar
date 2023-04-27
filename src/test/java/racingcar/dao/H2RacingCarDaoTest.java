package racingcar.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.dto.RacingCarDto;

@JdbcTest
@Transactional
class H2RacingCarDaoTest {
    private H2RacingCarDao h2RacingCarDao;
    private Long gameId;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setup() {
        jdbcTemplate.update("DELETE FROM racing_car");

        H2GameDao h2GameDao = new H2GameDao(jdbcTemplate);
        h2RacingCarDao = new H2RacingCarDao(jdbcTemplate);

        gameId = h2GameDao.insert(10, List.of("토리", "홍실"));
        h2RacingCarDao.insert(gameId, "토리", 10);
    }

    @DisplayName("자동차 이름과 포지션을 저장할 수 있다.")
    @Test
    void Should_Save_When_InsertRacingCar() {
        assertDoesNotThrow(() -> h2RacingCarDao.insert(gameId, "토리", 9));
    }

    @DisplayName("게임 아이디를 통해 해당 게임을 진행한 자동차들의 이름과 포지션을 반환할 수 있다.")
    @Test
    void Should_GetGameResult_When_SelectByGameId() {
        List<RacingCarDto> racingCarResult = h2RacingCarDao.selectByGameId(Math.toIntExact(gameId));

        assertAll(
                () -> assertThat(racingCarResult.get(0).getName()).isEqualTo("토리"),
                () -> assertThat(racingCarResult.get(0).getPosition()).isEqualTo(10));
    }
}
