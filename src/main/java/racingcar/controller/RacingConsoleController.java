package racingcar.controller;

import java.util.List;
import racingcar.domain.AdvanceJudgement;
import racingcar.domain.NumberGenerator;
import racingcar.domain.RacingCar;
import racingcar.domain.RacingGame;
import racingcar.domain.RandomNumberGenerator;
import racingcar.domain.Range;
import racingcar.utils.Parser;
import racingcar.validator.Validator;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingConsoleController {
    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

    public void run() {
        List<String> carNames = getValidCarNames();
        int tryCount = getValidTryCount();

        RacingGame racingGame = initializeRoundManager(carNames);

        outputView.printResultHeader();
        outputView.printRoundResult(racingGame.getStartStatus());
        for (int roundCount = 0; roundCount < tryCount; roundCount++) {
            racingGame.runRound();
            outputView.printRoundResult(racingGame.getStatus());
        }

        List<String> winningCarsName = racingGame.getWinningCarsName();
        outputView.printWinners(winningCarsName);
    }

    private List<String> getValidCarNames() {
        try {
            String carNames = inputView.readCarName();
            List<String> parsedCarNames = Parser.parsing(carNames, ",");
            Validator.validateNames(parsedCarNames);
            return parsedCarNames;
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return getValidCarNames();
        }
    }

    private int getValidTryCount() {
        try {
            String tryCount = inputView.readTryCount();
            Validator.validateTryCount(tryCount);
            return Integer.parseInt(tryCount);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return getValidTryCount();
        }
    }


    private void setCars(List<String> carNames, RacingGame racingGame) {
        for (String carName : carNames) {
            racingGame.addRacingCar(new RacingCar(carName));
        }
    }

    private RacingGame initializeRoundManager(List<String> carNames) {
        Range range = new Range(4, 9);
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        AdvanceJudgement advanceJudgement = new AdvanceJudgement(range, numberGenerator);
        RacingGame racingGame = new RacingGame(advanceJudgement);
        setCars(carNames, racingGame);
        return racingGame;
    }
}
