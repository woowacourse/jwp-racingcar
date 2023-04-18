package racingcar.domain.car;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("위치를 더할 수 있다.")
    @Test
    void addPosition() {
        //given
        Position position1 = new Position(1);
        Position position2 = new Position(2);
        Position result = new Position(3);

        //when
        Position calculatedResult = position1.add(position2);

        //then
        assertThat(calculatedResult).isEqualTo(result);
    }
}
