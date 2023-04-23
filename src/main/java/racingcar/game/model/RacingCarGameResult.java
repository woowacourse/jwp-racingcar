package racingcar.game.model;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.car.model.Car;

public final class RacingCarGameResult implements GameResult {
    
    private final String winners;
    private final List<Car> cars;
    
    private final long createdAt;
    
    private RacingCarGameResult(final String winners, final List<Car> cars) {
        this.winners = winners;
        this.cars = cars;
        this.createdAt = System.currentTimeMillis();
    }
    
    public RacingCarGameResult(final String winners, final List<Car> cars, final long createdAt) {
        this.winners = winners;
        this.cars = cars;
        this.createdAt = createdAt;
    }
    
    
    public static RacingCarGameResult create(final List<Car> winners, final List<Car> cars) {
        final String winnerNames = winners.stream()
                .map(car -> car.getName().getValue())
                .collect(Collectors.joining(","));
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
        return this.winners;
    }
}
