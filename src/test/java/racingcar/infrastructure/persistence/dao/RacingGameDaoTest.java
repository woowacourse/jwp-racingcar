package racingcar.infrastructure.persistence.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Cars;
import racingcar.domain.GameTime;
import racingcar.domain.RacingGame;
import racingcar.infrastructure.persistence.entity.RacingGameEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class RacingGameDaoTest {

    @Autowired
    private JdbcTemplate template;

    @Test
    void save() {
        // given
        final RacingGameDao racingGameDao = new RacingGameDao(template);
        final Cars cars = new Cars(List.of("브리", "토미", "브라운"));
        final GameTime gameTime = new GameTime("10");
        final RacingGame racingGame = new RacingGame(cars, gameTime);
        final RacingGameEntity racingGameEntity = new RacingGameEntity(racingGame);

        // when
        final Long id = racingGameDao.save(racingGameEntity);

        // then
        assertThat(racingGameDao.findById(id)).isNotEmpty();
    }
}
