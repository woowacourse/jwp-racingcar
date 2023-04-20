package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.service.RacingCarService;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarConsoleController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final RacingCarService racingCarService = new RacingCarService();
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
            return racingCarService.getCars(carNames);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return initCarData();
        }
    }

    private int setTryCount() {
        outputView.printTryCountMessage();

        try {
            int tryCount = inputView.inputTryCount();
            return racingCarService.getTrialCount(tryCount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return setTryCount();
        }
    }

    private void movePerRounds(Cars cars, int tryCount) {
        outputView.printResultMessage();
        racingCarService.playGame(cars, tryCount, randomNumberGenerator);
    }
}
