package racingcar.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacingGame {
    private final List<RacingCar> racingCars;
    private final NumberGenerator numberGenerator;

    public RacingGame(List<RacingCar> racingCars, NumberGenerator numberGenerator) {
        this.racingCars = racingCars;
        this.numberGenerator = numberGenerator;
    }

    public void runRound() {
        racingCars.forEach(racingCar -> racingCar.advance(numberGenerator.getNumber()));
    }

    public Map<RacingCar, GameResult> calculateResult() {
        Map<RacingCar, GameResult> results = new LinkedHashMap<>();
        racingCars.forEach(racingCar -> results.put(racingCar, GameResult.LOSE));
        List<String> winningCarsName = findWinningCarsName();
        racingCars.stream().filter(racingCar -> winningCarsName.contains(racingCar.getName()))
                .forEach(racingCar -> results.put(racingCar, GameResult.WIN));
        return results;
    }

    public List<String> findWinningCarsName() {
        List<RacingCar> sortedCars = racingCars.stream().sorted().collect(Collectors.toUnmodifiableList());
        RacingCar firstCar = sortedCars.get(0);
        return sortedCars.stream()
                .filter(car -> car.getPosition() == firstCar.getPosition())
                .map(RacingCar::getName)
                .collect(Collectors.toList());
    }

    public List<RacingCar> getStatus() {
        return List.copyOf(racingCars);
    }
}
