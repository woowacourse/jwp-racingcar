package racingcar.car.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarPositionTest {
    
    @Test
    @DisplayName("자동차 위치 생성 테스트")
    void create() {
        final int position = 0;
        final Position carPosition = CarPosition.create(position);
        assertEquals(position, carPosition.getValue());
    }
    
    @Test
    @DisplayName("자동차 위치가 0보다 작을 경우 예외 발생 테스트")
    void createException() {
        final int position = -1;
        assertThrows(IllegalArgumentException.class, () -> CarPosition.create(position));
    }
    
    @Test
    @DisplayName("자동차 위치 추가 테스트")
    void add() {
        final int position = 0;
        final int addPosition = 1;
        final Position carPosition = CarPosition.create(position);
        final Position addedCarPosition = carPosition.add(addPosition);
        assertEquals(position + addPosition, addedCarPosition.getValue());
    }
    
    @Test
    @DisplayName("자동차 위치 비교 테스트")
    void compare() {
        final int position = 0;
        final int comparePosition = 1;
        final Position carPosition = CarPosition.create(position);
        final Position compareCarPosition = CarPosition.create(comparePosition);
        assertEquals(-1, carPosition.compareTo(compareCarPosition));
    }
}