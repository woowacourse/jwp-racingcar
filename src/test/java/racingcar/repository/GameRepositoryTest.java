package racingcar.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import racingcar.service.TryCount;

@JdbcTest
class GameRepositoryTest {

    private GameRepository gameRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        gameRepository = new GameRepository(jdbcTemplate.getDataSource());
        jdbcTemplate.execute("ALTER TABLE game ALTER COLUMN id RESTART WITH 1");
    }

    @Test
    @DisplayName("TryCount를 받아 게임을 저장한다.")
    void insertGame() {
        TryCount tryCount = new TryCount(10);
        long gameId = gameRepository.save(tryCount.getCount()).longValue();

        assertThat(gameId).isEqualTo(1L);
    }
}
