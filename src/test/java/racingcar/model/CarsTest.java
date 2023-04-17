package racingcar.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CarsTest {

    @Test
    @DisplayName("단독 우승 테스트")
    void getWinner() {
        Car success = new TestCar("true", List.of(5, 7));
        Car fail = new TestCar("false", List.of(1, 3));
        Cars cars = new Cars(Arrays.asList(success, fail));
        cars.moveAll();
        assertThat(cars.getWinner()).isEqualTo("true");
    }

    @Test
    @DisplayName("공동 우승 테스트")
    void getWinners() {
        Car success1 = new TestCar("true1", List.of(5, 7));
        Car success2 = new TestCar("true2", List.of(4, 8));
        Cars cars = new Cars(Arrays.asList(success1, success2));
        cars.moveAll();
        assertThat(cars.getWinner()).isEqualTo("true1,true2");
    }

    @DisplayName("자동차 이름 중복 예외 테스트")
    @Test
    void 자동차_이름_중복_예외_테스트() {
        List<Car> cars = Stream.of("a", "a", "b").
                map(Car::new)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> new Cars(cars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차명은 중복되어선 안됩니다.");
    }
}
