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
    
    @Test
    void 자신보다_Position이_더_작은_Car와_비교할_경우_1을_반환한다() {
        // given
        Car car = new Car(new Name("Abel"));
        
        // when
        final int compareNumber = car.move(() -> true).compareTo(new Car(new Name("Split")));
        
        // then
        assertThat(compareNumber).isOne();
    }
    
    @Test
    void 자신보다_Position이_더_높은_Car와_비교할_경우_음수를_반환한다() {
        // given
        Car car = new Car(new Name("Abel"));
        
        // when
        final int compareNumber = car.compareTo(new Car(new Name("Split")).move(() -> true));
        
        // then
        assertThat(compareNumber).isEqualTo(-1);
    }
    
    @Test
    void 자신과_Position이_같은_Car와_비교할_경우_0을_반환한다() {
        // given
        Car car = new Car(new Name("Abel"));
        
        // when
        final int compareNumber = car.compareTo(new Car(new Name("Split")));
        
        // then
        assertThat(compareNumber).isZero();
    }
    
    @Test
    void 자신과_Position이_같은_경우_true_를_반환한다() {
        // given
        Car car = new Car(new Name("Abel"));
        
        // when
        final boolean isSamePosition = car.isSamePositionTo(new Car(new Name("Split")));
        
        // then
        assertThat(isSamePosition).isTrue();
    }
    
    @Test
    void 자신과_Position이_다른_경우_false_를_반환한다() {
        // given
        Car car = new Car(new Name("Abel"));
        
        // when
        final boolean isSamePosition = car.isSamePositionTo(new Car(new Name("Split")).move(() -> true));
        
        // then
        assertThat(isSamePosition).isFalse();
    }
}
