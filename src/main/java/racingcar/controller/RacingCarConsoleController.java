package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.util.RandomNumberGenerator;
import racingcar.validation.Validation;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    public void runGame() {
        Cars cars = initCarData();
        movePerRounds(cars, setTryCount());
        outputView.printWinner(cars);
    }

    private Cars initCarData() {
        outputView.printCarNameMessage();

        try {
            String carNames = inputView.inputCarNames();
            Validation.validateCarNames(carNames);
            return new Cars(carNames);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return initCarData();
        }
    }

    private void movePerRounds(Cars cars, int tryCount) {
        outputView.printResultMessage();

        for (int count = 0; count < tryCount; count++) {
            cars.moveForRound(randomNumberGenerator);
        }
    }

    private int setTryCount() {
        outputView.printTryCountMessage();

        try {
            int tryCount = inputView.inputTryCount();
            Validation.validateTryCount(tryCount);
            return tryCount;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return setTryCount();
        }
    }
}
