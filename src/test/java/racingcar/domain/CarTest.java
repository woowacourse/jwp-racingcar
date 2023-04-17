package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.engine.ImmovableEngine;
import racingcar.domain.engine.MovableEngine;

@DisplayName("자동차는 ")
class CarTest {
    private Car car;

    @DisplayName("4 이상일 경우 전진한다.")
    @Test
    public void moveForwardTest() {
        car = new Car(Name.from("쥬니"), new MovableEngine());

        car.tryMove();

        Position position = Position.create();
        position.move();

        Assertions.assertThat(car.getPosition())
                .isEqualTo(position.getPosition());
    }

    @DisplayName("3 이하일 경우 전진하지 않는다.")
    @Test
    public void notMoveForwardTest() {
        car = new Car(Name.from("쥬니"), new ImmovableEngine());

        car.tryMove();

        Position position = Position.create();

        Assertions.assertThat(car.getPosition())
                .isEqualTo(position.getPosition());
    }
}
