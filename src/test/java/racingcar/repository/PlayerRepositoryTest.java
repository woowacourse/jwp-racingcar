package racingcar.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import racingcar.domain.Car;
import racingcar.dto.RacingCarStatusResponse;

@JdbcTest
class PlayerRepositoryTest {
    private PlayerRepository playerRepository;
    private GameRepository gameRepository;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        playerRepository = new PlayerRepository(jdbcTemplate);
        gameRepository = new GameRepository(jdbcTemplate.getJdbcTemplate().getDataSource());
        jdbcTemplate.getJdbcTemplate().execute("ALTER TABLE player ALTER COLUMN id RESTART WITH 1");
    }

    @Test
    @DisplayName("플레이어를 저장한다.")
    void insertPlayer() {
        // given
        long gameId = gameRepository.insertGame(10).longValue();
        RacingCarStatusResponse veroStatusResponse = RacingCarStatusResponse.of(new Car("vero", 10));
        RacingCarStatusResponse poiStatusResponse = RacingCarStatusResponse.of(new Car("poi", 7));
        List<String> winners = List.of("vero");

        // when
        playerRepository.insertPlayer(List.of(veroStatusResponse, poiStatusResponse), winners, gameId);
        String winner = jdbcTemplate.getJdbcTemplate()
                .queryForObject("SELECT name FROM player WHERE is_winner = TRUE AND game_id = ?", String.class, gameId);
        String loser = jdbcTemplate.getJdbcTemplate()
                .queryForObject("SELECT name FROM player WHERE is_winner = FALSE AND game_id = ?", String.class, gameId);

        // expected
        assertAll(
                () -> assertThat(winner).isEqualTo("vero"),
                () -> assertThat(loser).isEqualTo("poi")
        );
    }
}
