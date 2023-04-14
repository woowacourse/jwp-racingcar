package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RaceTest {

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
