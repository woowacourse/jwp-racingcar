package racingcar.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.DeterminedNumberGenerator;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.persistence.dao.GameResultDao;
import racingcar.persistence.dao.PlayerResultDao;
import racingcar.persistence.repository.RacingGameRepository;

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
        RacingGame racingGame = new RacingGame(
                List.of("브리", "브라운"),
                3,
                new RandomNumberGenerator()
        );

        racingGameRepository.saveGame(racingGame);

        String actualWinners = jdbcTemplate.queryForObject("SELECT winners FROM GAME_RESULT", String.class);
        int trialCount = jdbcTemplate.queryForObject("SELECT trial_count FROM GAME_RESULT", Integer.class);
        assertThat(actualWinners).isEqualTo("브리,브라운");
        assertThat(trialCount).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어 별 정보가 데이터베이스에 저장될 수 있다")
    void shouldSaveEachPlayerResultWhenRequest() {
        DeterminedNumberGenerator determinedNumberGenerator = new DeterminedNumberGenerator();
        determinedNumberGenerator.readRepository(List.of(
                4, 0, 4,
                4, 4, 4,
                0, 0, 0
        ));

        RacingGame racingGame = new RacingGame(
                List.of("브리", "토미", "브라운"),
                3,
                determinedNumberGenerator
        );

        racingGame.start();
        racingGameRepository.saveGame(racingGame);

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
