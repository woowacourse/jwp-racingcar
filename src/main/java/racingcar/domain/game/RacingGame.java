package racingcar.domain.game;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.domain.cars.RacingCar;
import racingcar.domain.cars.RacingCars;

public class RacingGame {
    private final RacingCars racingCars;

    public RacingGame(List<RacingCar> racingCars) {
        this.racingCars = new RacingCars(racingCars);
    }

    public static RacingGame from(List<String> carNames) {
        List<RacingCar> racingCars = carNames.stream().map(RacingCar::new).collect(Collectors.toList());
        return new RacingGame(racingCars);
    }

    public void play(int trialCount, NumberGenerator numberGenerator) {
        for (int count = 0; count < trialCount; count++) {
            List<Integer> numbers = numberGenerator.generateNumbers(getRacingCars().size());
            racingCars.moveCars(numbers);
        }
    }

    public List<RacingCar> calculateWinners() {
        return racingCars.filter(car -> car.getPosition() == racingCars.calculateMaxPosition());
    }

    public boolean isWinner(RacingCar racingCar) {
        return calculateWinners().stream()
                .map(RacingCar::getName)
                .anyMatch(name -> name.equals(racingCar.getName()));
    }

    public List<RacingCar> getRacingCars() {
        return racingCars.getCars();
    }

}
