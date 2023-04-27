package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarTest {
    private Car car;

    @BeforeEach
    void setUp() {
        car = Car.from("토리");
    }

    @DisplayName("4보다 작은 값이 입력되면, Position 값에 변화가 없다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @ValueSource(ints = {0, 2, 3})
    void Should_UnConvertPosition_When_EngineLessThan4(int engine) {
        car.runForward(engine);

        assertThat(car.getPositionValue()).isEqualTo(0);
    }

    @DisplayName("4 이상인 값이 입력되면, Position 값이 증가한다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @ValueSource(ints = {4, 7, 10})
    void Should_IncreasePosition_When_EngineMoreThan4(int engine) {
        car.runForward(engine);
        
        assertThat(car.getPositionValue()).isEqualTo(1);
    }
}
