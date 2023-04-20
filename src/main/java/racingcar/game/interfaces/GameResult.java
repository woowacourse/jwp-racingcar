package racingcar.game.interfaces;

import java.util.List;
import racingcar.car.interfaces.Car;
import racingcar.game.model.GameEntity;
import racingcar.game.model.RacingCarGameResult;

public interface GameResult {
    
    static GameResult create(final GameEntity gameEntity, final List<Car> cars) {
        final long createdAt = gameEntity.getCreatedAt();
        final String winners = gameEntity.getWinners();
        return new RacingCarGameResult(winners, cars, createdAt);
    }
    
    long getCreatedAt();
    
    List<Car> getCars();
    
    String getWinners();
}
