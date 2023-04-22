package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class CarsTest {
    @DisplayName("Cars의 size가 1이상 50이하일 경우 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 49})
    void create(final int namesSize) {
        // given
        final List<String> nameValuesBetween1And50 = IntStream.rangeClosed(0, namesSize)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        // expect
        assertDoesNotThrow(() -> new Cars(nameValuesBetween1And50));
    }

    @DisplayName("Cars의 size가 1일 경우 예외가 발생한다.")
    @Test
    void throwExceptionWhenCarsSizeIsOne() {
        assertThatThrownBy(() -> new Cars(List.of("헤나")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Cars의 size가 50을 초과한 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {51, 52, 53, 54, 55, 100, 1000})
    void throwExceptionWhenCarsSizeIsOver50(final int namesSize) {
        // given
        final List<String> nameValuesOver50 = IntStream.rangeClosed(1, namesSize)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        // expect
        assertThatThrownBy(() -> new Cars(nameValuesOver50))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("자동차 이름이 중복될 경우 예외가 발생한다.")
    @Test
    void throwExceptionWhenCarNameIsDuplicated() {
        assertThatThrownBy(() -> new Cars(List.of("1", "2", "3", "1")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("모든 자동차들이 경주를 진행한다.")
    @Test
    void race() {
        // given
        final Cars cars = new Cars(List.of("헤나", "찰리"));
        final NumberGenerator movableNumberGenerator = new MovableNumberGenerator();

        // when
        cars.race(movableNumberGenerator);
        final List<Position> carPositions = cars.getRacingCars()
                .stream()
                .map(Car::getPosition)
                .collect(Collectors.toList());

        // then
        assertThat(carPositions).containsExactly(new Position(1), new Position(1));
    }
}
