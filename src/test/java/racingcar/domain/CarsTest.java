package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CarsTest {

    @DisplayName("Car 등록 테스트")
    @ParameterizedTest(name = "carNames = {0}, expectedSize = {1}")
    @MethodSource("carNamesDummy")
    void addCarTest(List<String> carNames, int expectedSize) {
        final Cars cars = Cars.from(carNames);
        assertThat(cars.getCars()).hasSize(expectedSize);
    }

    @DisplayName("자동차 경기 우승자들 이름 조회 테스트")
    @Test
    void findWinnerNamesTest() {
        final Cars cars = Cars.from(List.of("망고", "현구막"));
        cars.getCars().get(0).move(9);
        List<String> winnerNames = cars.findWinnerNames();
        assertThat(winnerNames.get(0)).isEqualTo("망고");
    }

    static Stream<Arguments> carNamesDummy() {
        return Stream.of(
                Arguments.arguments(List.of("aaaa", "bbbb"), 2),
                Arguments.arguments(List.of("가나다라", "가나다라마", "가나다"), 3),
                Arguments.arguments(List.of("1234", "123", "12", "1234"), 4)
        );
    }
}
