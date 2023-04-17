package racingcar.car.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import racingcar.car.model.Car;
import racingcar.car.model.CarNumberGenerator;
import racingcar.car.model.CarRandomNumberGenerator;
import racingcar.game.model.RacingCars;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {
    
    private static final int START_POSITION = 0;
    private final CarNumberGenerator carNumberGenerator = new CarRandomNumberGenerator();
    private final OutputView outputView = OutputView.getInstance();
    private final InputView inputView = InputView.getInstance();
    private RacingCars racingCars;
    
    
    public void run() {
        final List<Car> cars = this.generateCars();
        this.racingCars = new RacingCars(cars);
        final int tryNum = this.getTryNum();
        
        this.race(tryNum);
        this.showWinners();
    }
    
    private List<Car> generateCars() {
        this.outputView.printReadCarNamesMessage();
        final String[] carNames = this.inputView.readCarNames();
        final List<Car> cars = new ArrayList<>();
        for (final String carName : carNames) {
            cars.add(new Car(carName, START_POSITION));
        }
        return cars;
    }
    
    private int getTryNum() {
        this.outputView.printReadTryNumMessage();
        return this.inputView.readTryNum();
    }
    
    private void race(final int tryNum) {
        this.outputView.printRacingResultMessage();
        for (int repeatIndex = 0; repeatIndex < tryNum; repeatIndex++) {
            final List<Car> currentCars = this.racingCars.getCars();
            this.racingCars.tryOneTime(this.carNumberGenerator);
            this.outputView.printCurrentRacingCarsPosition(this.convertRacingCarsResultForPrint(currentCars));
        }
    }
    
    private void showWinners() {
        final List<String> winners = this.convertWinnersNameForPrint(this.racingCars.getWinners());
        this.outputView.printWinners(winners);
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
