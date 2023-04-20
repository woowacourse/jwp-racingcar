package racingcar.domain.car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PositionTest {
    @Test
    void 포지션이_1_증가한다() {
        // given
        Position position = new Position(3);
        
        // when
        Position increasedPosition = position.increase();
        
        // then
        assertThat(increasedPosition).isEqualTo(new Position(4));
    }
    
    @Test
    void 자신보다_더_작은_Position과_비교할_경우_1을_반환한다() {
        // given
        final Position position = new Position(2);
        
        // when
        final int compareNumber = position.compareTo(new Position(1));
        
        // then
        assertThat(compareNumber).isOne();
    }
    
    @Test
    void 자신보다_더_높은_Position과_비교할_경우_음수를_반환한다() {
        // given
        final Position position = new Position(2);
        
        // when
        final int compareNumber = position.compareTo(new Position(3));
        
        // then
        assertThat(compareNumber).isEqualTo(-1);
    }
    
    @Test
    void 자신과_같은_Position과_비교할_경우_0을_반환한다() {
        // given
        final Position position = new Position(2);
        
        // when
        final int compareNumber = position.compareTo(new Position(2));
        
        // then
        assertThat(compareNumber).isZero();
    }
}
