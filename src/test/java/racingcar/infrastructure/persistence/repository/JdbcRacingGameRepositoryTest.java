package racingcar.infrastructure.persistence.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JdbcRacingGameRepositoryTest {

    @Autowired
    private RacingGameRepository racingGameRepository;

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
