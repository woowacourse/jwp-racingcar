package racingcar.game.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.car.model.Car;
import racingcar.car.model.NumberGenerator;

public final class RacingCarGame implements Game {
    
    private final NumberGenerator numberGenerator;
    private final List<Car> cars;
    
    private RacingCarGame(final NumberGenerator numberGenerator, final List<Car> cars) {
        this.numberGenerator = numberGenerator;
        this.cars = cars;
    }
    
    public static RacingCarGame create(final NumberGenerator numberGenerator, final List<Car> cars) {
        return new RacingCarGame(numberGenerator, cars);
    }
    
    private Car findWinner(final List<Car> sortedCars) {
        sortedCars.sort(Comparator.reverseOrder());
        return sortedCars.get(0);
    }
    
    private List<Car> findWinners(final List<Car> sortedCars, final Car winner) {
        return sortedCars.stream().filter(car -> car.isSamePositionTo(winner))
                .collect(Collectors.toList());
    }
    
    @Override
    public Game moveCars() {
        final List<Car> movedCars = new ArrayList<>();
        this.cars.forEach(car -> {
            final int fuel = this.numberGenerator.generate();
            movedCars.add(car.move(fuel));
        });
        return new RacingCarGame(this.numberGenerator, movedCars);
    }
    
    @Override
    public Result calculateResult() {
        final List<Car> sortedCars = new ArrayList<>(this.cars);
        final Car winner = this.findWinner(sortedCars);
        final List<Car> winners = this.findWinners(sortedCars, winner);
        return GameResult.create(winners);
    }
    
}
