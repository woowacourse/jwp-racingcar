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

    @DisplayName("자동차 이동 테스트 실패")
    @Test
    void isMoveFail() {
        Vehicle car = new TestCarFail("falseTest");

        assertThat(car.isMove()).isEqualTo(false);
    }

    @DisplayName("자동차 이동 테스트 성공")
    @Test
    void isMoveSuccess() {
        Vehicle car = new TestCarSuccess("trueTest");

        assertThat(car.isMove()).isEqualTo(true);
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

    private static class TestCarSuccess extends Vehicle {

        public TestCarSuccess(String name) {
            super(name, 0);
        }

        @Override
        public boolean isMove() {
            return true;
        }
    }

    private static class TestCarFail extends Vehicle {

        public TestCarFail(String name) {
            super(name, 0);
        }
    }
}
