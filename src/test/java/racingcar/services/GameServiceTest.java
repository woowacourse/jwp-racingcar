package racingcar.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import racingcar.model.car.Car;
import racingcar.model.car.Cars;
import racingcar.model.manager.ThresholdCarMoveManager;

class GameServiceTest {

    GameService gameService;
    Cars cars;

    @BeforeEach
    public void setUp() {
        cars = new Cars(new ArrayList<>());
        gameService = new GameService(cars, new ThresholdCarMoveManager());
    }

    @Test
    void createCars() {
        String namesInput = "폴로,이리내";
        gameService.createCars(namesInput);

        assertThat(cars.getCurrentResult()).containsExactlyInAnyOrder(new Car("폴로"), new Car("이리내"));
    }
}
