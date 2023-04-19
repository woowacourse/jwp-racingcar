package racingcar.game.model;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.car.interfaces.Car;
import racingcar.car.interfaces.Name;
import racingcar.game.interfaces.GameResult;

public final class RacingCarGameResult implements GameResult {
    
    private final List<Name> winners;
    private final List<Car> cars;
    
    private final long createdAt = System.currentTimeMillis();
    
    private RacingCarGameResult(final List<Name> winners, final List<Car> cars) {
        this.winners = winners;
        this.cars = cars;
    }
    
    public static RacingCarGameResult create(final List<Car> winners, final List<Car> cars) {
        final List<Name> winnerNames = winners.stream().map(Car::getName).collect(Collectors.toList());
        return new RacingCarGameResult(winnerNames, cars);
    }
    
    @Override
    public long getCreatedAt() {
        return this.createdAt;
    }
    
    @Override
    public List<Car> getCars() {
        return this.cars;
    }
    
    @Override
    public String getWinners() {
        return this.winners.stream().map(Name::getValue).collect(Collectors.joining(","));
    }
}
