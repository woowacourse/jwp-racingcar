package racingcar.game.model;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.car.model.Car;
import racingcar.car.model.Name;

public final class GameResult implements Result {
    
    private final List<Name> winners;
    
    private GameResult(final List<Name> winners) {
        this.winners = winners;
    }
    
    public static GameResult create(final List<Car> cars) {
        final List<Name> winners = cars.stream().map(Car::getName).collect(Collectors.toList());
        return new GameResult(winners);
    }
    
    @Override
    public List<Name> getWinners() {
        return this.winners;
    }
}
