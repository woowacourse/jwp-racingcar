package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.engine.Engine;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class RacingGameTest {
    private final Engine engine = () -> true;

    @DisplayName("더 많이 움직인 자동차가 우승자가 된다.")
    @Test
    public void getWinnersTest() {
        Car car1 = Car.of(Name.from("k7"), engine);
        Car car2 = Car.of(Name.from("audi"), engine);

        Cars cars = Cars.from(Arrays.asList(car1, car2));

        moveByCount(car1, 5);
        moveByCount(car2, 7);

        assertThat(cars.getWinners().getCars())
                .containsExactly(car2);
    }

    @DisplayName("많이 움직인 자동차가 2개 이상인 경우 공동 우승자가 된다.")
    @Test
    public void getJointWinnersTest() {
        Car car1 = Car.of(Name.from("k7"), engine);
        Car car2 = Car.of(Name.from("audi"), engine);
        Car car3 = Car.of(Name.from("bmw"), engine);


        Cars cars = Cars.from(Arrays.asList(car1, car2, car3));

        moveByCount(car1, 5);
        moveByCount(car2, 7);
        moveByCount(car3, 7);

        assertThat(cars.getWinners().getCars())
                .containsExactly(car2, car3);
    }

    public void moveByCount(Car car, int count) {
        for (int i = 0; i < count; i++) {
            car.tryMove();
        }
    }
}
