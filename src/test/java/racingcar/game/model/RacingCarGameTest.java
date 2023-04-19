package racingcar.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.car.interfaces.Car;
import racingcar.car.model.RacingCar;
import racingcar.game.interfaces.Game;

class RacingCarGameTest {
    
    @Test
    @DisplayName("RacingCarGame 객체 생성 테스트")
    void create() {
        //given
        final Car echo = RacingCar.create("echo", 0);
        final Car io = RacingCar.create("io", 0);
        final List<Car> cars = List.of(echo, io);
        
        //when
        final Game racingCarGame = RacingCarGame.create(new FixedNumberGenerator(), cars);
        
        //then
        assertEquals(cars, racingCarGame.getCars());
    }
    
    @Test
    @DisplayName("RacingCarGame 객체 moveCars 테스트")
    void moveCars() {
        //given
        final Car echo = RacingCar.create("echo", 0);
        final Car io = RacingCar.create("io", 0);
        final List<Car> cars = List.of(echo, io);
        final Game racingCarGame = RacingCarGame.create(new FixedNumberGenerator(), cars);
        
        //when
        final Game movedRacingCarGame = racingCarGame.moveCars();
        
        //then
        assertEquals(1, movedRacingCarGame.getCars().get(0).getPosition().getValue());
        assertEquals(1, movedRacingCarGame.getCars().get(1).getPosition().getValue());
    }
    
    @Test
    @DisplayName("RacingCarGame 객체 race 테스트")
    void move() {
        //given
        final Car echo = RacingCar.create("echo", 0);
        final Car io = RacingCar.create("io", 0);
        final List<Car> cars = List.of(echo, io);
        final Game racingCarGame = RacingCarGame.create(new FixedNumberGenerator(), cars);
        final int count = 5;
        
        //when
        final Game movedRacingCarGame = racingCarGame.race(5);
        
        //then
        assertEquals(count, movedRacingCarGame.getCars().get(0).getPosition().getValue());
        assertEquals(count, movedRacingCarGame.getCars().get(1).getPosition().getValue());
    }
    
    
}