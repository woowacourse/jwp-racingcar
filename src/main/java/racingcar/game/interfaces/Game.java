package racingcar.game.interfaces;

import java.util.List;
import racingcar.car.interfaces.Car;

public interface Game {
    
    Game moveCars();
    
    Game race(int count);
    
    GameResult calculateResult();
    
    List<Car> getCars();
}
