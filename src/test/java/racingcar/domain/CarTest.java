package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CarTest {

    private Car testCar;

    @BeforeEach
    void init() {
        testCar = new Car(CarName.create("pobi"), CarPosition.init());
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 9})
    @DisplayName("랜덤값이 4 이상이면 자동차가 전진한다.")
    void move(final int randomNumber) {
        // when
        testCar.move(randomNumber);

        // then
        assertThat(testCar.getCarPosition())
                .isEqualTo(CarPosition.create(2));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 3})
    @DisplayName("랜덤값이 3 이하면 자동차가 정지한다.")
    void move_stop(final int randomNumber) {
        // when
        testCar.move(randomNumber);

        // then
        assertThat(testCar.getCarPosition())
                .isEqualTo(CarPosition.create(1));
    }
}
