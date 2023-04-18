package racingcar.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.util.NumberGenerator;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {

    private static final int START_POSITION = 0;

    private final OutputView outputView;
    private final InputView inputView;
    private final NumberGenerator numberGenerator;

    public RacingCarController(final NumberGenerator numberGenerator) {
        this.outputView = new OutputView();
        this.inputView = new InputView();
        this.numberGenerator = numberGenerator;
    }

    public void run() {
        String[] carNames = readCarNamesStep();
        RacingCars racingCars = RacingCars.makeCars(String.join(",", carNames));
        int tryNum = readTryNumStep();
        race(tryNum, racingCars);
        showWinners(racingCars);
    }

    private String[] readCarNamesStep() {
        outputView.printReadCarNamesMessage();
        String[] carNames = inputView.readCarNames();
        return carNames;
    }

    private void showWinners(RacingCars racingCars) {
        List<String> winners = convertWinnersNameForPrint(racingCars.getWinners());
        outputView.printWinners(winners);
    }

    private void race(int tryNum, RacingCars racingCars) {
        outputView.printRacingResultMessage();
        racingCars.moveAllCars(tryNum, numberGenerator);
        List<Car> currentCars = racingCars.getCars();
        outputView.printPlayResult(convertRacingCarsResultForPrint(currentCars));
    }

    private int readTryNumStep() {
        outputView.printReadTryNumMessage();
        return inputView.readTryNum();
    }

    private Map<String, Integer> convertRacingCarsResultForPrint(List<Car> currentCars) {
        Map<String, Integer> racingCarsResult = new HashMap<>();
        for (Car currentCar : currentCars) {
            racingCarsResult.put(currentCar.getName(), currentCar.getPosition());
        }
        return racingCarsResult;
    }

    private List<String> convertWinnersNameForPrint(List<Car> winners) {
        return winners.stream()
                .map(Car::getName)
                .collect(Collectors.toList());
    }
}
