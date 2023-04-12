package racingcar.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.domain.Car;

@JdbcTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(ReplaceUnderscores.class)
class WinnerDaoTest {

    private WinnerDao winnerDao;
    private int gameId;
    private int winningCarId;

    @Autowired
    void setUp(final DataSource dataSource, final JdbcTemplate jdbcTemplate) {
        winnerDao = RepositoryFactory.winnerDao(dataSource, jdbcTemplate);
        gameId = RepositoryFactory.gamesDao(dataSource).save(10);
        winningCarId = RepositoryFactory.insertCarDao(dataSource).save(new Car("토미", 9), gameId).getId();
    }

    @Test
    void 우승자_저장() {
        final int result = winnerDao.save(gameId, winningCarId);

        assertThat(result).isPositive();
    }

    @Nested
    class SelectTest {

        @BeforeEach
        void setUp() {
            winnerDao.save(gameId, winningCarId);
        }

        @Test
        void 우승자_조회() {
            final List<Integer> result = winnerDao.findByGameId(gameId);

            assertAll(
                    () -> assertThat(result).hasSize(1),
                    () -> assertThat(result.get(0)).isPositive()
            );

        }
    }
}
