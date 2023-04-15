package racingcar.controller;

import java.util.InputMismatchException;
import java.util.List;
import racingcar.domain.Cars;
import racingcar.domain.TryCount;
import racingcar.utils.CarsFactory;
import racingcar.utils.RandomPowerGenerator;
import racingcar.utils.RandomPowerMaker;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final RandomPowerGenerator randomPowerGenerator = new RandomPowerMaker();

    public void startGame() {

        final Cars cars = getCars();
        final TryCount tryCount = getTryCount();

        startRace(cars, tryCount);

        outputView.printWinners(cars);
    }

    private Cars getCars() {
        outputView.requestOfCarNames();

        try {
            final List<String> carNames = inputView.inputCarNames();
            return CarsFactory.createCars(carNames);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return getCars();
        }
    }

    private TryCount getTryCount() {
        outputView.requestOfTryCount();

        try {
            int input = inputView.inputTryCount();
            return new TryCount(input);
        } catch (IllegalArgumentException | InputMismatchException exception) {
            System.out.println(exception.getMessage());
            return getTryCount();
        }
    }

    private void startRace(Cars cars, TryCount tryCount) {
        outputView.printResultMessage();

        for (int i = 0; i < tryCount.getTryCount(); i++) {
            cars.moveAll(randomPowerGenerator);
            outputView.printCurrentRacingStatus(cars);
        }
    }
}
