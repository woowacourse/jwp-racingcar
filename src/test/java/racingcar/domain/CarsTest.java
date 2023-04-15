package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class CarsTest {

    @Test
    @DisplayName("자동차 이름이 없어서 빈 리스트가 넘어오면 오류가 발생한다.")
    void carsGenerateTest() {
        //when && then
        assertThatThrownBy(() -> new Cars(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 자동차 대수는 1이상이어야 합니다.");
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
