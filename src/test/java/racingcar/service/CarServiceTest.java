package racingcar.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import racingcar.controller.dto.GameInfoRequest;
import racingcar.dao.car.CarDao;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.util.NumberGenerator;
import racingcar.util.RandomNumberGenerator;

@SpringBootTest
class CarServiceTest {

    static class FixedNumberGenerator implements NumberGenerator {

        @Override
        public int generate() {
            return 5;
        }
    }

    @Autowired
    private CarDao carDao;
    private final CarService carService;

    public CarServiceTest() {
        this.carService = new CarService(carDao, new FixedNumberGenerator());
    }

    @RepeatedTest(10)
    @DisplayName("자동차 경주를 진행한다.")
    void race() {
        // given
        GameInfoRequest gameInfoRequest = new GameInfoRequest("성하,이오,코코닥", 2);

        // when
        RacingCars carsAfterRace = carService.race(gameInfoRequest);
        List<Car> cars = carsAfterRace.getCars();

        // then
        for (Car car : cars) {
            assertThat(car.getPosition() > 0).isTrue();
        }
    }
}
