package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarTest {

    @Test
    @DisplayName("이름이 5글자 초과면 예외")
    void nameLengthEx() {
        assertThatThrownBy(() -> new Car("loooooongName"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Action이 MOVE 이면 position은 1 증가한다.")
    void moveTest() {
        Car car = new Car("test");

        car.move(Action.MOVE);

        int position = car.getPosition();
        assertThat(position).isEqualTo(1);
    }

    @Test
    @DisplayName("Action이 STAY 이면 position은 증가하지않는다.")
    void notMoveTest() {
        Car boxster = new Car("boxer");
        boxster.move(Action.STAY);

        assertThat(boxster.getPosition()).isZero();
    }

    @Test
    @DisplayName("position이 앞에 있는 차를 반환한다")
    void winTest() {
        Car boxster = new Car("박스터");
        Car hyundai = new Car("현대");

        boxster.move(Action.MOVE);

        Car win = boxster.isWin(hyundai);

        assertThat(win).isEqualTo(boxster);
    }
}
