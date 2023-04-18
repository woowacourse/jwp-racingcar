package racingcar.domain.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.race.RacingCars;
import racingcar.domain.race.TestNumberPicker;

class WinnersTest {

    private RacingCars racingCars;

    @BeforeEach
    void setUp() {
        racingCars = new RacingCars(List.of("브리", "로지", "바론"));
        racingCars.moveCars(new TestNumberPicker());
    }

    @DisplayName("모든 차가 똑같이 이동했을 때, 모두 우승자로 계산된다.")
    @Test
    void allCarWinnerWhenSamePosition() {
        //given
        //when
        Winners winners = Winners.from(racingCars);
        //then
        assertThat(winners.getCars()).hasSize(2);
        assertThat(getWinnerCarNames(winners.getCars())).containsExactly("브리", "로지");
    }

    private List<String> getWinnerCarNames(List<Car> cars) {
        return cars.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    @DisplayName("차 이름이 우승자 목록에 포함되어 있으면 우승자임을 알려준다.")
    @Test
    void whenCarIsWinnerReturnsTrue() {
        //given
        Winners winners = Winners.from(racingCars);
        Car winCar1 = new Car("브리");
        Car winCar2 = new Car("로지");
        Car loseCar = new Car("바론");
        //when
        //then
        assertAll(
                () -> assertThat(winners.isWinner(winCar1)).isTrue(),
                () -> assertThat(winners.isWinner(winCar2)).isTrue(),
                () -> assertThat(winners.isWinner(loseCar)).isFalse()
        );
    }

}