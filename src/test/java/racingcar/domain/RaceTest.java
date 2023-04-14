package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class RaceTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 10000})
    @DisplayName("정상적인 시도 횟수가 들어오면 예외가 발생하지 않는다.")
    void create_success(final int raceCount) {
        assertThatCode(() -> new Race(raceCount))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -100})
    @DisplayName("시도 횟수가 0 이하일 경우 예외가 발생한다.")
    void create_fail_givenZeroRaceCount(final int raceCount) {
        assertThatThrownBy(() -> new Race(raceCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("%d 이상의 값을 입력해 주세요.", 0));
    }

    @Test
    @DisplayName("입력받은 모든 자동차를 규칙에 따라 이동시킨다.")
    void run() {
        // given
        final Race race = new Race(10);
        final Cars cars = new Cars(List.of(new Car(CarName.create("test1"), CarPosition.init()),
                new Car(CarName.create("test2"), CarPosition.init())), () -> 4);

        // when
        final Cars movedCars = race.run(cars);

        // then
        for (Car car : movedCars.getCars()) {
            assertThat(car.getCarPosition()).isEqualTo(CarPosition.create(11));
        }
    }
}
