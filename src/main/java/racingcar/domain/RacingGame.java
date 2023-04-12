package racingcar.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacingGame {
    private final List<RacingCar> racingCars;
    private final AdvanceJudgement advanceJudgement;

    public RacingGame(List<RacingCar> racingCars, AdvanceJudgement advanceJudgement) {
        this.racingCars = racingCars;
        this.advanceJudgement = advanceJudgement;
    }

    public void runRound() {
        racingCars.forEach(racingCar -> racingCar.advance(advanceJudgement.isAdvancePossible()));
    }

    public Map<RacingCar, GameResult> getResult() {
        Map<RacingCar, GameResult> results = new LinkedHashMap<>();
        racingCars.forEach(racingCar -> results.put(racingCar, GameResult.LOSE));
        List<String> winningCarsName = getWinningCarsName();
        racingCars.stream().filter(racingCar -> winningCarsName.contains(racingCar.getName()))
                .forEach(racingCar -> results.put(racingCar, GameResult.WIN));
        return results;
    }

    public List<RacingCar> getStatus() {
        return List.copyOf(racingCars);
    }

    public List<String> getWinningCarsName() {
        List<RacingCar> sortedCars = racingCars.stream().sorted().collect(Collectors.toUnmodifiableList());
        RacingCar firstCar = sortedCars.get(0);
        return sortedCars.stream()
                .filter(car -> car.getPosition() == firstCar.getPosition())
                .map(RacingCar::getName)
                .collect(Collectors.toList());
    }
}
