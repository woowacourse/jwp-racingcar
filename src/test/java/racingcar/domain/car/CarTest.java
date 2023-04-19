package racingcar.domain.car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CarTest {
    @Test
    void 무브가_확정된_경우_Car의_Position이_1_증가한다() {
        // given
        Car car = new Car(new Name("Abel"));
        
        // when
        Car movedCar = car.move(() -> true);
        movedCar = movedCar.move(() -> true);
        movedCar = movedCar.move(() -> true);
        
        // then
        assertThat(movedCar.getPosition()).isEqualTo(new Position(3));
    }
}
