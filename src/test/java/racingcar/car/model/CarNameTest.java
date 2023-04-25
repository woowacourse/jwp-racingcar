package racingcar.car.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarNameTest {
    
    @Test
    @DisplayName("자동차 이름 생성 테스트")
    void create() {
        final String name = "echo";
        final CarName carName = CarName.create(name);
        assertEquals(name, carName.getValue());
    }
    
    @Test
    @DisplayName("자동차 이름 길이가 5자를 초과할 경우 예외 발생 테스트")
    void createException() {
        final String name = "echo123";
        assertThrows(IllegalArgumentException.class, () -> CarName.create(name));
    }
}