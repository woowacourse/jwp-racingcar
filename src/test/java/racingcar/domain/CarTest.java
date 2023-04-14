package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {
    private Car car;
    
    @BeforeEach
    void setUp() {
        car = new Car("abel", 0);
    }
    
    @Test
    @DisplayName("지정한 만큼 거리 이동한다.")
    void drive() {
        car.drive(2);
        assertThat(car.getPosition()).isEqualTo(2);
    }
    
    @Test
    @DisplayName("해당 맥스 포지션과 같은지 확인한다.")
    void isWinner() {
        assertThat(car.isWinner(0)).isTrue();
    }
}