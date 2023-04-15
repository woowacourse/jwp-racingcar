package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CarTest {

    @DisplayName("자동차 이동 테스트 성공")
    @Test
    void isMoveFail() {
        Car car = new MovableCar("move");

        assertThat(car.canMove()).isEqualTo(true);
    }

    @DisplayName("자동차 이동 테스트 실패")
    @Test
    void isMoveSuccess() {
        Car car = new DontMovableCar("stop");

        assertThat(car.canMove()).isEqualTo(false);
    }

    @ParameterizedTest(name = "잘못된 자동차 이름의 글자수를 입력하면 예외가 발생한다")
    @EmptySource
    @ValueSource(strings = {"123456"})
    void 잘못된_자동차_이름의_글자수를_입력하면_예외가_발생한다(final String inputValue) {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Car(inputValue)
        );
    }

    @ParameterizedTest(name = "올바른 글자수의 자동차 이름이 입력되면 정상 생성된다")
    @ValueSource(strings = {"1", "12345"})
    void 올바른_글자수의_자동차_이름이_입력되면_정상_생성된다(final String inputValue) {
        assertThatNoException().isThrownBy(
                () -> new Car(inputValue)
        );
    }

    @Test
    @DisplayName("같은 거리인지 반환한다")
    void 같은_거리인지_판단한다() {
        Car car = new Car("도기");
        assertThat(car.isSameDistance(0)).isTrue();

        car.move();
        assertThat(car.isSameDistance(1)).isTrue();
    }

     private static class MovableCar extends Car {

         public MovableCar(final String name) {
             super(name);
         }

         @Override
         public boolean canMove() {
             return true;
         }
     }

     private static class DontMovableCar extends Car {

         public DontMovableCar(final String name) {
             super(name);
         }

         @Override
         public boolean canMove() {
             return false;
         }
     }
}
