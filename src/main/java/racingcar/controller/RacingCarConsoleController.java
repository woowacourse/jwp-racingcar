package racingcar.controller;

import racingcar.model.Car;
import racingcar.model.CarNumberGenerator;
import racingcar.model.CarRandomNumberGenerator;
import racingcar.model.RacingCars;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacingCarConsoleController {

    private static final int START_POSITION = 0;
    private final CarNumberGenerator carNumberGenerator = new CarRandomNumberGenerator();
    private final OutputView outputView = OutputView.getInstance();
    private final InputView inputView = InputView.getInstance();
    private RacingCars racingCars;


    public void run() {
        final List<Car> cars = generateCars();
        racingCars = new RacingCars(cars);
        final int tryNum = getTryNum();

        race(tryNum);
        showWinners();
    }

    private List<Car> generateCars() {
        outputView.printReadCarNamesMessage();
        final String[] carNames = inputView.readCarNames();
        final List<Car> cars = new ArrayList<>();
        for (final String carName : carNames) {
            cars.add(new Car(carName, START_POSITION));
        }
        return cars;
    }

    private int getTryNum() {
        outputView.printReadTryNumMessage();
        return inputView.readTryNum();
    }

    private void race(final int tryNum) {
        for (int repeatIndex = 0; repeatIndex < tryNum; repeatIndex++) {
            racingCars.tryOneTime(carNumberGenerator);
        }

        outputView.printRacingResultMessage();
        outputView.printCurrentRacingCarsPosition(convertRacingCarsResultForPrint(racingCars.getCars()));
    }

    private void showWinners() {
        final List<String> winners = convertWinnersNameForPrint(racingCars.getWinners());
        outputView.printWinners(winners);
    }

    private Map<String, Integer> convertRacingCarsResultForPrint(final List<Car> currentCars) {
        final Map<String, Integer> racingCarsResult = new HashMap<>();
        for (final Car currentCar : currentCars) {
            racingCarsResult.put(currentCar.getName(), currentCar.getPosition());
        }
        return racingCarsResult;
    }

    private List<String> convertWinnersNameForPrint(final List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }

}
