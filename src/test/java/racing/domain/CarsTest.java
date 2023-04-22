package racing.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racing.domain.fixture.CarFixtureFactory;
import racing.domain.utils.RacingCarNumberGenerator;
import racing.domain.utils.ScheduledNumberGenerator;

class CarsTest {

    private static final int STOP_NUMBER = 1;
    private static final int MOVE_NUMBER = 9;

    @DisplayName("이동 전략에 따라 자동차들이 전진한다.")
    @Test
    void moveCarsWithTest() {
        Car carA = CarFixtureFactory.getCarWithName("carA");
        Car carB = CarFixtureFactory.getCarWithName("carB");
        Cars cars = new Cars(List.of(carA, carB));
        RacingCarNumberGenerator scheduledNumberGenerator =
                new ScheduledNumberGenerator(STOP_NUMBER, MOVE_NUMBER);

        cars.moveCarsWith(scheduledNumberGenerator);

        assertThat(carA.getStep()).isEqualTo(0);
        assertThat(carB.getStep()).isEqualTo(1);
    }

    @DisplayName("제일 멀리 나간 자동차들을 찾을 수 있다.")
    @Test
    void filterMaxStepCarsTest() {
        Car bebe0 = CarFixtureFactory.getCarWithNameAndStep("carA", 1);
        Car bebe1 = CarFixtureFactory.getCarWithNameAndStep("carB", 2);
        Car bebe2 = CarFixtureFactory.getCarWithNameAndStep("carC", 2);
        Cars cars = new Cars(List.of(bebe0, bebe1, bebe2));

        Cars frontCars = cars.filterMaxStepCars();

        assertThat(frontCars.getCars()).contains(bebe1, bebe2);
    }
}
