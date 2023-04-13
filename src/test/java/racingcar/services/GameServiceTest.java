package racingcar.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import racingcar.model.car.Car;
import racingcar.model.car.Cars;

@SpringBootTest
class GameServiceTest {

    @Autowired
    GameService gameService;

    @Test
    void createCars() {
        String namesInput = "폴로,이리내";
        Cars cars = gameService.initialize();
        gameService.createCars(cars, namesInput);

        assertThat(cars.getCurrentResult()).containsExactlyInAnyOrder(new Car("폴로"), new Car("이리내"));
    }
}
