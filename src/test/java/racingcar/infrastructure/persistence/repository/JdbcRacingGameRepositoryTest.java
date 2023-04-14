package racingcar.infrastructure.persistence.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JdbcRacingGameRepositoryTest {

    @Autowired
    private RacingGameRepository racingGameRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private long setUpId = 0;

    @BeforeEach
    void setUp() {
        String sql = "INSERT INTO PLAY_RESULT (trial_count) VALUES (?)";
        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, 10);
            return preparedStatement;
        }, keyHolder);
        setUpId = ((long) keyHolder.getKeys().get("id"));
    }

    @Test
    void findById() {
        Optional<RacingGame> game = racingGameRepository.findById(setUpId);
        int gameTimeValue = game.get().getGameTimeValue();
        Assertions.assertAll(
                () -> assertThat(gameTimeValue).isEqualTo(10),
                () -> assertThat(game).isNotEmpty()
        );
    }

    @Test
    void save() {
        // given
        final Cars cars = new Cars(Stream.of("브리", "토미", "브라운")
                .map(Car::new)
                .collect(Collectors.toList()));
        final GameTime gameTime = new GameTime("10");
        final RacingGame racingGame = new RacingGame(cars, gameTime);

        // when
        final Long id = racingGameRepository.save(racingGame);

        // then
        assertThat(racingGameRepository.findById(id)).isNotEmpty();
    }
}
