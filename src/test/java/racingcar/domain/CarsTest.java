package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.common.exception.DuplicateResourceException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CarsTest {

    @ParameterizedTest
    @ValueSource(strings = {"pobi,pobi", "pobi, pobi"})
    @DisplayName("자동차 이름에 중복값이 들어오면 예외가 발생한다.")
    void create(final String names) {
        // given
        final List<String> carNames = Arrays.asList(names.split(","));
        final List<Car> cars = carNames.stream().map(name -> new Car(CarName.create(name), CarPosition.init()))
                .collect(Collectors.toUnmodifiableList());

        // when & then
        assertThatThrownBy(() -> new Cars(cars, () -> 1))
                .isInstanceOf(DuplicateResourceException.class);
    }
}
