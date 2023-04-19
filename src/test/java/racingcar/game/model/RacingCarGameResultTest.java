package racingcar.game.model;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.car.interfaces.Car;
import racingcar.car.model.RacingCar;

class RacingCarGameResultTest {
    
    @Test
    @DisplayName("RacingCarGameResult 객체 생성 테스트")
    void create() {
        //given
        final Car echo = RacingCar.create("echo", 1);
        final Car io = RacingCar.create("io", 0);
        final List<Car> cars = List.of(echo, io);
        
        //when
        final RacingCarGameResult gameResult = RacingCarGameResult.create(List.of(echo), cars);
        
        //then
        Assertions.assertEquals(2, gameResult.getCars().size());
        Assertions.assertEquals("echo", gameResult.getWinners());
    }
    
}