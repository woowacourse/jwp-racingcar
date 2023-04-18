package racingcar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import racingcar.dao.car.CarDaoWebImpl;
import racingcar.dao.gameresult.GameResultDaoWebImpl;
import racingcar.domain.Car;
import racingcar.domain.Names;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.domain.movingstrategy.DefaultMovingStrategy;
import racingcar.utils.DtoMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/data.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CarDaoWebImplTest {

    @Autowired
    private CarDaoWebImpl carDaoWebImpl;

    @Autowired
    private GameResultDaoWebImpl gameResultDaoWebImpl;

    @Test
    void save() {
        final List<String> names = List.of("헙크", "채채");
        final RacingGame racingGame = new RacingGame(new Names(names), new TryCount(3));
        racingGame.run(new DefaultMovingStrategy());
        Long gameResultId = gameResultDaoWebImpl.save(DtoMapper.toRacingGameDto(racingGame));
        assertThat(1L).isEqualTo(gameResultId);

        Car car = new Car("헙크", 3, true);
        Long carId = carDaoWebImpl.save(DtoMapper.toCarDto(gameResultId, car));
        assertThat(1L).isEqualTo(carId);
    }
}
