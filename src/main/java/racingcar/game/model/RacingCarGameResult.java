package racingcar.game.model;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.car.model.Car;
import racingcar.car.model.Name;

public final class RacingCarGameResult implements GameResult {
    
    private final List<Name> winners;
    
    private RacingCarGameResult(final List<Name> winners) {
        this.winners = winners;
    }
    
    public static RacingCarGameResult create(final List<Car> cars) {
        final List<Name> winners = cars.stream().map(Car::getName).collect(Collectors.toList());
        return new RacingCarGameResult(winners);
    }
    
    @Override
    public List<Name> getWinners() {
        return this.winners;
    }
}
