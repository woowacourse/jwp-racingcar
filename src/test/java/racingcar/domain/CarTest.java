package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.engine.ImmovableEngine;
import racingcar.domain.engine.MovableEngine;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("자동차는 ")
class CarTest {
    private Car car;

    @DisplayName("4 이상일 경우 전진한다.")
    @Test
    public void moveForwardTest() {
        car = new Car(new Name("쥬니"), new MovableEngine());

        car.tryMove();

        assertThat(car.getPosition())
                .isEqualTo(1);
    }

    @DisplayName("3 이하일 경우 전진하지 않는다.")
    @Test
    public void notMoveForwardTest() {
        car = new Car(new Name("쥬니"), new ImmovableEngine());

        car.tryMove();

        assertThat(car.getPosition())
                .isEqualTo(0);
    }
}
