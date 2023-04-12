package racingcar.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racingcar.dto.RacingCarDto;

public class RacingGame {
    private static final Boolean ALL_CARS_ADVANCE = true;

    private final List<RacingCar> racingCars = new ArrayList<>();
    private final AdvanceJudgement advanceJudgement;

    public RacingGame(AdvanceJudgement advanceJudgement) {
        this.advanceJudgement = advanceJudgement;
    }

    public void addRacingCar(RacingCar racingCar) {
        racingCars.add(racingCar);
    }

    public void runRound() {
        racingCars.forEach(racingCar -> racingCar.advance(advanceJudgement.isAdvancePossible()));
    }

    public List<RacingCarDto> getStartStatus() {
        List<RacingCarDto> roundResult = new ArrayList<>();
        racingCars.forEach(racingCar -> {
            racingCar.advance(ALL_CARS_ADVANCE);
            roundResult.add(RacingCarDto.from(racingCar));
        });
        return roundResult;
    }

    public Map<RacingCar, GameResult> getResult() {
        Map<RacingCar, GameResult> results = new LinkedHashMap<>();
        racingCars.forEach(racingCar -> results.put(racingCar, GameResult.LOSE));
        List<String> winningCarsName = getWinningCarsName();
        racingCars.stream().filter(racingCar -> winningCarsName.contains(racingCar.getName()))
                .forEach(racingCar -> results.put(racingCar, GameResult.WIN));
        return results;
    }

    public List<RacingCarDto> getStatus() {
        return racingCars.stream().map(RacingCarDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<String> getWinningCarsName() {
        List<RacingCar> sortedCars = racingCars.stream().sorted().collect(Collectors.toUnmodifiableList());
        RacingCar firstCar = sortedCars.get(0);
        return sortedCars.stream()
                .filter(car -> car.getPosition().equals(firstCar.getPosition()))
                .map(RacingCar::getName)
                .collect(Collectors.toList());
    }
}
