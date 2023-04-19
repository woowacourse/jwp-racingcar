package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.strategy.FixedMovingStrategy;

class CarsTest {

    Cars cars;

    FixedMovingStrategy fixedMovingStrategy = new FixedMovingStrategy();

    @BeforeEach
    void setUp() {
        cars = new Cars(List.of(new Car("joy"), new Car("pobi")));
        cars.move(fixedMovingStrategy);
    }

    @Test
    void findWinnersTest() {
        List<String> actual = cars.findWinners().stream()
                .map(Car::name)
                .collect(Collectors.toList());

        assertThat(actual).containsExactly("joy", "pobi");
    }
}
