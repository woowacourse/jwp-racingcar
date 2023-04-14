package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class CarsTest {

    @Test
    @DisplayName("단독 우승 테스트")
    void getWinner() {
        Car success = new TestCarSuccess("Success");
        Car fail = new TestCarFail("Fail");
        Cars cars = new Cars(Arrays.asList(success, fail));
        cars.moveAll();
        assertThat(cars.getWinner()).isEqualTo(List.of(success));
    }

    @Test
    @DisplayName("공동 우승 테스트")
    void getWinners() {
        Car success1 = new TestCarSuccess("Success1");
        Car success2 = new TestCarSuccess("Success2");
        Cars cars = new Cars(Arrays.asList(success1, success2));
        cars.moveAll();
        assertThat(cars.getWinner()).isEqualTo(List.of(success1, success2));
    }

    @Test
    @DisplayName("두 개 미만의 자동차가 생성되면 예외를 발생한다")
    void 두_개_미만의_자동차가_생성되면_예외를_발생한다() {
        Car car1 = new TestCarSuccess("1");

        assertThatIllegalArgumentException().isThrownBy(
                () ->  new Cars(List.of(car1))
        );
    }

    @Test
    @DisplayName("두 개 이상의 자동차가 생성되면 객체를 정상 생성한다")
    void 두_개_이상의_자동차가_생성되면_객체를_정상_생성한다() {
        Car car1 = new TestCarSuccess("1");
        Car car2 = new TestCarSuccess("2");

        assertThatNoException().isThrownBy(
                () ->  new Cars(List.of(car1, car2))
        );
    }

    @Test
    @DisplayName("자동차 이름이 중복되면 예외를 발생한다")
    void 자동차_이름이_중복되면_예외를_발생한다() {
        Car car1 = new TestCarSuccess("name");
        Car car2 = new TestCarSuccess("name");

        assertThatIllegalArgumentException().isThrownBy(
                () -> new Cars(List.of(car1, car2))
        );
    }

    @Test
    @DisplayName("자동차 이름이 중복되지 않으면 객체를 정상 생성한다")
    void 자동차_이름이_중복되지_않으면_객체를_정상_생성한다() {
        Car car1 = new TestCarSuccess("1");
        Car car2 = new TestCarSuccess("2");

        assertThatNoException().isThrownBy(
                () ->  new Cars(List.of(car1, car2))
        );
    }

    private static class TestCarSuccess extends Car {

        public TestCarSuccess(String name) {
            super(name);
        }

        @Override
        public boolean isMove() {
            return true;
        }
    }

    private static class TestCarFail extends Car {

        public TestCarFail(String name) {
            super(name);
        }

        @Override
        public boolean isMove() {
            return false;
        }
    }
}
