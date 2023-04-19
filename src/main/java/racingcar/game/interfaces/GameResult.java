package racingcar.game.interfaces;

import java.util.List;
import racingcar.car.interfaces.Car;

public interface GameResult {
    
    long getCreatedAt();
    
    List<Car> getCars();
    
    String getWinners();
}
