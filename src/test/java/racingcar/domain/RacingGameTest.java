package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.engine.Engine;
import racingcar.domain.engine.ImmovableEngine;
import racingcar.domain.engine.MovableEngine;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RacingGameTest {
    private final Engine engine = new MovableEngine();

    @DisplayName("더 많이 움직인 자동차가 우승자가 된다.")
    @Test
    public void getWinnersTest() {
        Car car1 = new Car(new Name("k7"), new MovableEngine());
        Car car2 = new Car(new Name("audi"), new ImmovableEngine());

        RacingGame racingGame = new RacingGame(List.of(car1, car2));
        racingGame.moveCars(3);

        assertThat(racingGame.decideWinners()).isEqualTo("k7");
    }

    @DisplayName("많이 움직인 자동차가 2개 이상인 경우 공동 우승자가 된다.")
    @Test
    public void getJointWinnersTest() {
        Car car1 = new Car(new Name("k7"), engine);
        Car car2 = new Car(new Name("audi"), engine);
        Car car3 = new Car(new Name("bmw"), engine);


        Cars cars = new Cars(Arrays.asList(car1, car2, car3));

        moveByCount(car1, 5);
        moveByCount(car2, 7);
        moveByCount(car3, 7);

        assertThat(cars.getWinners()).isEqualTo("audi,bmw");
    }

    public void moveByCount(Car car, int count) {
        for (int i = 0; i < count; i++) {
            car.tryMove();
        }
    }
}
