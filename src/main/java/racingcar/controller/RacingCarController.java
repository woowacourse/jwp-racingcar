package racingcar.controller;

import racingcar.domain.*;
import racingcar.dto.CarStatusDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RacingCarController {

    private final NumberGenerator numberGenerator;

    public RacingCarController() {
        this.numberGenerator = new RandomNumberGenerator();
    }

    public void run() {
        Cars cars = initCars();
        int lap = initTryCount();
        OutputView.printResultMessage();
        race(cars, lap, numberGenerator);
        showFinalStatus(cars);
    }

    private Cars initCars() {
        try {
            String input = InputView.inputCarNames();
            List<String> carNames = splitCarNames(input);
            List<Car> cars = new ArrayList<>();
            for (String carName : carNames) {
                cars.add(new Car(carName));
            }
            return new Cars(cars);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return initCars();
        }
    }

    private List<String> splitCarNames(String input) {
        return List.of(input.split(","));
    }

    private int initTryCount() {
        try {
            int tries = InputView.inputTries();
            return tries;
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return initTryCount();
        }
    }

    private void race(Cars cars, int lap, NumberGenerator numberGenerator) {
        RacingGame racingGame = new RacingGame(cars, lap);
        racingGame.race(numberGenerator);
    }

    private void showFinalStatus(Cars cars) {
        List<String> winners = cars.calculateWinners();
        OutputView.printWinners(winners);

        List<Car> latestResult = cars.getLatestResult();
        List<CarStatusDto> carStatuses = mapCarsToCarStatuses(latestResult);
        OutputView.printPlayerResult(carStatuses);
    }

    private List<CarStatusDto> mapCarsToCarStatuses(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarStatusDto(car.getCarName(), car.getCurrentPosition()))
                .collect(Collectors.toUnmodifiableList());
    }
}
