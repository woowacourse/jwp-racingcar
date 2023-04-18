package racingcar.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.CarData;
import racingcar.dto.GameResultResponse;
import racingcar.persistence.dao.GameResultDao;
import racingcar.persistence.dao.PlayerResultDao;
import racingcar.persistence.repository.RacingGameRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
class RacingGameRepositoryTest {

    private final JdbcTemplate jdbcTemplate;
    private final RacingGameRepository racingGameRepository;

    @Autowired
    public RacingGameRepositoryTest(
            final JdbcTemplate jdbcTemplate
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.racingGameRepository = new RacingGameRepository(
                new GameResultDao(jdbcTemplate),
                new PlayerResultDao(jdbcTemplate)
        );
    }

    @Test
    @DisplayName("게임 결과가 데이터베이스에 저장될 수 있다")
    void shouldSaveGameResultWhenRequest() {
        String expectedWinners = "브리,브라운";
        racingGameRepository.saveGameRecord(
                new GameResultResponse(
                        expectedWinners,
                        Collections.emptyList()
                ),
                3
        );

        String actualWinners = jdbcTemplate.queryForObject("SELECT winners FROM GAME_RESULT", String.class);
        int trialCount = jdbcTemplate.queryForObject("SELECT trial_count FROM GAME_RESULT", Integer.class);
        assertThat(actualWinners).isEqualTo("브리,브라운");
        assertThat(trialCount).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어 별 정보가 데이터베이스에 저장될 수 있다")
    void shouldSaveEachPlayerResultWhenRequest() {
        String expectedWinners = "브리,브라운";
        List<CarData> carData = List.of(
                new CarData("브리", 2),
                new CarData("토미", 1),
                new CarData("브라운", 2)
        );

        racingGameRepository.saveGameRecord(
                new GameResultResponse(expectedWinners, carData),
                3
        );

        int positionOfBri = jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '브리'", Integer.class);
        int positionOfTomi = jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '토미'", Integer.class);
        int positionOfBrown = jdbcTemplate.queryForObject("SELECT position FROM PLAYER_RESULT WHERE name = '브라운'", Integer.class);
        assertAll(() -> {
            assertThat(positionOfBri).isEqualTo(2);
            assertThat(positionOfTomi).isEqualTo(1);
            assertThat(positionOfBrown).isEqualTo(2);
        });
    }
}
