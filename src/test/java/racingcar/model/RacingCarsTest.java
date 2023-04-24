package racingcar.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

class RacingCarsTest {

    private static RacingCars racingCars;

    @BeforeEach
    void setUp() {
        final Car car1 = new Car("io", 0);
        final Car car2 = new Car("oi", 0);

        racingCars = new RacingCars(List.of(car1, car2));
    }

    @Test
    @DisplayName("")
    void tryOneTimeTest() {
        final TestCarNumberGenerator testCarNumberGenerator = new TestCarNumberGenerator(newArrayList(9, 1, 1, 1));

        racingCars.tryOneTime(testCarNumberGenerator);

        assertThat(racingCars.getCars().get(0).getPosition()).isEqualTo(1);
        assertThat(racingCars.getCars().get(1).getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("")
    void getWinnersTest() {
        final TestCarNumberGenerator testCarNumberGenerator = new TestCarNumberGenerator(newArrayList(9, 1, 1, 1));

        racingCars.tryOneTime(testCarNumberGenerator);
        final List<Car> winners = racingCars.getWinners();

        assertThat(winners.size()).isEqualTo(1);
        assertThat(winners.get(0).getName()).isEqualTo("io");
    }
}
