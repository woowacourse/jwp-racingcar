package racingcar.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import racingcar.dao.car.CarDaoWebImpl;
import racingcar.dao.gameresult.GameResultDaoWebImpl;
import racingcar.domain.Car;
import racingcar.domain.RacingGame;
import racingcar.domain.TryCount;
import racingcar.utils.DtoMapper;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CarDaoWebImplTest {

    @Autowired
    private CarDaoWebImpl carDaoWebImpl;

    @Autowired
    private GameResultDaoWebImpl gameResultDaoWebImpl;

    @Test
    @Rollback
    void save() {
        final RacingGame racingGame = new RacingGame(null, new TryCount(3));
        Long gameResultId = gameResultDaoWebImpl.save(DtoMapper.toRacingGameDto(racingGame));
        assertThat(1L).isEqualTo(gameResultId);

        Car car = new Car("헙크", 3, true);
        Long carId = carDaoWebImpl.save(DtoMapper.toCarDto(car));
        assertThat(1L).isEqualTo(carId);
    }
}
