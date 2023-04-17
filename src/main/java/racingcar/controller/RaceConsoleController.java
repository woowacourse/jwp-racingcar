package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.Race;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RaceConsoleController {

    private final NumberGenerator numberGenerator;

    public RaceConsoleController(final NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public void race() {
        final Cars cars = createCars();
        final Race race = createRace();
        race.run(cars);
        printRaceResult(cars);
    }

    private Cars createCars() {
        return InputView.getUserInput(() -> {
            final List<String> carNames = InputView.getCarNames();
            return Cars.create(carNames, numberGenerator);
        });
    }

    private static Race createRace() {
        return InputView.getUserInput(() -> {
            final int tryCount = InputView.getTryCount();
            return new Race(tryCount);
        });
    }

    private void printRaceResult(final Cars cars) {
        final List<String> winners = cars.getWinnerCarNames();
        OutputView.printWinnersResult(winners);
        cars.getCars()
                .forEach(car -> OutputView.printCarStatus(car.getName(), car.getPosition()));
    }
}
