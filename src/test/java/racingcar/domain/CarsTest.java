package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarsTest {



    @Test
    @DisplayName("자동차가 2대 미만이면 예외가 발생한다.")
    void create_fail_by_size() {
        //when && then
        assertThatThrownBy(() -> new Cars(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 대수는 2 이상이어야 합니다.");
    }

    @Test
    @DisplayName("중복된 자동차 이름이 존재한다면 예외가 발생한다.")
    void create_fail_duplicate_name() {
        //when && then
        assertThatThrownBy(() -> new Cars(List.of(new Car("car"), new Car("car"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 자동차 이름이 존재합니다.");
    }

    @Test
    @DisplayName("우승한 자동차를 제공한다.")
    void decideWinnerTest() {
        //given
        Cars cars = new Cars(List.of(new Car("A"), new Car("B"), new Car("C")));
        final TestNumberGenerator numberGenerator = new TestNumberGenerator(List.of(3, 4, 5));

        //when
        cars.move(numberGenerator);
        List<Car> actual = cars.findAllWinner();

        //then
        assertThat(actual.size()).isEqualTo(2);
    }
}
