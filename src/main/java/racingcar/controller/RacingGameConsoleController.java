package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.RacingGame;
import racingcar.domain.RacingResult;
import racingcar.domain.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingGameConsoleController {
    private final InputView inputView;
    private final OutputView outputView;

    public RacingGameConsoleController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        Cars cars = createCarGroup();
        int movingTrial = createMovingTrial();

        RacingGame racingGame = new RacingGame(cars, new RandomNumberGenerator());

        outputView.printNotice();
        raceWithHistory(movingTrial, racingGame);
        outputView.printWinner(racingGame.createRacingResult().pickWinner());
    }

    private Cars createCarGroup() {
        try {
            return new Cars(inputView.readCarNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createCarGroup();
        }
    }

    private int createMovingTrial() {
        try {
            return inputView.readMovingTrial();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createMovingTrial();
        }
    }

    private void raceWithHistory(int movingTrial, RacingGame racingGame) {
        for (int i = 0; i < movingTrial; i++) {
//            racingGame.race();

            RacingResult racingResult = racingGame.createRacingResult();
            outputView.printRacingResult(racingResult.getHistory());
        }
    }
}
