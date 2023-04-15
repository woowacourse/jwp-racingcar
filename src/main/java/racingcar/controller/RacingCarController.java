package racingcar.controller;

import racingcar.domain.*;
import racingcar.dto.CarStatus;
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
            List<Car> cars = new ArrayList<>();
            for (String carName : carNames) {
                cars.add(new Car(carName));
            }
            return new Cars(cars);
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
        while (lap.isPlayable()) {
            cars.moveCars(numberGenerator);
            List<Car> movedCars = cars.getLatestResult();
            List<CarStatus> carStatuses = mapCarsToCarStatuses(movedCars);
            lap.reduce();
            OutputView.printCarStatus(carStatuses);
        }
    }

    private List<CarStatus> mapCarsToCarStatuses(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarStatus(car.getCarName(), car.getCurrentPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    private void showFinalStatus(Cars cars) {
        List<Car> latestResult = cars.getLatestResult();
        List<CarStatus> carStatuses = mapCarsToCarStatuses(latestResult);
        OutputView.printCarStatus(carStatuses);
    }

    private void prizeWinner(Cars cars) {
        WinnerMaker winnerMaker = new WinnerMaker();
        List<String> winnersName = winnerMaker.getWinnerCarsName(cars.getLatestResult());
        OutputView.printFinalResult(winnersName);
    }
}
