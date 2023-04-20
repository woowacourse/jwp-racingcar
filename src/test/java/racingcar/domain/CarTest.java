package racingcar.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("자동차는 ")
class CarTest {
    private Car car;

    @DisplayName("4 이상일 경우 전진한다.")
    @Test
    public void moveForwardTest() {
        car = Car.from(Name.from("쥬니"));

        car.tryMove(() -> true);

        Position position = Position.create();
        position.move();

        Assertions.assertThat(car.getPosition())
                .isEqualTo(position.getPosition());
    }

    @DisplayName("3 이하일 경우 전진하지 않는다.")
    @Test
    public void notMoveForwardTest() {
        car = Car.from(Name.from("쥬니"));

        car.tryMove(() -> false);

        Position position = Position.create();

        Assertions.assertThat(car.getPosition())
                .isEqualTo(position.getPosition());
    }
}
