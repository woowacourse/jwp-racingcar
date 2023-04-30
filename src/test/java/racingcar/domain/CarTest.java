package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class CarTest {

    @ParameterizedTest(name = "자동차 생성 성공 테스트 : carName = {0}")
    @MethodSource("carNameDummy")
    void createCarSuccessTest(String carName) {
        assertThat(new Car(carName)).isNotNull();
    }

    @ParameterizedTest(name = "자동차 전진 테스트 : generatedNumber = {0}")
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void moveForwardTest(int generatedNumber) {
        Car car = new Car("이름입니다");
        int initPosition = car.getPosition();
        car.move(generatedNumber);
        assertThat(car.getPosition()).isEqualTo(initPosition + 1);
    }

    @ParameterizedTest(name = "자동차 정지 테스트 : generatedNumber = {0}")
    @ValueSource(ints = {1, 2, 3})
    void moveStopTest(int generatedNumber) {
        Car car = new Car("이름입니다");
        int initPosition = car.getPosition();
        car.move(generatedNumber);
        assertThat(car.getPosition()).isEqualTo(initPosition);
    }

    static Stream<Arguments> carNameDummy() {
        return Stream.of(
                Arguments.arguments("가"),
                Arguments.arguments("가나"),
                Arguments.arguments("가나다"),
                Arguments.arguments("가나다라"),
                Arguments.arguments("가나다라마")
        );
    }
}
