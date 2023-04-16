package racingcar.domain.race;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.car.Car;

public class RacingGame {
    private final RacingCars racingCars;

    public RacingGame(List<Car> cars) {
        this.racingCars = new RacingCars(cars);
    }

    public static RacingGame from(List<String> carNames) {
        List<Car> cars = carNames.stream().map(Car::new).collect(Collectors.toList());
        return new RacingGame(cars);
    }

    public void move(int trialCount, NumberGenerator numberGenerator) {
        for (int count = 0; count < trialCount; count++) {
            List<Integer> numbers = numberGenerator.generateNumbers(getRacingCars().size());
            racingCars.moveCars(numbers);
        }
    }

    public List<Car> calculateWinners() {
        return racingCars.filter(car -> car.getPosition() == racingCars.calculateMaxPosition());
    }

    public boolean isWinner(Car car) {
        return calculateWinners().stream()
                .map(Car::getName)
                .anyMatch(name -> name.equals(car.getName()));
    }

    public List<Car> getRacingCars() {
        return racingCars.getCars();
    }

}
