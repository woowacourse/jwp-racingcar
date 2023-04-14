package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.mock.MockNumberGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CarsTest {

    private Cars testCars;
    private MockNumberGenerator numberGenerator;

    @BeforeEach
    void init() {
        final List<Car> cars = List.of(Car.create(CarName.create("pobi"), CarPosition.init()),
                Car.create(CarName.create("crong"), CarPosition.init()),
                Car.create(CarName.create("honux"), CarPosition.init()));
        numberGenerator = new MockNumberGenerator();
        testCars = new Cars(cars, numberGenerator);
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi,pobi", "pobi, pobi"})
    @DisplayName("자동차 이름에 중복값이 들어오면 예외가 발생한다.")
    void givenDuplicateCarNames_thenFail(final String names) {
        // given
        final List<String> carNames = Arrays.asList(names.split(","));
        final List<Car> cars = carNames.stream().map(name -> Car.create(CarName.create(name), CarPosition.init()))
                .collect(Collectors.toUnmodifiableList());

        // when & then
        assertThatThrownBy(() -> new Cars(cars, numberGenerator))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 값을 입력할 수 없습니다.");
    }
}
