package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.mock.MockNumberGenerator;

class CarsTest {

    private Cars testCars;
    private MockNumberGenerator numberGenerator;

    @BeforeEach
    void init() {
        final List<Car> testCarNames = List.of(Car.create("pobi"), Car.create("crong"),
            Car.create("honux"));
        numberGenerator = new MockNumberGenerator();
        testCars = new Cars(testCarNames, numberGenerator);
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi,crong,honux"})
    @DisplayName("정상적인 자동차 이름이 들어오면 예외가 발생하지 않는다.")
    void givenNormalCarNames_thenSuccess(final String names) {
        final List<Car> cars = Arrays.stream(names.split(","))
            .map(Car::create)
            .collect(Collectors.toUnmodifiableList());
        // when & then
        assertThatCode(() -> new Cars(cars, numberGenerator))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi,pobi", "pobi, pobi"})
    @DisplayName("자동차 이름에 중복값이 들어오면 예외가 발생한다.")
    void givenDuplicateCarNames_thenFail(final String names) {
        final List<Car> cars = Arrays.stream(names.split(","))
            .map(Car::create)
            .collect(Collectors.toUnmodifiableList());
        assertThatThrownBy(() -> new Cars(cars, numberGenerator))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("중복된 값을 입력할 수 없습니다.");
    }
}
