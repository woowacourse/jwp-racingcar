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
        Cars cars = initCars();
        Lap lap = initTryCount();
        OutputView.printResultMessage();
        race(cars, lap, numberGenerator);
        showFinalStatus(cars);
        prizeWinner(cars);
    }

    private Cars initCars() {
        try {
            String input = InputView.inputCarNames();
            List<String> carNames = splitCarNames(input);
            return new Cars(carNames);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return initCars();
        }
    }

    private List<String> splitCarNames(String input) {
        return List.of(input.split(","));
    }

    private Lap initTryCount() {
        try {
            int tries = InputView.inputTries();
            return new Lap(tries);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return initTryCount();
        }
    }

    private void race(Cars cars, Lap lap, NumberGenerator numberGenerator) {
        while (!lap.isFinish()) {
            cars.moveCars(numberGenerator);
            lap.reduce();
        }
    }

    private void showFinalStatus(Cars cars) {
        List<Car> latestResult = cars.getLatestResult();
        List<CarStatusDto> carStatusDtos = mapCarsToCarStatuses(latestResult);
        OutputView.printCarStatus(carStatusDtos);
    }

    private List<CarStatusDto> mapCarsToCarStatuses(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarStatusDto(car.getCarName(), car.getCurrentPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    private void prizeWinner(Cars cars) {
        WinnerMaker winnerMaker = new WinnerMaker();
        List<String> winnersName = winnerMaker.getWinnerCarsName(cars.getLatestResult());
        OutputView.printFinalResult(winnersName);
    }
}
