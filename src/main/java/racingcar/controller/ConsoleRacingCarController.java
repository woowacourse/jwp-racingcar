package racingcar.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import racingcar.controller.dto.GameInfoRequest;
import racingcar.domain.Car;
import racingcar.domain.RacingCars;
import racingcar.service.ConsoleRaceService;
import racingcar.service.RaceService;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class ConsoleRacingCarController {

    private final OutputView outputView;
    private final InputView inputView;
    private final RaceService raceService;

    public ConsoleRacingCarController() {
        this.outputView = new OutputView();
        this.inputView = new InputView();
        this.raceService = new ConsoleRaceService(new RandomNumberGenerator());
    }

    public void run() {
        String[] carNames = readCarNamesStep();
        int tryNum = readTryNumStep();
        RacingCars carsAfterRace = race(carNames, tryNum);
        showWinners(carsAfterRace);
    }

    private String[] readCarNamesStep() {
        outputView.printReadCarNamesMessage();
        return inputView.readCarNames();
    }

    private int readTryNumStep() {
        outputView.printReadTryNumMessage();
        return inputView.readTryNum();
    }

    private RacingCars race(String[] names, int tryNum) {
        RacingCars carsAfterRace = raceService.race(new GameInfoRequest(String.join(",", names), tryNum));
        outputView.printRacingResultMessage();
        List<Car> cars = carsAfterRace.getCars();
        outputView.printPlayResult(convertRacingCarsResultForPrint(cars));
        return carsAfterRace;
    }

    private void showWinners(RacingCars racingCars) {
        List<String> winners = convertWinnersNameForPrint(racingCars.getWinners());
        outputView.printWinners(winners);
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
