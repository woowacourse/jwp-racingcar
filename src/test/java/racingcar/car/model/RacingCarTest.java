package racingcar.car.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.car.interfaces.Car;

class RacingCarTest {
    
    @Test
    @DisplayName("RacingCar 객체 생성 테스트")
    void create() {
        final String name = "echo";
        final int position = 0;
        final Car car = RacingCar.create(name, position);
        assertEquals(name, car.getName().getValue());
        assertEquals(position, car.getPosition().getValue());
    }
    
    @Test
    @DisplayName("RacingCar 객체 생성 예외 테스트")
    void createException() {
        final String name = "echo";
        final int position = -1;
        assertThrows(IllegalArgumentException.class, () -> RacingCar.create(name, position));
    }
    
    @Test
    @DisplayName("RacingCar 객체 이동 테스트")
    void move() {
        final String name = "echo";
        final int position = 0;
        final Car car = RacingCar.create(name, position);
        final Car movedCar = car.move(4);
        assertEquals(name, movedCar.getName().getValue());
        assertEquals(position + 1, movedCar.getPosition().getValue());
    }
    
    @Test
    @DisplayName("RacingCar 위치 비교 테스트")
    void compare() {
        final String name = "echo";
        final int position = 0;
        final Car car = RacingCar.create(name, position);
        final Car compareCar = RacingCar.create(name, position + 1);
        assertEquals(-1, car.compareTo(compareCar));
    }
}