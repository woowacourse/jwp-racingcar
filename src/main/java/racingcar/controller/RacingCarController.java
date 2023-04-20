package racingcar.controller;

import racingcar.domain.*;
import racingcar.dto.CarStatusDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class RacingCarController {
    private static final int MINIMUM_RANDOM_NUMBER = 0;
    private static final int MAXIMUM_RANDOM_NUMBER = 9;

    private final NumberGenerator numberGenerator;

    public RacingCarController() {
        this.numberGenerator = new RandomNumberGenerator(MINIMUM_RANDOM_NUMBER, MAXIMUM_RANDOM_NUMBER);
    }

    public void run() {
        final Cars cars = initCars();
        final Lap lap = initTryCount();
        OutputView.printResultMessage();
        race(cars, lap, numberGenerator);
        prizeWinner(cars);
        showFinalStatus(cars);
    }

    private Cars initCars() {
        try {
            final String input = InputView.inputCarNames();
            final List<String> carNames = splitCarNames(input);
            return new Cars(carNames);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return initCars();
        }
    }

    private List<String> splitCarNames(final String input) {
        return List.of(input.split(","));
    }

    private Lap initTryCount() {
        try {
            final int tries = InputView.inputTries();
            return new Lap(tries);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return initTryCount();
        }
    }

    private void race(final Cars cars, final Lap lap, final NumberGenerator numberGenerator) {
        while (!lap.isFinish()) {
            cars.moveCars(numberGenerator);
            lap.reduce();
        }
    }

    private void prizeWinner(final Cars cars) {
        final WinnerMaker winnerMaker = new WinnerMaker();
        final List<String> winners = winnerMaker.getWinnerCarsName(cars.getLatestResult());
        OutputView.printFinalResult(winners);
    }

    private void showFinalStatus(final Cars cars) {
        final List<Car> latestResult = cars.getLatestResult();
        final List<CarStatusDto> carStatusDtos = mapCarsToCarStatuses(latestResult);
        OutputView.printCarStatus(carStatusDtos);
    }

    private List<CarStatusDto> mapCarsToCarStatuses(final List<Car> cars) {
        return cars.stream()
                .map(car -> new CarStatusDto(car.getCarName(), car.getCurrentPosition()))
                .collect(Collectors.toUnmodifiableList());
    }
}
