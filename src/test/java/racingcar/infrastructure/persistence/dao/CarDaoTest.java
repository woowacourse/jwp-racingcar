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
import racingcar.infrastructure.persistence.entity.CarEntity;
import racingcar.infrastructure.persistence.entity.RacingGameEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
@SpringBootTest
class CarDaoTest {

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

        // when
        gameId = racingGameDao.save(racingGameEntity);
    }

    @Test
    void save() {
        // given
        final CarDao dao = new CarDao(template);
        final Car car = new Car("말랑");
        car.move(9);
        car.move(9);
        car.move(9);
        final CarEntity carEntity = new CarEntity(car, gameId);

        // when
        final Long id = dao.save(carEntity);

        // then
        final List<CarEntity> carById = dao.findByGameId(gameId);
        assertAll(
                () -> assertThat(carById.size()).isEqualTo(1),
                () -> assertThat(carById.get(0).getPosition()).isEqualTo(3)
        );
    }
}
