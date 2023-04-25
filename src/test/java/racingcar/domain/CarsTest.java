package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsTest {

    @DisplayName("자동차 경기 우승자들 이름 조회 테스트")
    @Test
    void findWinnerNamesTest() {
        //given
        Cars cars = Cars.from(List.of("car1", "car2", "car3"));
        List<Car> allCars = cars.getCars();
        Car car1 = allCars.get(0);
        car1.move(9);
        //when
        List<Car> winner = cars.findWinner();
        //then
        assertThat(winner.get(0).getName()).isEqualTo(car1.getName());
    }

    @DisplayName("이름 목록으로 Cars 생성")
    @MethodSource("carNamesDummy")
    @ParameterizedTest
    void from(final List<String> names) {
        assertThat(Cars.from(names)).isNotNull();
    }

    @DisplayName("한명으로는 생성 불가")
    @Test
    void fromExceptionWithOneName() {
        assertThatThrownBy(() -> Cars.from(List.of("한명"))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 이름으로 생성 불가")
    @Test
    void fromExceptionWithDuplicatedName() {
        assertThatThrownBy(() -> Cars.from(List.of("중복", "중복"))).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> carNamesDummy() {
        return Stream.of(
                Arguments.arguments(List.of("aaaa", "bbbb")),
                Arguments.arguments(List.of("가나다라", "가나다라마", "가나다")),
                Arguments.arguments(List.of("1234", "123", "12", "12345"))
        );
    }
}
