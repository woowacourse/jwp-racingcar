package racingcar.controller;

import racingcar.domain.Car.*;
import racingcar.dto.CarDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class RacingGameController {

    private static final int RACE_START_POINT = 0;

    public void run() {
        RacingCars racingCars = createCars();
        TryCount tries = getTries();

        OutputView.printResultMessage();
        raceCars(racingCars, tries.getTries());
        showFinalResult(racingCars);
    }

    private RacingCars createCars() {
        try {
            List<String> carNames = InputView.inputCarNames();
            List<Car> cars = CarFactory.generate(carNames, RACE_START_POINT);
            return new RacingCars(cars);

        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return createCars();
        }
    }

    private TryCount getTries() {
        try {
            int tries = InputView.inputTries();
            return new TryCount(tries);

        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return getTries();
        }
    }

    private void raceCars(RacingCars racingCars, int tries) {
        NumberGenerator numberGenerator = new RandomNumberGenerator();

        for (int i = 0; i < tries; i++) {
            racingCars.moveCars(numberGenerator);
            List<CarDto> raceResult = showRaceResult(racingCars);
            OutputView.printRaceResult(raceResult);
        }
    }

    private List<CarDto> showRaceResult(RacingCars racingCars) {
        return racingCars.getCars().stream()
                .map(car -> new CarDto(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    private void showFinalResult(RacingCars racingCars) {
        List<CarDto> raceResult = showRaceResult(racingCars);
        OutputView.printRaceResult(raceResult);

        showWinners(racingCars);
    }

    private void showWinners(RacingCars racingCars) {
        List<String> winnersName = racingCars.pickWinnerCarNames();
        OutputView.printFinalResult(winnersName);
    }
}
