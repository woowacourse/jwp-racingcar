package racingcar.domain.race;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.Car;
import racingcar.domain.car.Position;

class RacingCarsTest {

    private RacingCars racingCars;

    @BeforeEach
    void setUp() {
        racingCars = new RacingCars(List.of("브리", "로지", "바론"));
    }

    @DisplayName("중복되는 차 이름은 예외가 발생한다")
    @Test
    void racingCarDuplicateFail() {
        assertThatThrownBy(() -> new RacingCars(List.of("바론", "바론")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("모든 차를 이동시킬 수 있다.")
    @Test
    void moveAllCars() {
        //given
        //when
        racingCars.moveCars(new TestNumberPicker());
        //then
        assertThat(getAllCarsPosition(racingCars)).containsExactly(1, 1, 0);
    }

    private List<Integer> getAllCarsPosition(RacingCars racingCars) {
        return racingCars.getCars()
                .stream()
                .map(Car::getPosition)
                .collect(Collectors.toList());
    }

    @DisplayName("모든 차들 중 가장 멀리 있는 위치를 찾을 수 있다.")
    @Test
    void findBestPositionInAllCars() {
        //given
        racingCars.moveCars(new TestNumberPicker());
        //when
        Position bestPosition = racingCars.findBestPosition();
        //then
        assertThat(bestPosition).isEqualTo(new Position(1));
    }

    @DisplayName("같은 위치에 있는 차를 찾을 수 있다.")
    @Test
    void findAllCarsInSamePosition() {
        //given
        racingCars.moveCars(new TestNumberPicker());
        //when
        List<Car> carsInSamePosition = racingCars.findCarsInSamePosition(new Position(0));
        //then
        assertThat(carsInSamePosition).hasSize(1);
    }

}
