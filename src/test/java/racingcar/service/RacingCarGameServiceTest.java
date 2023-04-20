package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.dto.GameInitializationRequest;
import racingcar.dto.GameResultResponse;
import racingcar.repository.dao.GameDao;
import racingcar.repository.dao.GameWinnerDao;
import racingcar.repository.dao.PlayerDao;
import racingcar.repository.dao.PlayerPositionDao;
import racingcar.utils.NumberGenerator;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RacingCarGameServiceTest {

    private static final int FIRST_GAME_ID = 1;

    private final RacingCarGameService racingCarGameService;
    private final PlayerDao playerDao;
    private final GameDao gameDao;
    private final PlayerPositionDao playerPositionDao;
    private final GameWinnerDao gameWinnerDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    public void afterEach() {
        final List<String> truncateQueries = getTruncateQueries(jdbcTemplate);
        truncateTables(jdbcTemplate, truncateQueries);
    }

    private List<String> getTruncateQueries(final JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForList("SELECT Concat('TRUNCATE TABLE ', TABLE_NAME, ';') AS q FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'", String.class);
    }

    private void truncateTables(final JdbcTemplate jdbcTemplate, final List<String> truncateQueries) {
        execute(jdbcTemplate, "SET REFERENTIAL_INTEGRITY FALSE");
        truncateQueries.forEach(v -> execute(jdbcTemplate, v));
        execute(jdbcTemplate, "SET REFERENTIAL_INTEGRITY TRUE");
    }

    private void execute(final JdbcTemplate jdbcTemplate, final String query) {
        jdbcTemplate.execute(query);
    }

    @Autowired
    public RacingCarGameServiceTest(final PlayerDao playerDao,
                                    final GameDao gameDao,
                                    final PlayerPositionDao playerPositionDao,
                                    final GameWinnerDao gameWinnerDao) {
        this.racingCarGameService = new RacingCarGameService(
                new TestNumberGenerator(List.of(9, 0, 9)), playerDao, gameDao, playerPositionDao, gameWinnerDao);
        this.playerDao = playerDao;
        this.gameDao = gameDao;
        this.playerPositionDao = playerPositionDao;
        this.gameWinnerDao = gameWinnerDao;
    }

    @Test
    void 자동차_경주_게임을_진행하고_결과_값을_반환한다() {
        final GameInitializationRequest gameInitializationRequest = new GameInitializationRequest("a,b,c", 1);
        final GameResultResponse gameResultResponse = racingCarGameService.raceCar(gameInitializationRequest);

        final String winners = gameResultResponse.getWinners();

        assertThat(winners).isEqualTo("a,c");
    }

    static class TestNumberGenerator implements NumberGenerator {

        private final List<Integer> testNumberList;
        private int index = 0;

        public TestNumberGenerator(final List<Integer> testNumberList) {
            this.testNumberList = testNumberList;
        }

        @Override
        public int generate() {
            return testNumberList.get(index++);
        }
    }
}
