package racingcar.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarsTest {
    Cars cars = Cars.from(List.of("토리", "망고"));

    @DisplayName("우승자의 이름을 Return")
    @Test
    void Should_Winner_When_getWinnerMethod() {
        assertThat(cars.getWinner()).isEqualTo(List.of("망고"));
    }
}
