package racingcar.infrastructure.persistence.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.GameTime;
import racingcar.domain.RacingGame;
import racingcar.domain.Winner;
import racingcar.infrastructure.persistence.entity.RacingGameEntity;
import racingcar.infrastructure.persistence.entity.WinnerEntity;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class WinnerDaoTest {

    @Autowired
    private JdbcTemplate template;
    private Long gameId;

    @BeforeEach
    void setUp() {
        final RacingGameDao racingGameDao = new RacingGameDao(template);
        final Cars cars = new Cars(Stream.of("브리", "토미", "브라운")
                .map(Car::new)
                .collect(Collectors.toList()));
        final GameTime gameTime = new GameTime("10");
        final RacingGame racingGame = new RacingGame(cars, gameTime);
        final RacingGameEntity racingGameEntity = new RacingGameEntity(racingGame);
        gameId = racingGameDao.save(racingGameEntity);
    }

    @Test
    void save() {
        // given
        final WinnerDao dao = new WinnerDao(template);
        final Winner winner = new Winner("주드");
        final WinnerEntity winnerEntity = new WinnerEntity(winner, gameId);

        // when
        dao.save(winnerEntity);

        // then
        assertThat(dao.findByGameId(gameId).size()).isEqualTo(1);
    }
}
