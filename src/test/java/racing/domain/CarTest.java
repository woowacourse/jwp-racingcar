package racing.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarTest {

    @ParameterizedTest(name = "기준보다 낮은 숫자가 입력되면 정지한다.")
    @ValueSource(ints = {1, 2, 3})
    void moveForwardUnderStandardTest(int input) {
        Car carA = new Car(new CarName("carA"));

        carA.moveForwardByNumber(input);

        assertThat(carA.getStep()).isZero();
    }

    @ParameterizedTest(name = "기준보다 높은 숫자가 입력되면 전진한다.")
    @ValueSource(ints = {4, 5, 6, 7, 8})
    void moveForwardOverStandardTest(int input) {
        Car carA = new Car(new CarName("carA"));

        carA.moveForwardByNumber(input);

        assertThat(carA.getStep()).isEqualTo(1);
    }
}
