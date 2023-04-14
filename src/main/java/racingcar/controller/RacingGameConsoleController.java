package racingcar.controller;

import java.io.IOException;

import racingcar.domain.CarGroup;
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

    public void run() throws IOException {
        CarGroup carGroup = createCarGroup();
        int movingTrial = createMovingTrial();

        RacingGame racingGame = new RacingGame(carGroup, new RandomNumberGenerator());

        outputView.printNotice();
        raceWithHistory(movingTrial, racingGame);
        outputView.printWinner(racingGame.produceRacingResult().pickWinner());
    }

    private CarGroup createCarGroup() throws IOException {
        try {
            return new CarGroup(inputView.readCarNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createCarGroup();
        }
    }

    private int createMovingTrial() {
        try {
            return inputView.readMovingTrial();
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
            return createMovingTrial();
        }
    }

    private void raceWithHistory(int movingTrial, RacingGame racingGame) {
        for (int i = 0; i < movingTrial; i++) {
            racingGame.race();

            RacingResult racingResult = racingGame.produceRacingResult();
            outputView.printRacingResult(racingResult.getHistory());
        }
    }
}
