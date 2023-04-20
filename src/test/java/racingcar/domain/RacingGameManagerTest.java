package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class RacingGameManagerTest {
    private final RacingGameManager racingGameManager = RacingGameManager.getInstance();

    @DisplayName("더 많이 움직인 자동차가 우승자가 된다.")
    @Test
    public void getWinnersTest() {
        Car car1 = Car.from(Name.from("k7"));
        Car car2 = Car.from(Name.from("audi"));

        Cars cars = Cars.from(Arrays.asList(car1, car2));

        moveByCount(car1, 5);
        moveByCount(car2, 7);

        assertThat(racingGameManager.decideWinners(cars).getCars())
                .containsExactly(car2);
    }

    @DisplayName("많이 움직인 자동차가 2개 이상인 경우 공동 우승자가 된다.")
    @Test
    public void getJointWinnersTest() {
        Car car1 = Car.from(Name.from("k7"));
        Car car2 = Car.from(Name.from("audi"));
        Car car3 = Car.from(Name.from("bmw"));


        Cars cars = Cars.from(Arrays.asList(car1, car2, car3));

        moveByCount(car1, 5);
        moveByCount(car2, 7);
        moveByCount(car3, 7);

        assertThat(racingGameManager.decideWinners(cars).getCars())
                .containsExactly(car2, car3);
    }

    public void moveByCount(Car car, int count) {
        for (int i = 0; i < count; i++) {
            car.tryMove(() -> true);
        }
    }
}
