package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.config.FixedMovingStrategy;

class CarsTest {

    Cars cars;

    FixedMovingStrategy fixedMovingStrategy = new FixedMovingStrategy();

    @BeforeEach
    void setUp() {
        cars = new Cars(List.of(new Car("joy"), new Car("pobi")));
        cars.moveCars(fixedMovingStrategy);
    }

    @Test
    void getTotalStatus() {
        assertEquals(
            cars.getTotalStatus(),
            Map.of("joy", 1, "pobi", 1));
    }

    @Test
    void findWinnersTest() {
        List<String> actual = cars.findWinners().stream()
                .map(Car::getName)
                .collect(Collectors.toList());

        assertThat(actual).containsExactly("joy", "pobi");
    }
}
